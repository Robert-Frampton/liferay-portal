AUI.add(
	'liferay-menu',
	function(A) {
		var Browser = Liferay.Browser;
		var Lang = A.Lang;
		var Util = Liferay.Util;

		var trim = Lang.trim;

		var ARIA_ATTR_ROLE = 'role';

		var ATTR_CLASS_NAME = 'className';

		var AUTO = 'auto';

		var CSS_BTN_PRIMARY = 'btn-primary';

		var CSS_EXTENDED = 'lfr-extended';

		var CSS_OPEN = 'open';

		var DEFAULT_ALIGN_POINTS = ['tl', 'bl'];

		var EVENT_CLICK = 'click';

		var PARENT_NODE = 'parentNode';

		var STR_BOTTOM = 'b';

		var STR_LEFT = 'l';

		var STR_LTR = 'ltr';

		var STR_RIGHT = 'r';

		var STR_RTL = 'rtl';

		var STR_TOP = 't';

		var MAP_ALIGN_HORIZONTAL_OVERLAY = {
			right: STR_LEFT,
			left: STR_RIGHT
		};

		var MAP_ALIGN_HORIZONTAL_OVERLAY_RTL = {
			left: STR_LEFT,
			right: STR_RIGHT
		};

		var MAP_ALIGN_HORIZONTAL_TRIGGER = {
			right: STR_RIGHT,
			left: STR_LEFT
		};

		var MAP_ALIGN_HORIZONTAL_TRIGGER_RTL = {
			left: STR_RIGHT,
			right: STR_LEFT
		};

		var MAP_ALIGN_VERTICAL_OVERLAY = {
			down: STR_TOP,
			up: STR_BOTTOM
		};

		var MAP_ALIGN_VERTICAL_TRIGGER = {
			down: STR_BOTTOM,
			up: STR_TOP
		};

		var MAP_LIVE_SEARCH = {};

		var REGEX_DIRECTION = /\bdirection-(down|left|right|up)\b/;

		var REGEX_MAX_DISPLAY_ITEMS = /max-display-items-(\d+)/;

		var SELECTOR_ANCHOR = 'a';

		var SELECTOR_LIST_ITEM = 'li';

		var SELECTOR_SEARCH_CONTAINER = '.lfr-menu-list-search-container';

		var SELECTOR_SEARCH_INPUT = '.lfr-menu-list-search';

		var STR_BLANK = '';

		var TPL_MENU = '<div class="open" />';

		var TPL_SEARCH_BOX = '<div class="lfr-menu-list-search-container">' +
				'<input autocomplete="off" aria-autocomplete="list" aria-expanded="true" aria-labelledby="{searchLabeledBy}" aria-owns="{searchOwns}" class="lfr-menu-list-search" id="{searchId}" role="combobox" type="text">' +
			'</div>';

		var Menu = function() {
			var instance = this;

			instance._handles = [];

			if (!Menu._INSTANCE) {
				Menu._INSTANCE = instance;
			}
		};

		Menu.prototype = {
			_closeActiveMenu: function() {
				var instance = this;

				var menu = instance._activeMenu;

				if (menu) {
					var handles = instance._handles;

					A.Array.invoke(handles, 'detach');

					handles.length = 0;

					var trigger = instance._activeTrigger;

					instance._activeMenu = null;
					instance._activeTrigger = null;

					if (trigger.hasClass(CSS_EXTENDED)) {
						trigger.removeClass(CSS_BTN_PRIMARY);
					}
					else {
						trigger.removeClass(CSS_OPEN);
						trigger.get(PARENT_NODE).removeClass(CSS_OPEN);
						menu.removeClass(CSS_OPEN);
					}
				}
			},

			_getMenu: function(trigger) {
				var instance = this;

				var menu = trigger.getData('menu');
				var menuHeight = trigger.getData('menuHeight');

				var liveSearch = menu && MAP_LIVE_SEARCH[menu.guid()];

				if (liveSearch) {
					liveSearch.reset();
				}

				var listItems;

				if (!menu) {
					menu = trigger.next('ul');

					listItems = menu.all(SELECTOR_LIST_ITEM);

					listItems.last().addClass('last');

					trigger.setData('menu', menu);

					instance._setARIARoles(trigger, menu);

					// Util.createFlyouts(
					// 	{
					// 		container: menu.getDOM()
					// 	}
					// );

					//WidgetPositionConstrain

					menu.plug(A.WidgetPositionConstrain);

					menu.set('constrain', true);

					if (trigger.hasClass('select')) {
						menu.delegate(
							'click',
							function(event) {
								var selectedListItem = event.currentTarget;

								var selectedListItemIcon = selectedListItem.one('i');

								var triggerIcon = trigger.one('i');

								if (selectedListItemIcon && triggerIcon) {
									var selectedListItemIconClass = selectedListItemIcon.attr('class');

									triggerIcon.attr('class', selectedListItemIconClass);
								}

								var selectedListItemMessage = selectedListItem.one('.lfr-icon-menu-text');

								var triggerMessage = trigger.one('.lfr-icon-menu-text');

								if (selectedListItemMessage && triggerMessage) {
									triggerMessage.setContent(selectedListItemMessage.text());
								}
							},
							SELECTOR_LIST_ITEM
						);
					}
				}

				if (!menuHeight) {
					menuHeight = instance._getMenuHeight(trigger, menu, listItems || menu.all(SELECTOR_LIST_ITEM));

					trigger.setData('menuHeight', menuHeight);

					if (menuHeight !== AUTO) {
						menu.setStyle('maxHeight', menuHeight);
					}
				}

				//instance._getFocusManager();

				return menu;
			},

			_getMenuHeight: function(trigger, menu, listItems) {
				var instance = this;

				var cssClass = trigger.attr(ATTR_CLASS_NAME);

				var height = AUTO;

				if (cssClass.indexOf('lfr-menu-expanded') === -1) {
					var params = REGEX_MAX_DISPLAY_ITEMS.exec(cssClass);

					var maxDisplayItems = params && parseInt(params[1], 10);

					if (maxDisplayItems && listItems.size() > maxDisplayItems) {
						instance._getLiveSearch(trigger, trigger.getData('menu'));

						height = 0;

						var heights = listItems.slice(0, maxDisplayItems).get('offsetHeight');

						for (var i = heights.length - 1; i >= 0; i--) {
							height += heights[i];
						}
					}
				}

				return height;
			},

			_setARIARoles: function(trigger, menu) {
				var links = menu.all(SELECTOR_ANCHOR);

				var searchContainer = menu.one(SELECTOR_SEARCH_CONTAINER);

				var listNode = menu;

				var ariaListNodeAttr = 'menu';
				var ariaLinksAttr = 'menuitem';

				if (searchContainer) {
					ariaListNodeAttr = 'listbox';
					ariaListNodeAttr = 'option';
				}

				listNode.setAttribute(ARIA_ATTR_ROLE, ariaListNodeAttr);
				links.set(ARIA_ATTR_ROLE, ariaLinksAttr);

				trigger.attr(
					{
						'aria-haspopup': true,
						role: 'button'
					}
				);

				listNode.setAttribute('aria-labelledby', trigger.guid());
			}
		};

		Menu.handleFocus = function(id) {
			var node = A.one(id);

			if (node) {
				node.delegate('mouseenter', A.rbind(Menu._targetLink, node, 'focus'), SELECTOR_LIST_ITEM);
				node.delegate('mouseleave', A.rbind(Menu._targetLink, node, 'blur'), SELECTOR_LIST_ITEM);
			}
		};

		var buffer = [];

		Menu.register = function(id) {
			//var menuNode = document.getElementById(id);

			var menuNode = A.one('#' + id);

			if (!Menu._INSTANCE) {
				new Menu();
			}

			buffer.push(menuNode);

			Menu._registerTask();
		};

		Menu._registerTask = A.debounce(
			function() {
				var instance = Menu._INSTANCE;

				if (buffer.length) {
					var nodes = A.all(buffer);

					nodes.on(EVENT_CLICK, A.bind('_registerMenu', Menu));

					buffer.length = 0;
				}
			},
			100
		);

		Menu._targetLink = function(event, action) {
			var anchor = event.currentTarget.one(SELECTOR_ANCHOR);

			if (anchor) {
				anchor[action]();
			}
		};

		Liferay.provide(
			Menu,
			'_getFocusManager',
			function() {
				var instance = Menu._INSTANCE;

				var focusManager = instance._focusManager;

				if (!focusManager) {
					var bodyNode = instance._overlay.bodyNode;

					bodyNode.plug(
						A.Plugin.NodeFocusManager,
						{
							circular: true,
							descendants: 'li:not(.hide) a,input',
							focusClass: 'focus',
							keys: {
								next: 'down:40',
								previous: 'down:38'
							}
						}
					);

					bodyNode.on(
						'key',
						function(event) {
							var activeTrigger = instance._activeTrigger;

							if (activeTrigger) {
								instance._closeActiveMenu();

								activeTrigger.focus();
							}
						},
						'down:27,9'
					);

					focusManager = bodyNode.focusManager;

					bodyNode.delegate(
						'mouseenter',
						function(event) {
							if (focusManager.get('focused')) {
								focusManager.focus(event.currentTarget.one(SELECTOR_ANCHOR));
							}
						},
						SELECTOR_LIST_ITEM
					);

					focusManager.after(
						'activeDescendantChange',
						function(event) {
							var descendants = focusManager.get('descendants');

							var selectedItem = descendants.item(event.newVal);

							bodyNode.one('ul').setAttribute('aria-activedescendant', selectedItem.guid());
						}
					);

					instance._focusManager = focusManager;
				}

				focusManager.refresh();
			},
			['node-focusmanager'],
			true
		);

		Liferay.provide(
			Menu,
			'_getLiveSearch',
			function(trigger, menu) {
				var instance = this;

				var id = menu.guid();

				var liveSearch = MAP_LIVE_SEARCH[id];

				if (!liveSearch) {
					var listNode = menu.one('ul');

					var results = [];

					listNode.all('li').each(
						function(node) {
							results.push(
								{
									name: trim(node.one('.taglib-text-icon').text()),
									node: node
								}
							);
						}
					);

					liveSearch = new Liferay.MenuFilter(
						{
							content: listNode,
							minQueryLength: 0,
							queryDelay: 0,
							resultFilters: 'phraseMatch',
							resultTextLocator: 'name',
							source: results
						}
					);

					liveSearch.get('inputNode').swallowEvent('click');

					MAP_LIVE_SEARCH[id] = liveSearch;
				}

			},
			['liferay-menu-filter'],
			true
		);

		Liferay.provide(
			Menu,
			'_registerMenu',
			function(event) {
				var instance = Menu._INSTANCE;

				var handles = instance._handles;

				var trigger = event.currentTarget;

				var activeTrigger = instance._activeTrigger;

				if (activeTrigger && (activeTrigger != trigger)) {
					activeTrigger.removeClass(CSS_BTN_PRIMARY);

					activeTrigger.get(PARENT_NODE).removeClass(CSS_OPEN);
				}
				else if (activeTrigger) {
					instance._closeActiveMenu();

					return;
				}

				if (!trigger.hasClass('disabled')) {
					var menu = instance._getMenu(trigger);

					trigger.addClass(CSS_OPEN);
					trigger.get(PARENT_NODE).addClass(CSS_OPEN);
					menu.addClass(CSS_OPEN);

					instance._activeMenu = menu;
					instance._activeTrigger = trigger;

					if (!handles.length) {
						A.Event.defineOutside('touchend');

						handles.push(
							A.getWin().on('resize', A.debounce(instance._positionActiveMenu, 200, instance)),
							A.getDoc().on(EVENT_CLICK, instance._closeActiveMenu, instance)
						);

						var DDM = A.DD && A.DD.DDM;

						if (DDM) {
							handles.push(DDM.on('ddm:start', instance._closeActiveMenu, instance));
						}
					}

					event.halt();
				}
			},
			['aui-widget-cssclass', 'event-outside', 'event-touch', 'widget', 'widget-modality', 'widget-position', 'widget-position-align', 'widget-position-constrain', 'widget-stack', 'widget-stdmod']
		);

		Liferay.Menu = Menu;
	},
	'',
	{
		requires: ['array-invoke', 'aui-debounce', 'aui-node', 'portal-available-languages']
	}
);