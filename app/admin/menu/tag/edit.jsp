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
    <th valign="top"><label for="label"><s:text name="tag.form.label"/></label></th>
		<td>
		 <input type="text" name="bean.label" id="label" value="<s:property value="#attr.bean.label"/>" class="inp-form formTip" titleMsg="<s:text name="tag.form.help.label"/>"/>
		 <div class="helpinfo" for="label"></div>
		</td>
	 </tr>
	 <!-- Discounts -->
	 <%@include file="../../include/table/discounts.jsp" %>
	 <s:iterator var="item" value="#attr.bean.discounts.policies">
	  <script type="text/javascript">
		 addRow(<s:property value="#item.id"/>,
				 		<s:property value="#item.recurring"/>,
				 		'<s:date name="#item.fromTime" format="HH:mm"/>',
				 		'<s:date name="#item.toTime" format="HH:mm"/>',
				 		<s:property value="#item.fixedPrice"/>,
				 		<s:property value="#item.discountPrice"/>,
				 		<s:property value="#item.active"/>);
	  </script>
	 </s:iterator>
	 
	 <!-- Submit -->
	 <tr><td colspan="2" height="5"></td></tr>
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