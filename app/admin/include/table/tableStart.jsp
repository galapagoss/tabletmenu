<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<input type="hidden" name="dispatch" value=""/>
<input type="hidden" name="bean.id" value="0"/>
<input type="hidden" name="page.submitted" value="true"/>
<input type="hidden" name="page.page" value="1"/>
<input type="hidden" name="page.order" value="<s:property value="#attr.page.order"/>"/>
<input type="hidden" name="page.idList" value=""/>
<input type="hidden" name="page.orderList" value=""/>
<table width="100%" cellspacing="0" cellpadding="0" border="0" id="content-table">
	<tbody>
	<tr>
		<th class="sized" rowspan="3"><img width="20" height="300" alt="" src="<s:url value="html/images/shared/side_shadowleft.jpg"/>"></th>
		<th class="topleft"></th>
		<td id="tbl-border-top">&nbsp;</td>
		<th class="topright"></th>
		<th class="sized" rowspan="3"><img width="20" height="300" alt="" src="<s:url value="html/images/shared/side_shadowright.jpg"/>"></th>
	</tr>
	<tr>
		<td id="tbl-border-left"></td>
		<td>