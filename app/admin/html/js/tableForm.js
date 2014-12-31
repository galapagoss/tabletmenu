var reloadMain = false;
var formName; 
var orderChanged = false;

$(document).ready(function () {
		formName = document.tableForm;
	  $(".action-slider").click(function () { $("#actions-box-slider").slideToggle("fast"); $(this).toggleClass("activated"); return false; });
	  $('#product-table	tr').hover(function () { $(this).addClass('activity-blue'); }, function () {$(this).removeClass('activity-blue');});
	  $(".ui-checkbox").click(function(){ clickCheckbox(this); })
	  $("#toggle-all").click(function(){
	  	$("SPAN.ui-checkbox"+($("#toggle-all").hasClass("ui-checkbox-state-checked") ? ".ui-checkbox-state-checked" : ":not(.ui-checkbox-state-checked)")).each(function(){ clickCheckbox(this); });
	  	$(this).toggleClass("ui-checkbox-state-checked");
	  })
	  $("INPUT[name=idListVal]:checked").attr("checked",false);
	  memOrder();
	  $('.fancyDetail').fancybox({
			'padding'		: 0,
			'openEffect'	: 'elastic',
			'closeEffect'	: 'elastic',
			'width'		: 1000,
			'helpers' : { overlay: { closeClick: false }, title : null},      
			'closeClick':true,
			'type'			: 'iframe',
			'beforeLoad': function(){$.mask.close();},
			'afterClose' : function() {if (reloadMain) $("#mainForm").submit();}
		});
});
function clickCheckbox(elem){
	$(elem).toggleClass("ui-checkbox-state-checked");
	$(elem).parent().children("INPUT:checkbox").attr("checked",$(elem).hasClass("ui-checkbox-state-checked"));
}
function formSubmit(){
	formName.submit();
}
function formDispatch(dispatch){
	formName['dispatch'].value = dispatch;
	formName.submit();
}
function pager(numPage){
	formName['page.page'].value = numPage;
	formSubmit();
}
function order(id){
	if (formName['page.order'].value != id) {
		formName['page.order'].value = id;
		formSubmit();
	}
}
function confirmDelete(elem){
	if (confirm($(elem).attr("confirm"))) {
		formName['dispatch'].value = $(elem).attr("dispatch");
		formName['bean.id'].value = $(elem).attr("beanid");
		formSubmit();
	}
	return true;
}
function deleteList(elem,dispatch) {
	str = "";
	$("INPUT[name=idListVal]:checked").each(function(){
		str += (str!="" ? "," : "") + $(this).val();
	});
	if (str=="")
		alert($(elem).attr("nodata"));
	else if (confirm($(elem).attr("confirm"))) {
		formName['dispatch'].value = dispatch;
		formName['page.idList'].value = str;
		formSubmit();
	} 
}
function memOrder(){
	str = "";
	$("#product-table TR[order]").each(function(){
		str += (str!="" ? "," : "") + $(this).attr("order");
	});
	if (str!="")
		formName['page.orderList'].value = str;
}
function saveOrder(noData, dispatch) {
	str = "";
	$("#product-table TR[order]").each(function(){
		str += (str!="" ? "," : "") + $(this).attr("id");
	});
	if (str=="" || !orderChanged)
		alert(noData);
	else {
		formName['dispatch'].value = dispatch;
		formName['page.idList'].value = str;
		formSubmit();
	}
}
