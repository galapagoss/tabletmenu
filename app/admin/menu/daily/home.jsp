<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/tableForm.js"/>"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('TD.daily-item').hover(function () { $(this).addClass('select'); }, function () {$(this).removeClass('select');});
		});
	</script>


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
  <div id="page-heading"><h1><s:text name="menu.daily.title"/></h1></div>
  <%@include file="../../include/table/tableStart.jsp"  %>
		
		
		 <div id="content-table-inner">
		  <div id="table-content">
			
			 
			  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="product-table">
				 <tbody>
				 <tr class="nodrop nodrag">
					<th class="table-header-repeat line-left daily-type"></th>
					<s:iterator begin="0" end="6" var="day">
					 <th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="common.weekday.long.%{#day}"/></a>	</th>
					</s:iterator>
				</tr>
				<s:set var="curType" value="-1"/>
				<s:iterator var="item" value="#attr.page.items">
				 <s:if test="#curType!=#item.dayType">
				  <s:if test="#curType!=-1">
				   </tr>
				  </s:if>
				  <tr>
				   <td class="daily-item-type"><s:text name="daily.day.type.%{#item.dayType}"/></td>
				  <s:set var="curType" value="#item.dayType"/>
				 </s:if>
					<td class="daily-item">
					 <a class="fancyDetail" title="<s:text name="table.form.actions.edit"/>" href="<s:url action="%{#attr.actionname}"><s:param name="dispatch" value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_EDIT"/><s:param name="bean.id" value="#item.id"/></s:url>">
					  <div class="table-icon value-<s:property value="#item.active"/>"></div>
					  <div class="daily-price"><s:property value="#attr.menu.currency.format(#item.price,true)" escapeHtml="false" /></div>
					 </a> 
					</td>
				</s:iterator>
				<s:if test="#curType!=-1">
				 </tr>
			  </s:if>
				</tbody>
			 </table>
		 </div>
			
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