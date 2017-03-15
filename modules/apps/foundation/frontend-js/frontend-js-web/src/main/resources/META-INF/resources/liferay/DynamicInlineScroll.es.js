import core from 'metal';
import dom from 'metal-dom';
import { EventHandler } from 'metal-events';
import PortletBase from 'frontend-js-web/liferay/PortletBase.es';

/**
 * DynamicInlineScroll appends list item elements to dropdown menus with inline-scrollers
 * on scroll events to improve page load performance.
 *
 * @extends {Component}
 */
class DynamicInlineScroll extends PortletBase {
	/**
	 * @inheritDoc
	 */
	created() {
		this.eventHandler_ = new EventHandler();
	}

	/**
	 * @inheritDoc
	 */
	attached() {
		let inlineScrollers = this.all('ul.pagination ul.inline-scroller');

		if (inlineScrollers) {
			let inlineScrollersArr = [];

			for (let i = 0; i < inlineScrollers.length; i++) {
				inlineScrollersArr.push(inlineScrollers[i]);
			}

			inlineScrollersArr.forEach(
				el => {
					this.eventHandler_.add(el.addEventListener('scroll', (event) => {
						this.onScroll_(event);
					}));
				}
			);
		}
	}

	/**
	 * @inheritDoc
	 */
	detached() {
		super.detached();
		this.eventHandler_.removeAllListeners();
	}

	/**
	 * Returns the href attribute value for each
	 *
	 * @param {number} pageIndex Index of page
	 * @protected
	 * @return {string} String value of href
	 */
	getHREF_(pageIndex) {
		if (this.url !== null) {
			return this.url + this.namespace + this.curParam + '=' + pageIndex + this.urlAnchor;
		}

		return 'javascript:document.' + this.formName + '.' + this.namespace + this.curParam + '.value = "' + pageIndex + '; ' + this.jsCall;
	}

	/**
	 * Returns the value of the parameter passed in as a Number object
	 *
	 * @param {string|!Object} val The string or Object to be converted to a number
	 * @protected
	 * @return {number} Number value of parameter
	 */
	getNumber_(val) {
		return Number(val);
	}

	/**
	 * An event triggered when a dropdown menu with an inline-scroller is scrolled.
	 * Dynamically adds list item elements to the dropdown menu as it is scrolled down.
	 *
	 * @param {Event} event The scroll event triggered by scrolling a dropdown menu
	 * with an inline-scroller
	 * @protected
	 */
	onScroll_(event) {
		let initialPages = this.initialPages;
		let pages = this.pages;

		let target = event.currentTarget;

		let pageIndex = this.getNumber_(target.getAttribute('data-page-index'));
		let pageIndexMax = this.getNumber_(target.getAttribute('data-max-index'));

		if (pageIndex === 0) {
			let pageIndexCurrent = this.getNumber_(target.getAttribute('data-current-index'));

			if (pageIndexCurrent === 0) {
				pageIndex = initialPages;
			}
			else {
				pageIndex = pageIndexCurrent + initialPages;
			}
		}

		if (pageIndexMax === 0) {
			pageIndexMax = pages;
		}

		if ((this.cur <= pages) && (pageIndex < pageIndexMax) && (target.getAttribute('scrollTop') >= (target.getAttribute('scrollHeight') - 300))) {
			let anchor = document.createElement('a');
			let listItem = document.createElement('li');

			anchor.innerText = pageIndex;

			anchor.setAttribute('href', this.getHREF_(pageIndex));

			listItem.appendChild(anchor);

			pageIndex++;

			event.target.appendChild(listItem);

			event.target.setAttribute('data-page-index', pageIndex);

			dom.on(listItem, 'click', (event) => {
				if (this.forcePost == 'true') {
					event.preventDefault();

					let form = document.getElementById(this.randomNamespace + this.namespace + 'pageIteratorFm');

					form.elements[this.namespace + this.curParam].value = event.currentTarget.textContent;

					form.submit();
				}
			});
		}
	}
}

/**
 * State definition.
 * @ignore
 * @static
 * @type {!Object}
 */
DynamicInlineScroll.STATE = {
	/**
	 * Current page
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	cur: {
		setter: 'getNumber_',
		validator: core.isString
	},

	/**
	 * URL parameter for current page
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	curParam: {
		validator: core.isString
	},

	/**
	 * Forces a form post when a page on the dropdown menu is clicked
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	forcePost: {
		validator: core.isString
	},

	/**
	 * Form name
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	formName: {
		validator: core.isString
	},

	/**
	 * Number of pages loaded to the inline-scroll dropdown menu for the first
	 * page load
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	initialPages: {
		setter: 'getNumber_',
		validator: core.isString
	},

	/**
	 * JavaScript call
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	jsCall: {
		validator: core.isString
	},

	/**
	 * Namespace
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	namespace: {
		validator: core.isString
	},

	/**
	 * Total number of pages
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	pages: {
		setter: 'getNumber_',
		validator: core.isString
	},

	/**
	 * Random namespace
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	randomNamespace: {
		validator: core.isString
	},

	/**
	 * URL
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	url: {
		validator: core.isString
	},

	/**
	 * URL anchor
	 * @instance
	 * @memberof DynamicInlineScroll
	 * @type {string}
	 */
	urlAnchor: {
		validator: core.isString
	}
};

export default DynamicInlineScroll;