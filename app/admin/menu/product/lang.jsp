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
		nicConf = {
				iconsPath : '<s:url value="html/images/js/nicEditor/nicEditorIcons.gif"/>',
				buttonList : ['bold','italic','underline','justify','ol','ul','fontSize','fontFamily',]
		}
		new nicEditor(nicConf).panelInstance('subtitle');
		new nicEditor(nicConf).panelInstance('body');
		$.oxyFormHelpInit();
		if ($(".message-green").length>0) {
			top.$("#<s:property value="#attr.bean.idProduct"/>-<s:property value="#attr.bean.label"/>").removeClass("opaque");
			if (top.$("#<s:property value="#attr.bean.idProduct"/>-<s:property value="#attr.bean.label"/>.mainLang").length>0)
				top.$("TR#<s:property value="#attr.bean.idProduct"/> .mainLangTitle").html("<s:property value="#attr.bean.title"/>");
		}
	});
	function deleteItem() {
		if(confirm("<s:text name="table.form.confirm.delete"/>")){
			document.tableForm['dispatch'].value = "<s:property value="@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_DELETE"/>";
			document.tableForm.submit();
		}
	}
	</script>
	
</head>

<body> 


<div id="content-outer">

 <div id="contentWrap">
  <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"/>" id="mainForm" name="tableForm" method="post">
   <input type="hidden" name="bean.id" value="<s:property value="#attr.bean.id"/>"/>
   <input type="hidden" name="bean.idProduct" value="<s:property value="#attr.bean.idProduct"/>"/>
   <input type="hidden" name="bean.label" value="<s:property value="#attr.bean.label"/>"/>
   
   <div id="page-heading"><h1><s:if test="#attr.bean.id>0"><s:text name="table.form.title.edit"/></s:if><s:else><s:text name="table.form.title.new"/></s:else></h1></div>
   
   <s:if test="#attr.bean.deleted != null">
    <div class="box-tab">
		 <div class="redright"></div><div class="redleft"><s:text name="table.form.deleted"/> <s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#attr.bean.deleted)" nice="true"/></div>
		</div>
   </s:if>
   
   <%@include file="../../include/table/formStart.jsp"  %>
   
   <tr>
    <th valign="top"><label for="lang"><s:text name="product.lang.form.lang"/></label></th>
		<td>
		 <img src="<s:url value="html/images/flags/%{#attr.bean.label}.png"/>" title="<s:property value="#attr,bean.label"/>" alt="<s:property value="#attr.bean.label"/>"/>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="title"><s:text name="product.lang.form.title"/></label></th>
		<td>
		 <input type="text" id="title" class="inp-form formTip" name="bean.title" value="<s:property value="#attr.bean.title"/>" titleMsg="<s:text name="product.lang.form.help.title"/>">
		</td>
	 </tr>
	 <tr>
	  <th valign="top"><label for="subtitle"><s:text name="product.lang.form.subtitle"/></label></th>
	 <td>
		<textarea id="subtitle" class="inp-form formTip" name="bean.subtitle" titleMsg="<s:text name="product.lang.form.help.subtitle"/>"><s:property value="#attr.bean.subtitle"/></textarea>
	 </td>
	 </tr>
	 <tr>
	   <th valign="top"><label for="body"><s:text name="product.lang.form.body"/></label></th>
		<td>
		 <textarea id="body" class="inp-form formTip" name="bean.body" titleMsg="<s:text name="product.lang.form.help.body"/>"><s:property value="#attr.bean.body"/></textarea>
		</td>
	 </tr>
	 
	 
	 <!-- Submit -->
	 <tr><td colspan="2" height="5"></td></tr>
   <tr>
		<th>&nbsp;</th>
		<td valign="top">
			<input type="submit" class="form-submit" value="<s:if test="#attr.bean.id>0 && #attr.bean.deleted==null"><s:text name="table.form.submit.edit"/></s:if><s:else><s:text name="table.form.submit.new"/></s:else>"/>
			<s:if test="#attr.bean.deleted==null && #attr.bean.id>0">
			 <input type="button" class="form-reset" value="<s:text name="table.form.delete"/>" onclick="deleteItem()"/>
			</s:if>
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