<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="../../include/preHeader.jsp"  %>
	
	<script type="text/javascript" src="<s:url value="html/js/form.js" includeParams="none"/>"></script>
	<s:fielderror escape="false" theme="oxybay"/>
	<script type="text/javascript">
	$(document).ready(function(){
		$.oxyFormHelpInit();
		$("#mainForm").oxyProgressSubmit('<s:text name="common.loading"/>','<s:url action="info" namespace="/multipart"/>');
		top.reloadMain = ($(".message-green").length>0);
		$("#mainForm").submit(function(){
			f = document.tableForm;
			str = "";
			for(var i=0; i<f.tags.options.length; i++)
				if (f.tags.options[i].selected && f.tags.options[i].value==0) {
					if (str!="") 
						str+= ",";
					str += f.tags.options[i].text;
					f.tags.options[i].selected = false;
				}
			f.newTags.value = str;
		});
	});
	</script>
	
</head>

<body> 

<div id="content-outer">
 <div id="contentWrap">
  <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
   <input type="hidden" name="bean.id" value="<s:property value="#attr.bean.id"/>"/>
   
   <div id="page-heading"><h1><s:if test="#attr.bean.id>0"><s:text name="table.form.title.edit"/></s:if><s:else><s:text name="table.form.title.new"/></s:else></h1></div>
   
   <%@include file="../../include/table/formStart.jsp"  %>
   
	 <tr>
    <th valign="top"><label for="active"><s:text name="product.form.active"/></label></th>
		<td>
		 <input type="checkbox" name="bean.active" id="active" value="true" class="formTip" titleMsg="<s:text name="product.form.help.active"/>" <s:if test="#attr.bean.active">CHECKED</s:if>/>
		 <div class="helpinfo" for="active"></div>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="price"><s:text name="product.form.price"/></label></th>
		<td>
		 <input type="text" name="bean.price" id="price" value="<s:property value="#attr.bean.price"/>" class="inp-form-mini formTip" titleMsg="<s:text name="product.form.help.price"/>"/>
		 &nbsp; <s:property value="#attr.menu.currency.symbolHTML" escapeHtml="false"/>
		 <div class="helpinfo" for="price"></div>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="categories"><s:text name="product.form.categories"/></label></th>
		<td>
		 <select name="bean.categories" id="categories" class="formTip chosen" multiple="multiple" titleMsg="<s:text name="product.form.help.categories"/>" data-placeholder="<s:text name="product.form.help.categories"/>" data-no_results_text="<s:text name="table.form.select.noItemFound"/>">
		 <s:iterator var="item" value="#attr.categories">
		  <option value="<s:property value="#item.id"/>" <s:if test="#attr.bean.categories.contains(#item.id)">selected="true"</s:if>><s:property value="#item.mainLang.title"/></option>
		 </s:iterator>
		 </select>
		 <div class="helpinfo" for="categories"></div>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="filelogo"><s:text name="product.form.img"/></label></th>
		<td>
		 <input type="file" id="filelogo" class="formTip" name="bean.img.datafile" value="" titleMsg="<s:text name="product.form.help.img"/>">
		 <input type="hidden" name="bean.img.id" value="<s:property value="#attr.bean.img.id"/>"/>
		 <input type="hidden" name="bean.img.path" value="<s:property value="#attr.bean.img.path"/>"/>
		 <input type="hidden" name="bean.img.filename" value="<s:property value="#attr.bean.img.filename"/>"/>
		 <div class="helpinfo" for="filelogo"></div>
		 <s:if test="#attr.bean.img.id>0">
			 <div class="current-file">
			  <div class="current-file-text"><s:text name="common.upload.currentFile"/> <a href="<s:url value="%{#attr.bean.img.getUrl()}"/>" class="fancyImage"><s:property value="#attr.bean.img.filename"/></a></div> 
			  <div class="file-delredo" rel="bean.img.id"></div>
			 </div>
		 </s:if>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="tags"><s:text name="product.form.tags"/></label></th>
		<td>
		 <input type="hidden" name="bean.newTags" id="newTags" value=""/>
		 <select name="bean.tags" id="tags" class="formTip chosen" multiple="multiple" titleMsg="<s:text name="product.form.help.tags"/>" data-placeholder="<s:text name="product.form.help.tags"/>" data-no_results_text="<s:text name="table.form.select.noItemFound"/>" data-add-option="<s:text name="table.form.actions.add"/>">
		 <s:iterator var="item" value="#attr.tags">
		  <option value="<s:property value="#item.id"/>" <s:if test="#attr.bean.tags.contains(#item.id)">selected="true"</s:if>><s:property value="#item.label"/></option>
		 </s:iterator>
		 </select>
		 <div class="helpinfo" for="tags"></div>
		</td>
	 </tr>
	 <!-- Discounts -->
	 <%@include file="../../include/table/discounts.jsp" %>
	 <s:iterator var="item" value="#attr.bean.discounts.policies">
	  <script type="text/javascript">
		 addRow(<s:property value="#item.id"/>,
				 		<s:property value="#item.recurring"/>,
				 		'<s:date name="#item.fromTime" format="HH:mm"/>',
				 		'<s:date name="#item.toTime" format="HH:mm"/>',
				 		<s:property value="#item.fixedPrice"/>,
				 		<s:property value="#item.discountPrice"/>,
				 		<s:property value="#item.active"/>);
	  </script>
	 </s:iterator>
	 
	 <!-- Submit -->
	 <tr><td colspan="2" height="5"></td></tr>
   <tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="submit" class="form-submit" value="<s:if test="#attr.bean.id>0"><s:text name="table.form.submit.edit"/></s:if><s:else><s:text name="table.form.submit.new"/></s:else>"/>
		</td>
	 </tr>
	
	 <%@include file="../../include/table/formEnd.jsp"  %>
  </form>
  
  <div class="clear">&nbsp;</div>
 </div>
 <div class="clear">&nbsp;</div>
</div>

</body>
</html>