<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
  <script type="text/javascript">
  top.$("#<s:property value="#attr.bean.idCategory"/>-<s:property value="#attr.bean.label"/>").addClass("opaque");
  </script>
</head>

<body> 

 
<div id="content-outer">
 <div id="contentWrap">
  <br/><br/>
  <div align="center">
   <b><s:text name="table.form.messages.delete.ok"/></b>
   </br>
   <img src="<s:url value="html/images/shared/delete.png"/>" />
  </div>
 <div class="clear">&nbsp;</div>
 </div>
 <div class="clear">&nbsp;</div>
</div>

 
</body>
</html>