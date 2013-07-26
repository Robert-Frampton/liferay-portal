AUI.add(
	'liferay-social-bookmarks',
	function(A) {
		var NAME = 'social-bookmarks',
			WIN = A.getWin();

		var SocialBookmarks = A.Component.create(
			{
				ATTRS: {
					shareWindowDimensions: {
						value: {
							height: 436,
							width: 626
						},
						validator: function(val) {
							return (A.Lang.isObject && (A.Object.keys(val) === ['height', 'width']));
						}
					},
					shareWindowFeatures: {
						valueFn: function() {
							var instance = this;

							var shareWindowFeatures = [
								'left=' + instance.get('shareWindowPosition').left,
								'height=' + instance.get('shareWindowDimensions').height,
								'toolbar=0',
								'top=' + instance.get('shareWindowPosition').top,
								'status=0',
								'width=' + instance.get('shareWindowDimensions').width
							];

							return shareWindowFeatures.join(',');
						}
					},
					shareWindowPosition: {
						valueFn: function() {
							var instance = this;

							var shareWindowDimensions = instance.get('shareWindowDimensions');

							return {
								left: ((WIN.get('innerWidth') / 2) - (shareWindowDimensions.width / 2)),
								top: ((WIN.get('innerHeight') / 2) - (shareWindowDimensions.height / 2))
							};
						}
					}
				},

				NAME: NAME,

				prototype: {
					initializer: function(config) {
						var instance = this;

						A.one('#' + config.trigger + ' .btn-group').once('mouseover', instance._onTriggerMouseover, instance);
					},

					_onClick: function(event) {
						var instance = this;

						instance._openShareWindow(event.currentTarget.attr('data-url'), null);
					},

					_onTriggerMouseover: function(event) {
						var instance = this;

						A.getBody().all('.social-bookmark .taglib-icon').each(
							function(item, index, collection) {
								item.attr('data-url', item.attr('href'));

								item.attr('href', 'javascript:void(0);');

								item.on('click', instance._onClick, instance);
							}
						);
					},

					_openShareWindow: function(url, windowName) {
						var instance = this;

						window.open(url, windowName, instance.get('shareWindowFeatures')).focus();

						void('');
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