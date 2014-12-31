<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
</head>

<body> 

 
<div id="content-outer">
 <div id="contentWrap" align="center">
  
  <table id="discount-table" cellpadding="0" cellspacing="0" border="0">
	 <tr>
	  <td id="htype" class="table-header-repeat line-left"><s:text name="menu.discounts.type"/></td>
	  <td id="hlabel" class="table-header-repeat line-left"><s:text name="menu.discounts.label"/></td>
	  <td id="hdays" class="table-header-repeat line-left"><s:text name="menu.discounts.days"/></td>
	  <td id="hfrom" class="table-header-repeat line-left"><s:text name="menu.discounts.from"/></td>
	  <td id="hto" class="table-header-repeat line-left"><s:text name="menu.discounts.to"/></td>
	  <td id="hprice" class="table-header-repeat line-left"><s:text name="menu.discounts.price"/></td>
	 </tr>
	 <s:iterator var="item" value="#attr.discounts">
	  <tr class="discount-item">
	   <td align="center"><div class="type-discount-icon <s:property value="#item.type"/>"></div></td>
	   <td>&nbsp;<s:property value="#item.label"/></td>
	   <td class="discount-recurring">
	    <s:iterator begin="0" end="6" var="day">
	     <div class="discount-weekday <s:if test="!#item.isRecurringDay(#day)">noselect</s:if> discount-weekday-<s:property value="#day"/>"><s:text name="common.weekday.short.%{#day}"/></div>
	    </s:iterator>
	   </td>
	   <td align="center" class="discount-fromTime"><s:date name="#item.fromTime" format="HH:mm"/></td>
	   <td align="center" class="discount-toTime"><s:date name="#item.toTime" format="HH:mm"/></td>
	   <td align="right" class="discount-price">
		   <s:if test="#item.fixedPrice==-1">
		    <s:property value="#item.discountPrice"/> %
		   </s:if>
		   <s:else>
		   <s:property value="#attr.menu.currency.format(#item.fixedPrice,true)" escapeHtml="false" />
		   </s:else>
		   &nbsp;
		 </td>
	  </tr>
	 </s:iterator>
	</table>
 <div class="clear">&nbsp;</div>
 </div>
 <div class="clear">&nbsp;</div>
</div>

 
</body>
</html>