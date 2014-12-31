<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/tableForm.js"/>"></script>
</head>

<body> 

 <%@include file="../../include/topMenu.jsp"  %>
 
 <div class="clear"></div>
 
 <div id="content-outer">

  <%@include file="../../include/leftMenu.jsp"  %>


  <div id="content">



  <%@include file="../../include/messages.jsp"  %>
  
 <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
  <s:token/>
  <%@include file="../../include/table/tableStart.jsp"  %>
		
		 <!--  start actions-box ............................................... -->
			<div id="actions-box">
				<a class="action-slider" href=""><s:text name="table.form.actions"/></a>
				<div id="actions-box-slider">
				 <div id="actions-box-slider-bg1">
					<a id="generateKey" class="action-item" href="#" onclick="formDispatch('generateKey')">
					 <span class="table-icon icon-key"></span>
					 <span class="action-item-name"><s:text name="table.form.actions.keygeneration"/></span>
					</a>
					</div>
					<div id="actions-box-slider-bg2"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!-- end actions-box........... -->
			
		 <div id="content-table-inner">
		  <div id="table-content">
			 
			  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="product-table">
				 <tbody>
				 <tr>
					<th class="table-header-repeat line-left width-warning"><a class="no-ordering"><s:text name="menulang.list.header.warning"/></a></th>
					<th class="table-header-repeat line-left width-id"><a class="no-ordering"><s:text name="menulang.list.header.id"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="menulang.list.header.flag"/></a></th>
					<th class="table-header-repeat line-left width-100"><a class="no-ordering"><s:text name="menulang.list.header.label"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="menulang.list.header.main"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="menulang.list.header.active"/></a></th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="menulang.list.header.description"/></a></th>
					<th class="table-header-options line-left"><a class="no-ordering"><s:text name="menulang.list.header.options"/></a></th>
				</tr>
				<s:iterator var="item" value="#attr.page.items">
				 <tr>
				  <td><div class="table-icon msg-<s:property value="#item.warning"/>" title="<s:text name="menulang.warning.%{#item.warning}"/>"/></td>
					<td align="center"><s:property value="#item.id"/></td>
					<td align="center">
					 <img src="<s:url value="html/images/flags/%{#item.label}_32.png"/>" title="<s:property value="#item.description"/>" alt="<s:property value="#item.description"/>"/>
					</td>
					<td><s:property value="#item.label"/></td>
					<td><div class="table-icon value-<s:property value="#item.main"/>"/></td>
					<td><div class="table-icon value-<s:property value="#item.active"/>"/></td>
					<td><s:property value="#item.description"/></td>
					<td class="options-width">
					 <a class="fancyDetail table-icon icon-edit" title="<s:text name="table.form.actions.edit"/>" href="<s:url action="%{#attr.actionname}"><s:param name="dispatch" value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_EDIT"/><s:param name="bean.label" value="#item.label"/><s:param name="bean.idMenu" value="#item.idMenu"/></s:url>"></a>
					</td>
				 </tr>
				</s:iterator>
				</tbody>
			 </table>
		 </div>
			
			
			<%@include file="../../include/table/pageFooter.jsp" %>
			
			<div class="clear"></div>
		 
		</div>
		
		
	<%@include file="../../include/table/tableEnd.jsp"  %>

 </form>
</div>


<div class="clear">&nbsp;</div>
</div>

<div class="clear">&nbsp;</div>
    

<%@include file="../../include/footer.jsp"  %>
 
</body>
</html>