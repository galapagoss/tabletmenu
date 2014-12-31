$(document).ready(function () {
  $(".showhide-account").click(function () { $(".account-content").slideToggle("fast"); $(this).toggleClass("active"); return false; });
  $(".message-close").live("click",function () {$(this).parents(".message-div").fadeOut("slow");});
});

$(document).bind("click", function (e) {
    if (e.target.id != $(".showhide-account").attr("class")) $(".account-content").slideUp();
    if (e.target.id != $(".action-slider").attr("class")) $("#actions-box-slider").slideUp();
});
 
function addWarning(text){
	$("#div-messages").append('<div class="message-yellow message-div no-display" ><table width="100%" cellspacing="0" cellpadding="0" border="0"><tr><td class="yellow-left">'+text+'</td><td class="yellow-right"><a class="close-yellow message-close"></a></td></tr></table></div>');
	$("#div-messages .message-yellow:hidden").fadeIn("slow");
}
function addNotify(text){
	$("#div-messages").append('<div class="message-blue message-div no-display" ><table width="100%" cellspacing="0" cellpadding="0" border="0"><tr><td class="blue-left">'+text+'</td><td class="blue-right"><a class="close-blue message-close"></a></td></tr></table></div>');
	$("#div-messages .message-blue:hidden").fadeIn("slow");
}

(function($) {
	$.fn.oxyProgressSubmit = function(label,url) {
		jQuery(this).submit(function(){
			form = jQuery(this);
			if (jQuery("INPUT:file[value!='']").length) {
				jQuery(form).attr("enctype","multipart/form-data");
				$.fancybox( 
						'<div id="progress-meter" class="meter"><span style="width: 0%"></span></div><div id="progress-loading-label">'+label+'</div><div id="progress-bytes-length"></div><div id="progress-bytes-read"></div>',
						{padding:0,margin:0,width:500,height:80,minHeight:80,autoSize:false,modal:true,afterShow : function(){$.oxyProgressUpdate(url);}}
				);
			}
			return true;
		});
	}
	$.oxyProgressUpdate = function(url){
		$.getJSON(url, function(data) {
			if (data.percent>0) {
				jQuery("#progress-meter span").stop().animate({width:Math.min(parseInt(data.percent),100) +'%'},1000,'easeInQuad');
				jQuery("#progress-bytes-length").text(" / "+parseInt(data.bytesLength/1024)+" K");
				jQuery("#progress-bytes-read").text(parseInt(data.bytesRead/1024));
			}
			if(data.percent<100) {
				window.setTimeout(function(){$.oxyProgressUpdate(url)},3000);
			}
	  });
	}
})(jQuery);
