<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<input type="hidden" name="dispatch" value="<s:property value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_SAVE" />"/>
<s:token/>

<%@include file="../messages.jsp" %>
<table cellspacing="0" cellpadding="0" border="0" width="100%" id="content-table">
 <tbody>
 <tr>
	<th class="sized" rowspan="3"><img height="300" width="20" alt="" src="<s:url value="html/images/shared/side_shadowleft.jpg"/>"></th>
	<th class="topleft"></th>
	<td id="tbl-border-top">&nbsp;</td>
	<th class="topright"></th>
	<th class="sized" rowspan="3"><img height="300" width="20" alt="" src="<s:url value="html/images/shared/side_shadowright.jpg"/>"></th>
 </tr>
 <tr>
	<td id="tbl-border-left"></td>
	<td valign="top">
	 <div id="content-table-inner">
	  <table cellspacing="0" cellpadding="0" border="0" width="100%">
	   <tbody>
	   <tr>
	    <td valign="top">
	     <table cellspacing="0" cellpadding="0" border="0" id="id-form">
		   <tbody>