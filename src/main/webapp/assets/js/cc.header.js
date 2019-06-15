jQuery(function($) {
	$(document).ready(function() {
	});
});
$("#menu li").mouseenter(function() {
	if (! $(this).hasClass("selected")) {
		$(this).animate({'border-color':'#fff'}, 300);
	}
});
$("#menu li").mouseleave(function() {
	if (! $(this).hasClass("selected")) {
		$(this).animate({'border-color':'transparent'}, 300);
	}
});