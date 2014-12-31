<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/tableForm.js"/>"></script>

</head>

<body> 

 <%@include file="../include/topMenu.jsp"  %>
 
 <div class="clear"></div>
 
 <div id="content-outer">

  <%@include file="../include/leftMenu.jsp"  %>


  <div id="content">



  <%@include file="../include/messages.jsp"  %>

 <form action="<s:url action="%{#attr.actionname}"/>" id="mainForm" name="tableForm" method="post">
  <%@include file="../include/table/tableStart.jsp"  %>
		
		
		 <!--  start actions-box ............................................... -->
			<div id="actions-box">
				<a class="action-slider" href=""><s:text name="table.form.actions"/></a>
				<div id="actions-box-slider">
				 <div id="actions-box-slider-bg1">
					<a class="action-item " href="#" onclick="formSubmit()">
					 <span class="table-icon icon-reload"></span>
					 <span class="action-item-name"><s:text name="table.form.actions.reload"/></span>
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
				<tr class="nodrop nodrag">
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="tools.connections.header.db"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="tools.connections.header.pointer"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="tools.connections.header.business"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="tools.connections.header.stack"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="tools.connections.header.obtainDB"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="tools.connections.header.created"/></a></th>
				</tr>
				<s:iterator var="item" value="#attr.connections">
				 <tr>
					<td><s:property value="#item.db"/></td>
					<td><s:property value="#item.pointer"/></td>
					<td><s:property value="#item.bsnClass" escapeHtml="false"/></td>
					<td><s:property value="#item.stackHtml" escapeHtml="false"/></td>
					<td><s:property value="#item.timeObtainDB"/></td>
					<td><s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#item.created)" nice="true"/></td>
				 </tr>
				</s:iterator>
				
				
				</tbody>
			 </table>
		 </div>
			
			
			<div class="clear"></div>
		 
		</div>
		
		
	<%@include file="../include/table/tableEnd.jsp"  %>

 </form>
</div>


<div class="clear">&nbsp;</div>
</div>

<div class="clear">&nbsp;</div>
    

<%@include file="../include/footer.jsp"  %>
 
</body>
</html>