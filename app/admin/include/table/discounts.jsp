<tr>
 <th valign="top">
  <label for="discounts" id="discounts" titleMsg="<s:text name="table.form.help.discounts"/>"><s:text name="table.form.discounts"/></label>
 </th>
 <td>

<script type="text/javascript" src="<s:url value="html/js/discounts.js" includeParams="none"/>"></script>
<script type="text/javascript">
 currency_digit = <s:property value="#attr.menu.currency.digit"/>;
 currency_symbol = "<s:property value="#attr.menu.currency.symbolHTML" escapeHtml="false"/>";
</script>

<input type="hidden" name="bean.discounts.changed" id="changeDiscounts" value="false"/>
<!-- Add Window -->
<div style="float:left">
 <div id="discount-window">
  <table cellpadding="0" cellspacing="0" border="0" width="410">
   <tr>
    <td class="discount-field"><s:text name="menu.discounts.days"/></td>
    <td colspan="3">
     <s:iterator begin="0" end="6" var="day">
       <div class="discount-insert-weekday"><s:text name="common.weekday.short.%{#day}"/></div>
      </s:iterator>
     </td>
   </tr>
   <tr>
    <td class="discount-field"><s:text name="menu.discounts.from"/></td>
    <td width="150"> <input id="disc-from" type="range" class="timeInput" default-value="0"/></td>
    <td class="discount-field"><s:text name="menu.discounts.to"/></td>
    <td width="150"><input id="disc-to" type="range" class="timeInput" default-value="1440"/></td>
   </tr>
   <tr>
    <td class="discount-field discount-td"><s:text name="menu.discounts.discount"/></td>
    <td class="discount-td"><input id="disc-discount" type="range" class="discountInput" default-value="0"/></td>
    <td class="discount-field"><s:text name="menu.discounts.price"/></td>
    <td><input type="text" id="disc-price" class="discount-insert-price" value="" size="5"/> <s:property value="#attr.menu.currency.symbolHTML" escapeHtml="false"/></td>
   </tr>
   <tr>
    <td colspan="4" align="center">
     <a id="discount-submit"><s:text name="menu.discounts.add"/></a>
    </td>
   </tr>
  </table>
 </div>
 <!-- Template Row -->
 <div id="discount-row" style="display:none">
  <table>
  <tr class="discount-item" style="display:none" elem-id="[id]">
   <td align="center" class="dragHandle">&nbsp;</td>
   <td class="discount-recurring" item-val="[recurring]">
    <s:iterator begin="0" end="6" var="day">
     <div class="discount-weekday noselect discount-weekday-<s:property value="#day"/>"><s:text name="common.weekday.short.%{#day}"/></div>
    </s:iterator>
   </td>
   <td align="center" class="discount-fromTime">[fromTime]</td>
   <td align="center" class="discount-toTime">[toTime]</td>
   <td align="right" class="discount-price" elem-type="[type]"><span>[price]</span> [symbol]</td>
   <td class="noborder"></td>
   <td class="noborder" align="right"><div title="<s:text name="table.form.activate.true"/>" title-true="<s:text name="table.form.activate.false"/>" title-false="<s:text name="table.form.activate.true"/>" class="discount-active discount-icon false"></div></td>
   <td class="noborder" align="right"><div title="<s:text name="table.form.delete"/>" class="discount-delete discount-icon delete"></div></td>
   </tr>
   </table>
 </div>
 
	<a id="discounts-add"><s:text name="menu.discounts.add"/></a><br/>
	<table id="discount-table" cellpadding="0" cellspacing="0" border="0">
	 <tr class="nodrop nodrag">
	  <td id="horder" class="table-header-repeat line-left"><s:text name="menu.discounts.order"/></td>
	  <td id="hdays" class="table-header-repeat line-left"><s:text name="menu.discounts.days"/></td>
	  <td id="hfrom" class="table-header-repeat line-left"><s:text name="menu.discounts.from"/></td>
	  <td id="hto" class="table-header-repeat line-left"><s:text name="menu.discounts.to"/></td>
	  <td id="hprice" class="table-header-repeat line-left"><s:text name="menu.discounts.price"/></td>
	  <td id="hspace" class="noborder"></td>
	  <td id="hicon" class="noborder"></td>
	  <td id="hicon" class="noborder"></td>
	 </tr>
	</table>
	
	<% if (request.getAttribute("bean") instanceof com.oxybay.web.beans.menu.product.ProductBean) { %>
	 <div class="discount-inherit"><a class="fancyDiscounts" href="<s:url action="product"><s:param name="dispatch">discounts</s:param><s:param name="bean.id" value="#attr.bean.id"></s:param></s:url>"><s:text name="product.alldiscounts"/></a></div>
	<% } %>
 </div>
 
 <div class="helpinfo" for="discounts"></div>

</td>
</tr>