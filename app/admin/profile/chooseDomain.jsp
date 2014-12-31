<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../include/preHeader.jsp"  %>
<script type="text/javascript">
$(document).ready(function () {
	$('#product-table	tr[id]').hover(function () { $(this).addClass('activity-blue'); }, function () {$(this).removeClass('activity-blue');});
	$('#product-table	tr[id]').click(function(){
		$("#selDomain").val($(this).attr("id"));
		document.tableForm.submit();
	});
});
</script>
<style>
 TR[id]{cursor:pointer;}
</style>
</head>

<body> 

 <%@include file="../include/topMenu.jsp"  %>
 
 <div class="clear"></div>
 
 <div id="content-outer">

  <%@include file="../include/leftMenu.jsp"  %>


  <div id="content">

  <div id="page-heading"><h1><s:text name="domains.choose.title"/></h1></div>


 <%@include file="../include/messages.jsp"  %>

 <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
  <input type="hidden" id="selDomain" name="domain.id" value=""/>
  <s:token/>
  
  <%@include file="../include/table/tableStart.jsp"  %>
		
		 <div id="content-table-inner">
		  <div id="table-content">
			
			 
			  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="product-table">
				 <tbody>
				 <tr>
					<th class="table-header-repeat line-left width-id"><a class="no-ordering"><s:text name="domains.choose.list.header.id"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="domains.choose.list.header.active"/></a></th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="domains.choose.list.header.label"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="domains.choose.list.header.description"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="domains.choose.list.header.creation"/></a>	</th>
				</tr>
				<s:iterator var="item" value="#attr.domains">
				 <tr <s:if test="#item.active">id="<s:property value="#item.id" />"</s:if>>
					<td><s:property value="#item.id"/></td>
					<td><div class="table-icon value-<s:property value="#item.active"/>"/></td>
					<td><s:property value="#item.label"/></td>
					<td><s:property value="#item.description"/></td>
					<td>
					 <s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#item.creationDate)" nice="true"/>
		 		  </td>
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