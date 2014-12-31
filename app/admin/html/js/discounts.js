var currency_digit = 0;
var currency_symbol = "";

$(document).ready(function(){
	discountTableOrdering();
	$(".dragHandle").live({
	  mouseover: function() {$(this).addClass('showDragHandle-mini');},
	  mouseout: function() {$(this).removeClass('showDragHandle-mini');}
	});
	$("INPUT.discountInput").rangeinput({ min: -100, max: 100, step: 1, onSlide : slideDiscount, change: slideDiscount});
	$("INPUT.discountInput").each(function(){
		$(this).after("<div class=\"rangeDiscountDesc\">"+$(this).val()+"%</div>");
	});
	$("INPUT.timeInput").rangeinput({ min: 0, max: 1440, step: 15, onSlide : slideTime, change:slideTime});
	$("INPUT.timeInput").each(function(){
		$(this).after("<div class=\"rangeTimeDesc\">"+getTimeLabel($(this).val())+"</div>");
	});
	$("#discounts-add").click(discountsWin);
	$(".discount-insert-weekday").click(function(){$(this).toggleClass("selected")});
	$("#disc-price").keyup(function(){
		if ($(this).val()!="") {
			$(".discount-td").addClass("opaque");
		} else {
			$(".discount-td.opaque").removeClass("opaque");
		}
	});
	$("DIV.discount-delete").live("click",function(){saveChange();$(this).parents("TR:first").remove()})
	$("DIV.discount-active").live("click",function(){saveChange();activateRow($(this));})
	$("#discount-submit").click(addItem);
	$("#changeDiscounts").parents("FORM:first").submit(function(){prepareData($(this));});
});

function prepareData(form){
	strInputs = "";
	var counter = 0;
	$("#discount-table TR.discount-item").each(function() {
		strInputs += "<input type=\"hidden\" name=\"bean.discounts.policies["+counter+"].recurring\" value=\""+$(this).find(".discount-recurring").attr("item-val")+"\" />";
		strInputs += "<input type=\"hidden\" name=\"bean.discounts.policies["+counter+"].fromTimeStr\" value=\""+$(this).find(".discount-fromTime").text()+"\" />";
		strInputs += "<input type=\"hidden\" name=\"bean.discounts.policies["+counter+"].toTimeStr\" value=\""+$(this).find(".discount-toTime").text()+"\" />";
		if ($(this).find(".discount-price").attr("elem-type")=="P") {
			valPrice = $(this).find(".discount-price SPAN").text();
			valDiscount = 0;
		} else {
			valPrice = -1;
			valDiscount = $(this).find(".discount-price SPAN").text();
		}
		strInputs += "<input type=\"hidden\" name=\"bean.discounts.policies["+counter+"].fixedPrice\" value=\""+parseFloat(valPrice)+"\" />";
		strInputs += "<input type=\"hidden\" name=\"bean.discounts.policies["+counter+"].discountPrice\" value=\""+parseInt(valDiscount)+"\" />";
		strInputs += "<input type=\"hidden\" name=\"bean.discounts.policies["+counter+"].active\" value=\""+!$(this).find(".discount-active").is(".false")+"\" />";
		counter++;
	});
	form.prepend(strInputs);
}

function saveChange(){
	$("#changeDiscounts").val("true");
}
function discountsWin() {
	formReset();
	$("#discount-window").fadeIn("slow")
											 .expose({opacity: 0.6,color:'#000',onClose: function(){$("#discount-window").fadeOut("fast");}});
}
function formReset(){
	$(".discount-insert-weekday").removeClass("selected");
	$("#disc-from").data("rangeinput").setValue("0");
	$("#disc-to").data("rangeinput").setValue("1440");
	$("#disc-discount").data("rangeinput").setValue("0");
	$("#disc-price").val("");
	$(".discount-td.opaque").removeClass("opaque");
}
function getTimeLabel(val) {
	h = Math.floor(val/60);
	m = val % 60;
	return (h>=10 ? h :"0"+h)+":"+(m>=10 ? m : "0"+m);
}
function slideTime(event, value) {
	this.getInput().next(".rangeTimeDesc").html(getTimeLabel(value));
	return true;
}
function slideDiscount(event, value) {
	this.getInput().next(".rangeDiscountDesc").html(value+"%");
	return true;
}
function addItem(){
	saveChange();
	strRecurring = "";
	$(".discount-insert-weekday").each(function(){strRecurring = ($(this).is(".selected") ? "1" : "0")+strRecurring;});
	addRow(0,getByteValue(strRecurring), getTimeLabel($("#disc-from").val()), getTimeLabel($("#disc-to").val()), $("#disc-price").val(), $("#disc-discount").val(), true);
	discountTableOrdering();
	$("#discount-window").fadeOut("slow")
	$.mask.close();
}
function discountTableOrdering() {
	$("#discount-table").tableDnD({
		dragHandle: ".dragHandle",
		onDragStart: function() {saveChange();}
	});
}
function getByteValue(val) {
	var sumValue = 0;
	for(var i=0; i<val.length; i++) {
		if (val[i]=='1')
			sumValue += Math.pow(2,(val.length-1)-i);
	}
	return sumValue;
}
function activateRow(elem,active) {
	if (active==undefined)
		active = elem.is(".false");
	if (active)
		elem.attr("title",elem.attr("title-true")).removeClass("false").addClass("true");
	else
		elem.attr("title",elem.attr("title-false")).addClass("false").removeClass("true");
}
function addRow(id,recurring, fromTime, toTime, price, discount, active) {
	str = $("#discount-row TABLE TBODY").html();
	str = str.replace("[id]",id);
	str = str.replace("[recurring]",recurring);
	str = str.replace("[fromTime]",fromTime);
	str = str.replace("[toTime]",toTime);
	floatPrice = parseFloat(price);
	if (floatPrice>=0) {
		str = str.replace("[type]","P");
		str = str.replace("[price]",floatPrice.toFixed(currency_digit));
		str = str.replace("[symbol]",currency_symbol);
	} else {
		str = str.replace("[type]","D");
		str = str.replace("[price]",(parseInt(discount)>=0 ? "+" : "")+discount);
		str = str.replace("[symbol]","%");
	}
	$('#discount-table tr:last').after(str);
	recString = recurring.toString(2);
	var cont = 0;
	for(var i=recString.length-1; i>=0; i--) {
		if (recString[i]=='1')
			$('#discount-table tr:last .discount-weekday-'+cont).removeClass("noselect");
		cont++;
	}
	if (active) 
		activateRow($('#discount-table tr:last .discount-active'),true);
		
	$('#discount-table tr:last').show();
}