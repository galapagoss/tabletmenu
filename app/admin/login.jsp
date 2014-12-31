<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="include/preHeader.jsp"  %>
	<script type="text/javascript">
	if(self!=top)
		top.document.location.href = '<s:url action="login" namespace="/admin"/>';
	$(document).ready(function () {
		$(".forgot-pwd").click(function () { $("#loginbox").hide();	$("#forgotbox").show();	return false;	});
		$(".back-login").click(function () { $("#loginbox").show();	$("#forgotbox").hide();	return false;	});
		$("INPUT[name='user.nickname']").focus();
	});
	</script>
</head>
<body id="login-bg"> 
 
<!-- Start: login-holder -->
<div id="login-holder">

	<!-- start logo -->
	<div id="logo-login">
		<a href="<s:url action="login"/>"><img src="<s:url value="/admin/html/images/shared/logo.png"/>" width="128" height="93" alt="" /></a>
	</div>
	<!-- end logo -->
	
	<div class="clear"></div>
	
	<!--  start loginbox ................................................................................. -->
	<div id="loginbox">
	
	<!--  start login-inner -->
	<div id="login-inner">
		<form action="<s:url action="login"  method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" method="post">
		<input type="hidden" name="dispatch" value="login" />
		<s:token/>
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th><s:text name="login.form.email"/></th>
			<td><input type="text" name="user.nickname" tabindex="1" class="login-inp" /></td>
		</tr>
		<tr>
			<th><s:text name="login.form.password"/></th>
			<td><input type="password" name="user.password" tabindex="2" class="login-inp" /></td>
		</tr>
		<!-- <tr>
			<th></th>
			<td valign="top"><input type="checkbox" class="checkbox-size" id="login-check" /><label for="login-check">Remember me</label></td>
		</tr> -->
		<tr>
			<th></th>
			<td><input type="submit" class="submit-login" tabindex="3" /></td>
		</tr>
		</table>
		</form>
	</div>
 	<!--  end login-inner -->
	<div class="clear"></div>
	<a href="" class="forgot-pwd"><s:text name="login.form.forgot.password"/></a>
 </div>
 <!--  end loginbox -->
 
<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror escape="false" theme="oxybay"/>
   </div>
</s:if>
 
	<!--  start forgotbox ................................................................................... -->
	<div id="forgotbox">
		<div id="forgotbox-text">Please send us your email and we'll reset your password.</div>
		<!--  start forgot-inner -->
		<div id="forgot-inner">
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th>Email address:</th>
			<td><input type="text" value=""   class="login-inp" /></td>
		</tr>
		<tr>
			<th> </th>
			<td><input type="button" class="submit-login"  /></td>
		</tr>
		</table>
		</div>
		<!--  end forgot-inner -->
		<div class="clear"></div>
		<a href="" class="back-login">Back to login</a>
	</div>
	<!--  end forgotbox -->

</div>
<!-- End: login-holder -->
</body>
</html>