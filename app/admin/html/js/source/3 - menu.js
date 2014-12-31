$(function() {
	$('#sidebar>ul>li:not([class*="current"])>ul').hide();
	$('#sidebar>ul>li:not([class*="nosubmenu"])>a').click(function(){
		e=$(this).parent();$('#sidebar>ul>li.current').removeClass('current').find('ul:first').slideUp();
		e.addClass('current').find('ul:first').slideDown();}
	);
	
	var htmlCollapse=$('#menucollapse').html();
	/*if($.cookie('isCollapsed')){
		$('body').addClass('collapsed');
		$('#menucollapse').html('&#9654;');
	}*/
	$('#menucollapse').click(function(){
		var body=$('body');
		body.toggleClass('collapsed');
		isCollapsed=body.hasClass('collapsed');
		if(isCollapsed){
			$(this).html('&#9654;');
		}else{
			$(this).html(htmlCollapse);
		}
	});
});