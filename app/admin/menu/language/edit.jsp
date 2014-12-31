<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/form.js" includeParams="none"/>"></script>
	<s:fielderror escape="false" theme="oxybay"/>
	<script type="text/javascript">
	$(document).ready(function(){
		$.oxyFormHelpInit();
		top.reloadMain = ($(".message-green").length>0);
	});
	</script>
	
</head>

<body> 


<div id="content-outer">
 <div id="contentWrap">
  <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
   <input type="hidden" name="bean.id" value="<s:property value="#attr.bean.id"/>"/>
   <input type="hidden" name="bean.label" value="<s:property value="#attr.bean.label"/>"/>
   <input type="hidden" name="bean.idMenu" value="<s:property value="#attr.bean.idMenu"/>"/>
   
   <div id="page-heading"><h1><s:if test="#attr.bean.id>0"><s:text name="table.form.title.edit"/></s:if><s:else><s:text name="table.form.title.new"/></s:else></h1></div>
   
   <%@include file="../../include/table/formStart.jsp"  %>
   
   <tr>
    <th valign="top"><label for="lang"><s:text name="menulang.form.lang"/></label></th>
		<td>
		 <img src="<s:url value="html/images/flags/%{#attr.bean.label}.png"/>" title="<s:property value="#attr.bean.label"/>" alt="<s:property value="#attr.bean.label"/>"/>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="active"><s:text name="menulang.form.active"/></label></th>
		<td>
		 <input type="checkbox" <s:if test="#attr.bean.main">disabled="disabled"</s:if> name="bean.active" id="active" value="true" class="formTip" titleMsg="<s:text name="menulang.form.help.active"/>" <s:if test="#attr.bean.active">CHECKED</s:if>/>
		 <div class="helpinfo" for="active"></div>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="labels" id="labels" titleMsg="<s:text name="menulang.form.help.active"/>"><s:text name="menulang.form.labels"/></label></th>
		<td>
		 <div class="customLabels">
		  <table border="0">
		   <s:set var="counter" value="0"/>
		   <s:iterator var="custom" value="#attr.bean.customLabels">
		    <tr>
		     <td class="labelHead"><s:text name="menulang.label.%{#custom.label.label}"/></td>
		     <td>
		      <input type="hidden" name="bean.customLabels[<s:property value="#counter"/>].id" value="<s:property value="#custom.id"/>"/>
		      <input type="hidden" name="bean.customLabels[<s:property value="#counter"/>].label.id" value="<s:property value="#custom.label.id"/>"/>
		      <input type="hidden" name="bean.customLabels[<s:property value="#counter"/>].label.label" value="<s:property value="#custom.label.label"/>"/>
		      <input type="hidden" name="bean.customLabels[<s:property value="#counter"/>].label.value" value="<s:property value="#custom.label.value"/>"/>
		      <input type="text" name="bean.customLabels[<s:property value="#counter"/>].value" value="<s:property value="#custom.value"/>" class="inp-form-mini"/>
		      <span>(<s:property value="#custom.label.value"/>)</span>
		     </td>
		    </tr>
		    <s:set var="counter" value="#counter+1"/>
		   </s:iterator>
		  </table>
		 </div>
		 <div class="helpinfo" for="labels"></div>
		</td>
	 </tr>
	 
	 
	 <!-- Submit -->
	 <tr><td colspan="2" height="5"></td></tr>
   <tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="submit" class="form-submit" value="<s:text name="table.form.submit.edit"/>"/>
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