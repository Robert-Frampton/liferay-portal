(function() {
	var pluginName = 'a11yhelpbtn';

	CKEDITOR.plugins.add(
		pluginName,
		{
			init: function(editor) {
				if (editor.ui.addButton) {
					editor.ui.addButton(
						'A11YBtn',
						{
							command: 'a11yHelp',
							label: Liferay.Language.get('action.HELP')
						}
					);
				}
			}
		}
	);
})();