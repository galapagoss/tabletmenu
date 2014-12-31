$(document).ready(function(){
	dailyItemTableOrdering();
	$(".dragHandle").live({
	  mouseover: function() {$(this).addClass('showDragHandle-mini');},
	  mouseout: function() {$(this).removeClass('showDragHandle-mini');}
	});
	$("DIV.daily-item-delete").live("click",function(){$(this).parents("TR:first").remove()})
	$("DIV.daily-item-active").live("click",function(){activateRow($(this));})
	$("#daily-item-add").parents("FORM:first").submit(function(){prepareData($(this));});
	$("#daily-item-add").chosen().change(function(){
		var valArr = $(this).val().split(",");
		if (valArr.length==2) {
			if ($(".daily-item-item[value-id='"+$(this).val()+"']").length==0) {
				addRow(valArr[1],valArr[0],$(this).find("option:selected").html(), true);
				dailyItemTableOrdering();
				$(this).val("");
				$(this).trigger("liszt:updated");
			} else 
				alert($(this).attr("err-msg"));
		}
	});
});

function prepareData(form){
	strInputs = "";
	var counter = 0;
	
	$("#daily-item-table TR.daily-item-item").each(function() {
		strInputs += "<input type=\"hidden\" name=\"bean.items["+counter+"].id\" value=\""+$(this).attr("elem-id")+"\" />";
		strInputs += "<input type=\"hidden\" name=\"bean.items["+counter+"].type\" value=\""+$(this).find(".daily-item-item-type").attr("elem-value")+"\" />";
		strInputs += "<input type=\"hidden\" name=\"bean.items["+counter+"].active\" value=\""+!$(this).find(".discount-active").is(".false")+"\" />";
		counter++;
	});
	form.prepend(strInputs);
}

function dailyItemTableOrdering() {
	$("#daily-item-table").tableDnD({
		dragHandle: ".dragHandle"
	});
}
function activateRow(elem,active) {
	if (active==undefined)
		active = elem.is(".false");
	if (active)
		elem.attr("title",elem.attr("title-true")).removeClass("false").addClass("true");
	else
		elem.attr("title",elem.attr("title-false")).addClass("false").removeClass("true");
}
function addRow(id,type,title, active) {
	str = $("#daily-item-row TABLE TBODY").html();
	str = str.replace("[id]",id);
	str = str.replace("[idvalue]",type+","+id);
	str = str.replace("[type]",type);
	str = str.replace("[title]",title);
	$('#daily-item-table tr:last').after(str);
	if (active) 
		activateRow($('#daily-item-table tr:last .daily-item-active'),true);
		
	$('#daily-item-table tr:last').show();
}