$('.js-hide').click(function (e) {
	$(this).next('canvas').slideToggle();
});
if (window.location.hash.substr(1))
	$('#'+window.location.hash.substr(1) + " canvas").show(0);