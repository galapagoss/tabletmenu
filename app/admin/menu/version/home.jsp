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
					<a class="action-item fancyDetail" href="<s:url action="%{#attr.actionname}"><s:param name="dispatch">check</s:param></s:url>">
					 <span class="table-icon icon-new"></span>
					 <span class="action-item-name"><s:text name="table.form.actions.new"/></span>
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
					<th class="table-header-repeat line-left width-id"><a class="no-ordering"><s:text name="menu.list.header.id"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="menu.list.header.status"/></a>	</th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="menu.list.header.date"/></a>	</th>
					<th class="table-header-repeat line-left width-order"><a class="no-ordering"><s:text name="menu.list.header.version"/></a></th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="menu.list.header.filesql"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="menu.list.header.filezip"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="menu.list.header.fileop"/></a>	</th>
				</tr>
				<s:iterator var="item" value="#attr.page.items">
				 <tr>
					<td><s:property value="#item.id"/></td>
					<td><div class="table-icon value-<s:property value="#item.deployEnd!=null"/>"/></td>
					<td><s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToZone(#item.creationDate,#session.userAccount.timezone)" format="dd/MM/yyyy HH:mm"/> </td>
					<td><s:property value="#item.version"/></td>
					<td align="center">
					 <s:if test="#item.fileSql!=null">
					  <a href="<s:url value="%{#item.getSQLUrl()}"/>" class="version-icon icon-sql"></a>
					  <span class="filesize">(<s:property value="@com.oxybay.web.resources.utils.Utils@getFormattedSize(#item.fileSqlSize)" />)</span>
					  </s:if>
					</td>
					<td align="center">
					<s:if test="#item.fileSql!=null">
					 <s:if test="#item.fileZip!=null">
					  <a href="<s:url value="%{#item.getZIPUrl()}"/>" class="version-icon icon-zip"></a>
					  <span class="filesize">(<s:property value="@com.oxybay.web.resources.utils.Utils@getFormattedSize(#item.fileZipSize)" />)</span>
					 </s:if>
					 <s:else><a class="version-icon icon-zip-disabled"></a></s:else>
					</s:if>
					</td>
					<td align="center">
					 <s:if test="#item.fileSql!=null">
					  <s:if test="#item.fileOp!=null">
					   <a href="<s:url value="%{#item.getOPUrl()}"/>" class="version-icon icon-cmd fancyDetail"></a>
					   <span class="filesize">(<s:property value="@com.oxybay.web.resources.utils.Utils@getFormattedSize(#item.fileOpSize)" />)</span>
					  </s:if>
					  <s:else><a class="version-icon icon-cmd-disabled"></a></s:else>
					 </s:if>
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