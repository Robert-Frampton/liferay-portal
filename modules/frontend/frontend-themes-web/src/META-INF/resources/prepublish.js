var path = require('path');

var FRONTEND_THEMES_WEB = 'frontend-themes-web';

var PATH_FRONTEND_THEMES_WEB = path.join(__dirname, '../../../');

if (path.basename(PATH_FRONTEND_THEMES_WEB) === FRONTEND_THEMES_WEB) {
	var exec = require('child_process').exec;

	var antBuildThemes = exec(
		'ant build-themes',
		{
			cwd: PATH_FRONTEND_THEMES_WEB
		},
		function(error, stdout, stderr) {
			if (error) {
				process.kill(process.pid);
			}
		}
	);

	var write = function(data) {
		process.stdout.write(data);
	};

	antBuildThemes.stderr.on('data', write);
	antBuildThemes.stdout.on('data', write);
}