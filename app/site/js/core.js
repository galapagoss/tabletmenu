$(document).ready(function(){
});


function callRegister() {
	$.fancybox({
		'padding'		: 0,
		'autoScale'		: false,
		'transitionIn'	: 'elastic',
		'transitionOut'	: 'elastic',
		'width'		: 566,
		'height'		: 360,
		'hideOnOverlayClick'			: false,
		'href'			: $("#newUser").attr("href"),
		'type'			: 'iframe'
	});
}

(function($) {$.fn.invokeRegistration = function() {
	 $(this).click(callRegister);
	 return false;
	} 
})(jQuery);

function callUpgrade(profile,href) {
	urlRef = (href) ? href : document.location.href;
	$.fancybox({
		'padding'		: 0,
		'autoScale'		: false,
		'transitionIn'	: 'elastic',
		'transitionOut'	: 'elastic',
		'width'		: 566,
		'height'		: 526,
		'hideOnOverlayClick'			: false,
		'href'			: urlUpgrade+"/"+profile+"?url="+escape(urlRef),
		'type'			: 'iframe'
	});
}

(function($) {$.fn.requiredUpgrade = function() {
	href = ($(this).attr("anchor")) ? document.location.href+"#"+$(this).attr("anchor") : $(this).attr("href");
	$(this).click(function(){callUpgrade($(this).attr("reserved"),href);return false;});
	} 
})(jQuery);

function checkTitleHead() {
	hTit = $(".box-article .box-heading").height();
	$(".box-article .box-heading").each(function() {
		if ($(this).children("H2").height()+$(this).find(".subtitle A").height()>hTit)
			$(this).children(".subtitle").hide();
	})
}