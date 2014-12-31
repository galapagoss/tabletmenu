(function($) {
	$.fn.oxyFormHelp = function(settings) {
		settings = jQuery.extend({
			helpDivID: "",
			hover: false,
			textNoSelection: ""
		}, settings);
		if (jQuery("#hiddenFormErrors").length>0) {
			jQuery("#"+settings.helpDivID).hide();
			jQuery("#"+settings.helpDivID).html("<div class=\"left\"><div class=\"help-icon-error\"></div></div><div class=\"right formHelpError\"><div class=\"general\">"+jQuery("#"+settings.helpDivID).attr("msggeneral")+"</div>"+jQuery("#hiddenFormErrors").html()+"</div>");
			jQuery("#"+settings.helpDivID).fadeIn("slow");
		}
		if(settings.hover) {
			jQuery(this).live({
				mouseover: function() {showHelp($("#"+jQuery(this).attr("for")));},
			  mouseout: function() {hideHelp();}
			});
		} else {
			jQuery(this).live("focus",function(){
				// Custom References
				// TextEditor
				if (jQuery(this).is("DIV[contenteditable]"))
					inputDest = jQuery(this).parent().next("textarea");
				else if (jQuery(this).is("DIV.dd"))
					inputDest = jQuery(this).prev().find("SELECT");
				else if (jQuery(this).is("INPUT:radio,INPUT:checkbox"))
					inputDest = jQuery(this).parents(".group-radiocheck").length>0 ? jQuery(this).parents(".group-radiocheck") : jQuery(this);
				else
					inputDest = jQuery(this);
				
				showHelp(inputDest);
			});
			jQuery(this).live("blur",function(){
				hideHelp();
			});
		}
		
		showHelp = function(dest) {
			label = jQuery("label[for='"+jQuery(dest).attr("id")+"']").text();
			title = jQuery(dest).attr("titleMsg");
			error = jQuery(dest).attr("errorMsg")
			str = "<div class=\"left\"><div class=\"help-icon-info\"></div></div><div class=\"right\"><H5>"+(label ? label : "") + "</H5>" +(title ? title : "") + "</div><div class=\"clear\"/>";
			if (error)
				str += "<div class=\"lines-dotted-short\"/><div class=\"left\"><div class=\"help-icon-error\"></div></div><div class=\"right formHelpError\">"+(error ? error : "") + "</div>";
			if (settings.textNoSelection=="")
				settings.textNoSelection = jQuery("#"+settings.helpDivID).html();
			jQuery("#"+settings.helpDivID).stop()
																		.hide()
																		.html(str)
																		.fadeIn("slow");
		}
		
		hideHelp = function() {
			jQuery("#"+settings.helpDivID).html(settings.textNoSelection);
		}
	}
	
	$.oxyFormHelpInit = function() {
		$(".formTip,DIV[contenteditable],DIV.dd").oxyFormHelp({helpDivID:"related-act-inner"});
		$(".helpinfo").oxyFormHelp({helpDivID:"related-act-inner",hover:true});
	}
	
	$.oxyFormHelpError = function(field,error) {
		if (field!='' && jQuery("INPUT[name='"+field+"']").length>0) {
			elem = jQuery("INPUT[name='"+field+"']").eq(0);
			if (jQuery(elem).is(":file")) {
				jQuery(elem).attr("errorMsg",error);
				jQuery(elem).parents(".customfile").addClass("inputError");
				return true;
			} else if (jQuery(elem).is("INPUT[ref]")) {
				jQuery("#"+jQuery(elem).attr("ref")).addClass("inputError");
				jQuery("#"+jQuery(elem).attr("ref")).attr("errorMsg",error);
				return true;
			} else if (jQuery(elem).is(":visible")) {
				jQuery(elem).attr("errorMsg",error).addClass("inputError");
				return true;
			}
		}
		if (jQuery("#hiddenFormErrors").length>0)
			jQuery("#hiddenFormErrors UL").append("<li>"+error+"</li>");
		else {
			jQuery("BODY").append("<div id=\"hiddenFormErrors\" style=\"display:none\"><ul class=\"greyarrow\"><li>"+error+"</li></ul></div>");
		}
		return true;
	}
})(jQuery);

$(document).ready(function(){
	$('a.reloadPage').live("click",function(){document.location.href=document.location.href.replace("!postData","")});
	$("SELECT:not(.chosen)").msDropDown();
	$("SELECT.chosen").chosen();
  $('INPUT:file').customFileInput();
  $('.fancyImage').fancybox();
	$(".file-delredo").click(function(){
		jQuery(this).parent().toggleClass("redo");
		elem = jQuery("INPUT[name='"+jQuery(this).attr("rel")+"']");
		elem.val(elem.val()*-1);
	})
	$('.fancyDiscounts').fancybox({
		'padding'		: 0,
		'width'		: 600,
		'height'		: 350,
		'closeBtn'	: false,
		'helpers' : { overlay: { closeClick: true }, title : null},      
		'type'			: 'iframe'
	});
});