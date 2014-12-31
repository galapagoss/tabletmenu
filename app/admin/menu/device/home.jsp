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
		
		<div class="box-tab">
		 <div class="blueright"></div><div class="blueleft"><s:text name="device.devicekey"><s:param value="#attr.deviceDomain.getCompleteBindKey()"/></s:text></div>
		 <div class="yellowright"></div><div class="yellowleft"><s:text name="device.unlockkey"><s:param value="#attr.deviceDomain.unlockKey"/></s:text></div>
		</div>
		
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
					<th class="table-header-repeat line-left "><a class="no-ordering"><s:text name="device.list.header.id"/></a></th>
					<th class="table-header-repeat line-left width-order"><a class="no-ordering"><s:text name="device.list.header.version"/></a>	</th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="device.list.header.updated"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="device.list.header.registered"/></a></th>
					<th class="table-header-options line-left"><a class="no-ordering"><s:text name="device.list.header.options"/></a></th>
				</tr>
				<s:iterator var="item" value="#attr.page.items">
				 <tr>
					<td align="center"><s:property value="#session.userAccount.domain.label"/>-<s:property value="#item.id"/></td>
					<td align="center"><s:property value="#item.version.version"/></td>
					<td><s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#item.lastUpdate)" nice="true"/> </td>
					<td><s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#item.creationDate)" nice="true"/> </td>
					<td class="options-width">
					 <a class="fancyDetail table-icon icon-edit" title="<s:text name="table.form.actions.edit"/>" href="<s:url action="%{#attr.actionname}"><s:param name="dispatch" value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_EDIT"/><s:param name="bean.id" value="#item.id"/></s:url>"></a>
					 <a class="table-icon icon-del" beanid="<s:property value="#item.id"/>" confirm="<s:text name="table.form.confirm.delete"/>" dispatch="<s:property value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_DELETE"/>" title="<s:text name="table.form.actions.delete"/>" onclick="confirmDelete(this)"></a>
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