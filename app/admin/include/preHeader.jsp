<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:property value="@com.oxybay.web.resources.keys.ConfigKeys@SITE_NAME"/> <s:property value="@com.oxybay.web.resources.keys.ConfigKeys@SITE_VERSION"/></title>
<!--  Main Css  -->
<link href="<s:url value="html/css/screen.css"/>" rel="stylesheet" type="text/css">
<link href="<s:url value="html/css/js.css"/>" rel="stylesheet" type="text/css">

<!--  libs -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="<s:url value="html/js/libs.js" includeParams="none"/>"></script>
<!--  core js -->
<script type="text/javascript">
$(document).ready(function () {
  $(":checkbox:not(.ui-helper-hidden-accessible)").iButton({labelOn: "<s:text name="common.checkbox.true"/>",labelOff: "<s:text name="common.checkbox.false"/>"});
});
</script>

<script type="text/javascript" src="<s:url value="html/js/core.js" includeParams="none"/>"></script>