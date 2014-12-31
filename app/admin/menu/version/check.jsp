<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	<style>.result{display:none;}#product-table TR{height:50px;}.result_button{margin:20px auto 0;display:table;}</style>
	<script type="text/javascript" src="<s:url value="html/js/tableForm.js"/>"></script>
	<script type="text/javascript">
	 var resCount = 0;
	 var idInt;
	 $(document).ready(function() {
		 idInt = window.setInterval(function(){
			 $(".results_"+resCount).fadeIn(700);
			 resCount++;
			 if (resCount>7)
				 window.clearInterval(idInt); 
		 },700);
	 });
	</script>
</head>

<body> 

 
<div id="content-outer">
 <div id="contentWrap">


  <%@include file="../../include/messages.jsp"  %>

 <form action="<s:url action="%{#attr.actionname}"/>" id="mainForm" name="tableForm" method="post">
  <%@include file="../../include/table/tableStart.jsp"  %>
		
		 <div id="content-table-inner">
		  <div id="table-content">
			
			 
			  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="product-table">
				 <tbody>
				 <tr>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="menu.check.header.section"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="menu.check.header.status"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="menu.check.header.updates"/></a></th>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.menu"/></b></td>
				 <td><div class="result results_0"><div class="table-icon value-<s:property value="#attr.updates.menuUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_0">
				   <s:if test="#attr.updates.menuUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updated"/></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.languages"/></b></td>
				 <td><div class="result results_1"><div class="table-icon value-<s:property value="#attr.updates.langUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_1">
				   <s:if test="#attr.updates.langUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updates"><s:param value="#attr.updates.langUpdates"/></s:text></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.categories"/></b></td>
				 <td><div class="result results_2"><div class="table-icon value-<s:property value="#attr.updates.catUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_2">
				   <s:if test="#attr.updates.catUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updates"><s:param value="#attr.updates.catUpdates"/></s:text></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.categories.translations"/></b></td>
				 <td><div class="result results_3"><div class="table-icon value-<s:property value="#attr.updates.catTransUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_3">
				   <s:if test="#attr.updates.catTransUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updates"><s:param value="#attr.updates.catTransUpdates"/></s:text></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.products"/></b></td>
				 <td><div class="result results_4"><div class="table-icon value-<s:property value="#attr.updates.prodUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_4">
				   <s:if test="#attr.updates.prodUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updates"><s:param value="#attr.updates.prodUpdates"/></s:text></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.products.translations"/></b></td>
				 <td><div class="result results_5"><div class="table-icon value-<s:property value="#attr.updates.prodTransUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_5">
				   <s:if test="#attr.updates.prodTransUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updates"><s:param value="#attr.updates.prodTransUpdates"/></s:text></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				<tr>
				 <td><b><s:text name="menu.check.form.attachments"/></b></td>
				 <td><div class="result results_6"><div class="table-icon value-<s:property value="#attr.updates.attachUpdates>0"/>"/></div></td>
				 <td>
				  <div class="result results_6">
				   <s:if test="#attr.updates.attachUpdates>0"><div class="elem-updated"><s:text name="menu.check.item.updates"><s:param value="#attr.updates.attachUpdates"/></s:text></div></s:if>
				   <s:else><div class="elem-no-updated"><s:text name="menu.check.item.noupdate"/></div></s:else>
				  </div>
				 </td>
				</tr>
				</tbody>
			 </table>
		 </div>
			
			
			<div class="clear"></div>
		 
		</div>
		
		
	<%@include file="../../include/table/tableEnd.jsp"  %>

 </form>
 
 <form action="<s:url action="menuDeploy" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" method="post">
 <s:token/>
 <div class="result_button">
  <div class="result results_6">
   <s:if test="#attr.updates.isToDeploy()"><input type="submit" class="form-submit" value="<s:text name="menu.check.deploy"/>"></s:if>
	 <s:else><input type="button" class="form-reset" value="<s:text name="menu.check.deploy"/>" disabled="disabled"></s:else>
  </div>
 </div>
 </form>

 <div class="clear">&nbsp;</div>
 </div>
 <div class="clear">&nbsp;</div>
</div>

 
</body>
</html>