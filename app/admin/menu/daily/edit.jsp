<%@page import="com.oxybay.web.beans.menu.daily.DailyMenuItem"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/form.js" includeParams="none"/>"></script>
	<script type="text/javascript" src="<s:url value="html/js/dailyItem.js" includeParams="none"/>"></script>
	<s:fielderror escape="false" theme="oxybay"/>
	<script type="text/javascript">
	$(document).ready(function(){
		$.oxyFormHelpInit();
		$("#mainForm").oxyProgressSubmit('<s:text name="common.loading"/>','<s:url action="info" namespace="/multipart"/>');
		top.reloadMain = ($(".message-green").length>0);
	});
	</script>
</head>

<body> 


<div id="content-outer">
 <div id="contentWrap">
  <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
   <input type="hidden" name="bean.id" value="<s:property value="#attr.bean.id"/>"/>
   
   <div id="page-heading"><h1><s:if test="#attr.bean.id>0"><s:text name="table.form.title.edit"/></s:if><s:else><s:text name="table.form.title.new"/></s:else></h1></div>
   
   <%@include file="../../include/table/formStart.jsp"  %>
   
	<tr>
    <th valign="top"><label for="active"><s:text name="daily.form.active"/></label></th>
		<td>
		 <input type="checkbox" name="bean.active" id="active" value="true" class="formTip" titleMsg="<s:text name="daily.form.help.active"/>" <s:if test="#attr.bean.active">CHECKED</s:if>/>
		 <div class="helpinfo" for="active"></div>
		</td>
	 </tr>
	 <th valign="top"><label for="price"><s:text name="daily.form.price"/></label></th>
		<td>
		 <input type="text" name="bean.price" id="price" value="<s:property value="#attr.bean.price"/>" class="inp-form-mini formTip" titleMsg="<s:text name="daily.form.help.price"/>"/>
		 &nbsp; <s:property value="#attr.menu.currency.symbolHTML" escapeHtml="false"/>
		 <div class="helpinfo" for="price"></div>
		</td>
	 </tr>
	 
	<tr>
	 <th valign="top"><label for="elements" id="elements" titleMsg="<s:text name="daily.form.help.elements"/>"><s:text name="daily.form.elements"/></label></th>
	 <td>
	 <div style="float:left">
	 <!-- Template Row -->
	 <div id="daily-item-row" style="display:none">
	  <table>
	  <tr class="daily-item-item" style="display:none" elem-id="[id]" value-id="[idvalue]">
	   <td align="center" class="dragHandle">&nbsp;</td>
	   <td align="center" class="daily-item-item-type" elem-value="[type]"><div class="type-discount-icon [type]"></div></td>
	   <td align="center" class="daily-item-item-title">[title]</td>
	   <td class="noborder"></td>
	   <td class="noborder" align="right"><div title="<s:text name="table.form.activate.true"/>" title-true="<s:text name="table.form.activate.false"/>" title-false="<s:text name="table.form.activate.true"/>" class="daily-item-active daily-item-icon false"></div></td>
	   <td class="noborder" align="right"><div title="<s:text name="table.form.delete"/>" class="daily-item-delete daily-item-icon delete"></div></td>
	  </tr>
	  </table>
	 </div>
	 
		<!-- SELECT BOX -->
		<select id="daily-item-add" class="formTip chosen" err-msg="<s:text name="daily.elements.error"/>" data-placeholder="<s:text name="daily.elements.add"/>" data-no_results_text="<s:text name="table.form.select.noItemFound"/>">
		 <option value="" selected="selected"></option>
		 <optgroup label="<s:text name="daily.elements.%{@com.oxybay.web.beans.menu.daily.DailyMenuItem$ItemType@PRODUCT}"/>">
		  <s:iterator var="item" value="#attr.products">
		   <option value="<s:property value="@com.oxybay.web.beans.menu.daily.DailyMenuItem$ItemType@PRODUCT+','+#item.id"/>" ><s:property value="#item.mainLang.title"/></option>
		  </s:iterator>
		 </optgroup>
		 <optgroup label="<s:text name="daily.elements.%{@com.oxybay.web.beans.menu.daily.DailyMenuItem$ItemType@CATEGORY}"/>">
		  <s:iterator var="item" value="#attr.categories">
		  <option value="<s:property value="@com.oxybay.web.beans.menu.daily.DailyMenuItem$ItemType@CATEGORY+','+#item.id"/>"><s:property value="#item.mainLang.title"/></option>
		 </s:iterator>
		 </optgroup>
		 <optgroup label="<s:text name="daily.elements.%{@com.oxybay.web.beans.menu.daily.DailyMenuItem$ItemType@TAG}"/>">
		  <s:iterator var="item" value="#attr.tags">
		   <option value="<s:property value="@com.oxybay.web.beans.menu.daily.DailyMenuItem$ItemType@TAG+','+#item.id"/>"><s:property value="#item.label"/></option>
		  </s:iterator>
		 </optgroup>
		 </select>
		 
		 
		<table id="daily-item-table" cellpadding="0" cellspacing="0" border="0">
		 <tr class="nodrop nodrag">
		  <td id="horder" class="table-header-repeat line-left"><s:text name="daily.elements.list.header.order"/></td>
		  <td id="htype" class="table-header-repeat line-left"><s:text name="daily.elements.list.header.type"/></td>
		  <td id="htitle" class="table-header-repeat line-left"><s:text name="daily.elements.list.header.title"/></td>
		  <td id="hspace" class="noborder"></td>
		  <td id="hicon" class="noborder"></td>
		  <td id="hicon" class="noborder"></td>
		 </tr>
		</table>
	
	 </div>
	 <div class="helpinfo" for="elements"></div>
	 <s:iterator var="item" value="#attr.bean.items">
	  <script type="text/javascript">
		 addRow(<s:property value="#item.id"/>,
				 		'<s:property value="#item.type"/>',
				 		'<s:property value="#item.title.replaceAll('\\\'','\\\\\\\\\\\'')"/>',
				 		<s:property value="#item.active"/>);
	  </script>
	 </s:iterator>
	</td>
	</tr>
	
	<!-- Submit -->
    <tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="submit" class="form-submit" value="<s:if test="#attr.bean.id>0"><s:text name="table.form.submit.edit"/></s:if><s:else><s:text name="table.form.submit.new"/></s:else>"/>
		</td>
	 </tr>
	
	 <%@include file="../../include/table/formEnd.jsp"  %>
  </form>
  
  <div class="clear">&nbsp;</div>
 </div>
 <div class="clear">&nbsp;</div>
</div>

</body>
</html>