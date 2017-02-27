AUI.add(
	'liferay-dynamic-inline-scroll',
	function(A) {
		var Lang = A.Lang;

		var TPL_LIST_ITEM = '<li>' +
				'<a href="{href}" onclick="{onlick}">{pageTitle}</a>' +
			'</li>';

		var DynamicInlineScroll = A.Component.create(
			{
				ATTRS: {
					cur: {
						value: 0
					},

					curParam: {
						value: ''
					},

					forcePost: {
						value: false
					},

					formName: {
						value: ''
					},

					initialPages: {
						value: 0
					},

					jsCall: {
						value: ''
					},

					namespace: {
						value: ''
					},

					pages: {
						value: 0
					},

					url: {
						value: ''
					},

					urlAnchor: {
						value: ''
					}
				},

				NAME: 'liferaydynamicinlinescroll',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._inlineScrollers = A.all('ul.pagination ul.inline-scroller');
					},

					bindUI: function() {
						var instance = this;

						instance._inlineScrollers.each(
							function(item, index) {
								item.on('scroll', instance._onScroll, instance);
							}
						);
					},

					_getHREF: function(pageIndex) {
						var instance = this;

						var curParam = instance.get('curParam');
						var formName = instance.get('formName');
						var jsCall = instance.get('jsCall');
						var namespace = instance.get('namespace');
						var url = instance.get('url');
						var urlAnchor = instance.get('urlAnchor');

						curParam = namespace + curParam;

						if (url !== null) {
							return url + curParam + '=' + pageIndex + urlAnchor;
						}

						return 'javascript:document.' + formName + '.' + curParam + '.value = "' + pageIndex + '"; ' + jsCall;
					},

					_getOnClick: function(pageIndex) {
						var instance = this;

						var curParam = instance.get('curParam');
						var namespace = instance.get('namespace');

						return 'event.preventDefault(); ' + namespace + 'submitForm("' + namespace + curParam + ',' + pageIndex + '");';
					},

					_onScroll: function(event) {
						var instance = this;

						var currentPage = Number(instance.get('cur'));
						var initialPages = Number(instance.get('initialPages'));
						var pages = Number(instance.get('pages'));

						var target = event.target;

						var pageIndex = Number(target.attr('data-pageindex'));
						var pageIndexMax = Number(target.attr('data-maxindex'));
						var scrollHeight = target.get('scrollHeight');
						var scrollTop = target.get('scrollTop');

						if (pageIndex === 0) {
							var pageIndexCurrent = Number(target.attr('data-curindex'));

							if (pageIndexCurrent === 0) {
								pageIndex = initialPages;
							}
							else {
								pageIndex = pageIndexCurrent + initialPages;
							}
						}

						if (pageIndexMax === 0) {
							pageIndexMax = Number(pages);
						}

						if ((currentPage <= pages) && (pageIndex < pageIndexMax) && (scrollTop >= (scrollHeight - 300))) {
							var listItemTPL = TPL_LIST_ITEM;

							var listItemHtml = Lang.sub(
								listItemTPL,
								{
									href: instance._getHREF(pageIndex),
									onclick: instance.get('forcePost') ? instance._getOnClick(pageIndex) : '',
									pageTitle: pageIndex
								}
							);

							pageIndex++;

							event.target.setAttribute('data-pageindex', pageIndex);

							listItemHtml = A.Node.create(listItemHtml);

							event.target.append(listItemHtml);
						}
					}
				}
			}
		);

		Liferay.DynamicInlineScroll = DynamicInlineScroll;
	},
	'',
	{
		requires: ['aui-base']
	}
);