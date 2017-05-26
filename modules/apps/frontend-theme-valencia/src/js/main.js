AUI().ready(

	function() {

		$('.carousel').carousel();

		var transparent = true;

		if ($('.navbar-color-on-scroll').length !== 0) {
			$(window).on(
				'scroll',
				debounce(
					function () {
						if (!transparent) {
							transparent = true;

							$('.navbar-color-on-scroll').addClass('navbar-transparent');
						}

						if ($(document).scrollTop() > 100 && transparent) {
							transparent = false;

							$('.navbar-color-on-scroll').removeClass('navbar-transparent');
						}
					},
					17
				)
			);
		}

		function debounce(func, wait, immediate) {
			var timeout;

			return function() {
				var args = arguments;
				var instance = this;

				clearTimeout(timeout);

				timeout = setTimeout(
					function() {
						timeout = null;

						if (!immediate) {
							func.apply(instance, args);
						}
					}, wait)
				;

				if (immediate && !timeout) {
					func.apply(instance, args);
				}
			};
		}

		if ($('#ControlMenu').hasClass('control-menu')) {
			$('#navigation').addClass('navbar-control-helper');
		}
	}
);