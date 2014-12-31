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
				buttonList : ['bold','italic','underline','ol','ul','fontSize','fontFamily',]
		}
		new nicEditor(nicConf).panelInstance('presentation');
		new nicEditor(nicConf).panelInstance('footer');
		$.oxyFormHelpInit();
		if ($(".message-green").length>0) {
			top.$("#menutext-<s:property value="#attr.bean.lang"/>").removeClass("opaque");
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
   <input type="hidden" name="bean.idMenu" value="<s:property value="#attr.bean.idMenu"/>"/>
   <input type="hidden" name="bean.lang" value="<s:property value="#attr.bean.lang"/>"/>
   
   <div id="page-heading"><h1><s:if test="#attr.bean.id>0"><s:text name="table.form.title.edit"/></s:if><s:else><s:text name="table.form.title.new"/></s:else></h1></div>
   
   <s:if test="#attr.bean.deleted != null">
    <div class="box-tab">
		 <div class="redright"></div><div class="redleft"><s:text name="table.form.deleted"/> <s:date name="@com.oxybay.web.resources.utils.DateTagFix@fixToNice(#attr.bean.deleted)" nice="true"/></div>
		</div>
   </s:if>
   
   <%@include file="../../include/table/formStart.jsp"  %>
   
   <tr>
    <th valign="top"><label for="lang"><s:text name="menutext.form.lang"/></label></th>
		<td>
		 <img src="<s:url value="html/images/flags/%{#attr.bean.lang}.png"/>" title="<s:property value="#attr.bean.lang"/>" alt="<s:property value="#attr.bean.lang"/>"/>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="title"><s:text name="menutext.form.presentation"/></label></th>
		<td>
		 <div class="textarea-container"><textarea id="presentation" class="inp-form formTip" name="bean.textPresentation" titleMsg="<s:text name="menutext.form.help.presentation"/>"><s:property value="#attr.bean.textPresentation"/></textarea></div>
		 <div class="helpinfo" for="presentation"></div>
		</td>
	 </tr>
	 <tr>
    <th valign="top"><label for="footer"><s:text name="menutext.form.footer"/></label></th>
		<td>
		 <div class="textarea-container"><textarea id="footer" class="inp-form formTip" name="bean.textFooter" titleMsg="<s:text name="menutext.form.help.footer"/>"><s:property value="#attr.bean.textFooter"/></textarea></div>
		 <div class="helpinfo" for="footer"></div>
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