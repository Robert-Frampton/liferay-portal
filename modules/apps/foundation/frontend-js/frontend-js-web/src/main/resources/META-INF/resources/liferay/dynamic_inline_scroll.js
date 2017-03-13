AUI.add(
	'liferay-dynamic-inline-scroll',
	function(A) {
		var Lang = A.Lang;

		var TPL_LIST_ITEM = '<li>' +
				'<a href="{href}">{pageTitle}</a>' +
			'</li>';

		var DynamicInlineScroll = A.Component.create(
			{
				ATTRS: {
					cur: {
						getter: '_getValue',
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
						getter: '_getValue',
						value: 0
					},

					jsCall: {
						value: ''
					},

					namespace: {
						value: ''
					},

					pages: {
						getter: '_getValue',
						value: 0
					},

					randomNamespace: {
						value: ''
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
							function(item) {
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

						return 'javascript:document.' + formName + '.' + curParam + '.value = "' + pageIndex + '; ' + jsCall;
					},

					_getValue: function(val) {
						return Number(val);
					},

					_onScroll: function(event) {
						var instance = this;

						var currentPage = instance.get('cur');
						var initialPages = instance.get('initialPages');
						var pages = instance.get('pages');

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
									pageTitle: pageIndex
								}
							);

							listItemHtml = A.Node.create(listItemHtml);

							listItemHtml.on(
								'click',
								function(event) {
									if (instance.get('forcePost') == 'true') {
										event.preventDefault();

										var curParam = instance.get('curParam');
										var namespace = instance.get('namespace');
										var randomNamespace = instance.get('randomNamespace');

										var form = document.getElementById(randomNamespace + namespace + 'pageIteratorFm');

										form.elements[namespace + curParam].value = pageIndex - 1;

										form.submit();
									}
								},
								instance
							);

							pageIndex++;

							event.target.setAttribute('data-pageindex', pageIndex);

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