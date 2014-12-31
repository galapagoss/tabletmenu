<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../include/preHeader.jsp"  %>
</head>

<body> 

<div id="content-outer">
 <div id="contentWrap">
  <br/><br/>
  <div align="center">
   <b><s:text name="common.messages.error.token"><s:param><s:url action="%{#attr.actionname}"/></s:param></s:text></b>
   </br>
   <img src="<s:url value="html/images/shared/token_error.png"/>" />
  </div>
 <div class="clear">&nbsp;</div>
 </div>
 <div class="clear">&nbsp;</div>
</div>

 
</body>
</html>