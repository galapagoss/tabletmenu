<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/formHelp.js" includeParams="none"/>"></script>
	<s:fielderror escape="false" theme="oxybay"/>
	<script type="text/javascript">
	$(document).ready(function(){
		$.oxyFormHelpInit();
		top.reloadMain = ($(".messages").length>0);
	});
	</script>
	
</head>

<body> 


<div id="content-outer">
 <div id="contentWrap">
  <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
   <input type="hidden" name="bean.id" value="<s:property value="#attr.bean.id"/>"/>
   
   <div id="page-heading"><h1><s:text name="user.pwd.title"/></h1></div>
   
   <%@include file="../../include/table/formStart.jsp"  %>
   
   <tr>
    <th valign="top"><label for="old"><s:text name="user.pwd.form.old"/></label></th>
		<td>
		 <input type="password" id="old" class="inp-form formTip" name="oldPassword" value="" titleMsg="<s:text name="user.pwd.form.help.old"/>">
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="new"><s:text name="user.pwd.form.new"/></label></th>
		<td>
		 <input type="password" id="new" class="inp-form formTip" name="bean.password" value="" titleMsg="<s:text name="user.pwd.form.help.new"/>">
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="confirmNew"><s:text name="user.pwd.form.confirmNew"/></label></th>
		<td>
		 <input type="password" id="confirmNew" class="inp-form formTip" name="bean.confirmPassword" value="" titleMsg="<s:text name="user.pwd.form.help.confirmNew"/>">
		</td>
	 </tr>

	 <!-- Submit -->
	 <tr><td colspan="2" height="5"></td></tr>
   <tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="submit" class="form-submit" value="<s:text name="table.form.submit.edit"/>">
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