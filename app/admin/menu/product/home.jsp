<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/tableForm.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("SELECT.chosen").chosen({allow_single_deselect: true});
		$("#product-table").tableDnD({
			dragHandle: ".dragHandle", 
			onDrop: function(table, row) {if(!orderChanged){orderChanged=true;addWarning("<s:text name="common.messages.warning.order.save"/>");}}
		});
		$(".dragHandle").hover(function(){$(this).addClass('showDragHandle');}, function(){$(this).removeClass('showDragHandle');});
		$("A.others-languages-tab").click(function(){
			$(this).next(".others-languages").fadeIn("slow");
			$(this).next(".others-languages").expose({opacity: 0.6,color:'#000',onClose: function(){$(".others-languages").fadeOut();}});
		});
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
					<a class="action-item " href="#" onclick="saveOrder('<s:text name="table.form.messages.noOrder"/>','<s:property value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_SAVEORDER" />')">
					 <span class="table-icon icon-save"></span>
					 <span class="action-item-name"><s:text name="table.form.actions.save"/></span>
					</a>
					</div>
					<div id="actions-box-slider-bg2"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!-- end actions-box........... -->
			
			<div id="filter-div">
  		 <div class="filter"><div class="filter-text"><s:text name="table.form.filter.search"/></div><div class="filter-filter"><input type="text" class="inp-form-mini" name="page.label.search" value="<s:property value="#attr.page.label.search"/>"></div></div>
  		 <div class="filter"><div class="filter-text"><s:text name="product.filter.category"/> </div>
  		  <div class="filter-filter">
  		 	<select name="page.idCategory" class="formTip chosen" style="width:200px;" data-placeholder="<s:text name="product.filter.category.help"/>" data-no_results_text="<s:text name="table.form.select.noItemFound"/>">
  		 	 <option value="0"></option>
  		 	 <s:iterator var="item" value="#attr.categories">
  		 	  <option value="<s:property value="#item.id"/>" <s:if test="#item.id==#attr.page.idCategory">selected="true"</s:if>><s:property value="#item.mainLang.title"/></option>
  		 	 </s:iterator>
  		 	</select>
  		 	</div>
  		 </div>
  		 <div id="submit-div"><input type="submit" class="form-submit" value="<s:text name="table.form.filter.submit"/>"></div>
 			</div>
		
		
		 <div id="content-table-inner">
		  <div id="table-content">
			
			 
			  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="product-table">
				 <tbody>
				 <tr class="nodrop nodrag">
					<th class="table-header-check"><a id="toggle-all"></a> </th>
					<th class="table-header-repeat line-left width-warning"><a class="no-ordering"><s:text name="product.list.header.warning"/></a></th>
					<th class="table-header-repeat line-left width-id"><a class="no-ordering"><s:text name="product.list.header.id"/></a></th>
					<th class="table-header-repeat line-left width-order"><a class="no-ordering"><s:text name="product.list.header.order"/></a></th>
					<th class="table-header-repeat line-left width-active"><a class="no-ordering"><s:text name="product.list.header.active"/></a>	</th>
					<th class="table-header-repeat line-left minwidth-1"><a class="no-ordering"><s:text name="product.list.header.title"/></a>	</th>
					<th class="table-header-repeat line-left right-side"><a class="no-ordering"><s:text name="product.list.header.price"/></a>	</th>
					<th class="table-header-repeat line-left "><a class="no-ordering"><s:text name="product.list.header.languages"/></a>	</th>
					<th class="table-header-options line-left"><a class="no-ordering"><s:text name="product.list.header.options"/></a></th>
				</tr>
				<s:iterator var="item" value="#attr.page.items">
				 <tr id="<s:property value="#item.id"/>" order="<s:property value="#item.order"/>">
					<td><input type="checkbox" name="idListVal" class="ui-helper-hidden-accessible" value="<s:property value="#item.id"/>"><span class="ui-checkbox"></span></td>
					<td><div class="table-icon msg-<s:property value="#item.warning"/>" title="<s:text name="product.warning.%{#item.warning}"/>"/></td>
					<td><s:property value="#item.id"/></td>
					<td class="dragHandle">&nbsp;</td>
					<td><div class="table-icon value-<s:property value="#item.active"/>"/></td>
					<td class="mainLangTitle"><s:property value="#item.mainLang.title"/></td>
					<td class="right-side"><s:property value="#attr.menu.currency.format(#item.price,true)" escapeHtml="false" /> </td>
					<td>
					 <s:set name="otherLangs" value="false"/>
					 <s:iterator var="lang" value="#attr.languages">
					  <s:if test="!#lang.mainLanguage && !#lang.menuLanguage">
					   <s:if test="!#otherLangs">
					   <s:set name="otherLangs" value="true"/>
					    <a class="others-languages-tab"><s:text name="category.lang.other"/></a>
					     <div class="others-languages">
					   </s:if>
					  </s:if>
					  <a class="fancyDetail" href="<s:url action="productLang"><s:param name="bean.idProduct" value="#item.id"/><s:param name="bean.label" value="#lang.label"/></s:url>">
					   <img id="<s:property value="#item.id"/>-<s:property value="#lang.label"/>" class="flag-list <s:if test="#lang.label==#item.mainLang.label">mainLang</s:if> <s:if test="!#item.languages.contains(#lang.label)">opaque</s:if>" src="<s:url value="html/images/flags/%{#lang.label}.png"/>" title="<s:property value="#lang.description"/>"/>
					  </a>
					 </s:iterator>
					 <s:if test="#otherLangs"></div></s:if>
					 
					</td>
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