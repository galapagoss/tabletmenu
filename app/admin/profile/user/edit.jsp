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
		top.reloadMain = ($(".messages").length>0);
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
    <th valign="top"><label for="email"><s:text name="user.form.email"/></label></th>
		<td>
		 <s:if test="#attr.bean.id>0">
		  <s:property value="#attr.bean.email"/>
		  <input type="hidden" class="inp-form" name="bean.email" value="<s:property value="#attr.bean.email"/>">
		 </s:if>
		 <s:else>
		  <input type="text" id="email" class="inp-form formTip" name="bean.email" value="<s:property value="#attr.bean.email"/>" titleMsg="<s:text name="user.form.help.email"/>">
		 </s:else>
		</td>
	 </tr>
	 
	 <s:if test="#attr.bean.id==0">
	  <tr>
	   <th valign="top"><label for="password"><s:text name="user.form.password"/></label></th>
		 <td>
		  <input type="password" id="password" class="inp-form formTip" name="bean.password" value="" titleMsg="<s:text name="user.form.help.password"/>">
		 </td>
		</tr>
		<tr>
	   <th valign="top"><label for="confirmPassword"><s:text name="user.form.confirmPassword"/></label></th>
		 <td>
		  <input type="password" id="confirmPassword" class="inp-form formTip" name="bean.confirmPassword" value="" titleMsg="<s:text name="user.form.help.confirmPassword"/>">
		 </td>
		</tr> 
	 </s:if>
	 
	 <tr><td colspan="2" height="5"></td></tr>
	 <tr>
    <th valign="top"><label for="active"><s:text name="user.form.active"/></label></th>
		<td>
		 <input type="checkbox" name="bean.active" value="true" id="active" class="formTip" titleMsg="<s:text name="user.form.help.active"/>" <s:if test="#attr.bean.active">CHECKED</s:if>/>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="timezone"><s:text name="user.form.timezone"/></label></th>
		<td>
		 <select name="bean.timezoneID" id="timezone" class="formTip" titleMsg="<s:text name="user.form.help.timezone"/>">
	    <s:iterator var="zone" value="@com.oxybay.web.resources.utils.Timezones@getList()">
	     <option value="<s:property value="#zone.ID"/>" <s:if test="#attr.bean.timezone.ID==#zone.ID">selected="true"</s:if>><s:property value="#zone.ID"/> <s:text name="common.gmt"/> <s:property value="@com.oxybay.web.resources.utils.Timezones@offsetGMT(#zone)"/></option>
	    </s:iterator>
	   </select>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="language"><s:text name="user.form.language"/></label></th>
		<td>
		 <select name="bean.language.label" id="language" class="formTip" titleMsg="<s:text name="user.form.help.language"/>" style="width:200px;">
	    <s:iterator var="lang" value="#attr.langs">
	     <option title="<s:url value="html/images/flags/%{#lang.label}.png"/>" value="<s:property value="#lang.label"/>" <s:if test="#attr.bean.language.label==#lang.label">selected="true"</s:if>><s:property value="#lang.description"/></option>
	    </s:iterator>
	   </select>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="nickname"><s:text name="user.form.nickname"/></label></th>
		<td>
		 <input type="text" id="nickname" class="inp-form formTip" name="bean.nickname" value="<s:property value="#attr.bean.nickname"/>" titleMsg="<s:text name="user.form.help.nickname"/>">
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="firstname"><s:text name="user.form.firstname"/></label></th>
		<td>
		 <input type="text" id="firstname" class="inp-form formTip" name="bean.firstname" value="<s:property value="#attr.bean.firstname"/>" titleMsg="<s:text name="user.form.help.firstname"/>">
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="lastname"><s:text name="user.form.lastname"/></label></th>
		<td>
		 <input type="text" id="lastname" class="inp-form formTip" name="bean.lastname" value="<s:property value="#attr.bean.lastname"/>" titleMsg="<s:text name="user.form.help.lastname"/>">
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="profile"><s:text name="user.form.profile"/></label></th>
		<td>
		 <select name="bean.type" id="profile" class="formTip" titleMsg="<s:text name="user.form.help.profile"/>">
		  <option value="<s:property value="@com.oxybay.web.beans.profile.UserBean@TYPE_USER"/>" <s:if test="#attr.bean.type==@com.oxybay.web.beans.profile.UserBean@TYPE_USER">selected="true"</s:if>><s:text name="user.profile.%{@com.oxybay.web.beans.profile.UserBean@TYPE_USER}"/></option>
		  <option value="<s:property value="@com.oxybay.web.beans.profile.UserBean@TYPE_DOMAIN_ADMIN"/>" <s:if test="#attr.bean.type==@com.oxybay.web.beans.profile.UserBean@TYPE_DOMAIN_ADMIN">selected="true"</s:if>><s:text name="user.profile.%{@com.oxybay.web.beans.profile.UserBean@TYPE_DOMAIN_ADMIN}"/></option>
		 </select>
		</td>
	 </tr>
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