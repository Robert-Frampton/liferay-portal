AUI.add(
	'liferay-social-bookmarks',
	function(A) {
		var BODY = A.getBody();

		var NAME = 'social-bookmarks';

		var SHARE_WINDOW_HEIGHT = 436;

		var SHARE_WINDOW_WIDTH = 626;

		var STR_DATA_URL = 'data-url';

		var STR_HREF = 'href';

		var WIN = A.getWin();

		BODY.delegate(
			'click',
			function(event) {
				var shareWindowFeatures = [
					'left=' + ((WIN.get('innerWidth') / 2) - (SHARE_WINDOW_WIDTH / 2)),
					'height=' + SHARE_WINDOW_HEIGHT,
					'toolbar=0',
					'top=' + ((WIN.get('innerHeight') / 2) - (SHARE_WINDOW_HEIGHT / 2)),
					'status=0',
					'width=' + SHARE_WINDOW_WIDTH
				];

				var url = event.currentTarget.attr(STR_DATA_URL);

				window.open(url, null, shareWindowFeatures.join(',')).focus();

				void('');
			},
			'.social-bookmark .taglib-icon'
		);

		var SocialBookmarks = A.Component.create(
			{
				NAME: NAME,

				prototype: {
					initializer: function(config) {
						var instance = this;

						var trigger = A.one('#' + config.trigger + ' .btn-group');

						if (trigger) {
							trigger.once('mouseover', instance._onTriggerMouseover, instance, config.items);
						}
					},

					_onTriggerMouseover: function(event, items) {
						var instance = this;

						var itemsSelector = '.' + items + ' .taglib-icon';

						BODY.all(itemsSelector).each(
							function(item, index, collection) {
								if (!item.attr(STR_DATA_URL)) {
									item.attr(STR_DATA_URL, item.attr(STR_HREF));

									item.attr(STR_HREF, 'javascript:void(0);');
								}
							}
						);
					}
				}
			}
		);

		Liferay.SocialBookmarks = SocialBookmarks;
	},
	'',
	{
		requires: ['aui-component', 'aui-node']
	}
);