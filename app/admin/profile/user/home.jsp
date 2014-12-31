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
					<a class="action-item fancyDetail" href="<s:url action="%{#attr.actionname}"><s:param name="dispatch" value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_CREATE"/></s:url>">
					 <span class="table-icon icon-new"></span>
					 <span class="action-item-name"><s:text name="table.form.actions.new"/></span>
					</a>
					<a class="action-item " href="#" onclick="deleteList(this,'<s:property value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_DELETELIST" />')" nodata="<s:text name="table.form.messages.noSelection"/>" confirm="<s:text name="table.form.confirm.deletelist"/>">
					<span class="table-icon icon-del"></span>
					<span class="action-item-name"><s:text name="table.form.actions.delete"/></span>
					</a>
					</div>
					<div id="actions-box-slider-bg2"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!-- end actions-box........... -->
			
			<div id="filter-div">
  		 <div class="filter"><s:text name="table.form.filter.search"/> &nbsp;<input type="text" class="inp-form-mini" name="page.nickname.search" value="<s:property value="#attr.page.nickname.search"/>"></div>
  		 <div id="submit-div"><input type="submit" class="form-submit" value="<s:text name="table.form.filter.submit"/>"></div>
 			</div>
		
		
		 <div id="content-table-inner">
		  <div id="table-content">
			
			 
			  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="product-table">
				 <tbody>
				 <tr>
					<th class="table-header-check"><a id="toggle-all"></a> </th>
					<th class="table-header-repeat line-left width-id"><a href="#" onclick="order(2)"><s:text name="user.list.header.id"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="user.list.header.active"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a href="#" onclick="order(3)"><s:text name="user.list.header.nick"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a href="#" onclick="order(4)"><s:text name="user.list.header.firstname"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a href="#" onclick="order(5)"><s:text name="user.list.header.lastname"/></a>	</th>
					<th class="table-header-repeat line-left"><a class="no-ordering"><s:text name="user.list.header.lang"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a href="#" onclick="order(1)"><s:text name="user.list.header.registered"/></a>	</th>
					<th class="table-header-options line-left"><a class="no-ordering"><s:text name="user.list.header.options"/></a></th>
				</tr>
				<s:iterator var="item" value="#attr.page.items">
				 <tr>
					<td><input type="checkbox" name="idListVal" class="ui-helper-hidden-accessible" value="<s:property value="#item.id"/>"><span class="ui-checkbox"></span></td>
					<td><s:property value="#item.id"/></td>
					<td><div class="table-icon value-<s:property value="#item.active"/>"/></td>
					<td><s:property value="#item.nickname"/></td>
					<td><s:property value="#item.firstname"/></td>
					<td><s:property value="#item.lastname"/></td>
					<td class="flag">
					 <s:if test="!#item.language.label == ''">
					  <img src="<s:url value="html/images/flags/%{#item.language.label}.png"/>" title="<s:property value="#item.language.description"/>"/>
					 </s:if>
					</td>
					<td>
					 <s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#item.creationDate)" nice="true"/>
		 		  </td>
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