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
		$("A.others-languages-tab").click(function(){
			$(this).next(".others-languages").fadeIn("slow");
			$(this).next(".others-languages").expose({opacity: 0.6,color:'#000',onClose: function(){$(".others-languages").fadeOut();}});
		});
		$('.fancyDetail').fancybox({
			'padding'		: 0,
			'openEffect'	: 'elastic',
			'closeEffect'	: 'elastic',
			'width'		: 1000,
			'helpers' : { overlay: { closeClick: false }, title : null},      
			'closeClick':true,
			'type'			: 'iframe',
			'beforeLoad': function(){$.mask.close();}
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
		  <form action="<s:url action="%{#attr.actionname}" method="%{@com.oxybay.web.resources.keys.AdminActionKeys@ACTION_METHODSUBMIT}"><s:param name="bean.id" value="#attr.bean.id"/></s:url>" id="mainForm" name="tableForm" method="post">
		   
		   <div id="page-heading"><h1><s:text name="menu.manager.title"/></h1></div>
		   
		   <%@include file="../../include/table/formStart.jsp"  %>
		   
		   <tr>
		    <th valign="top"><label for="title"><s:text name="menu.form.title"/></label></th>
				<td>
				 <input type="text" id="title" class="inp-form-large formTip" name="bean.title" value="<s:property value="#attr.bean.title"/>" titleMsg="<s:text name="menu.form.help.title"/>">
				 <div class="helpinfo" for="title"></div>
				</td>
			 </tr>
			 <tr>
		    <th valign="top"><label for="currency"><s:text name="menu.form.currency"/></label></th>
				<td>
				 <select name="bean.currency.id" id="currency" class="formTip" titleMsg="<s:text name="menu.form.help.currency"/>" style="width:130px;">
			    <s:iterator var="item" value="#attr.currencies">
			     <option title="<s:url value="html/images/flags/%{#item.label}.png"/>" value="<s:property value="#item.id"/>" <s:if test="#attr.bean.currency.id==#item.id">selected="true"</s:if>><s:text name="menu.form.currency.%{#item.label}"/> (<s:property value="#item.symbolHTML" escapeHtml="false"/>)</option>
			    </s:iterator>
			   </select>
			   <div class="helpinfo" for="currency"></div>
				</td>
			 </tr>
			 <tr>
		    <th valign="top"><label for="mainLanguage"><s:text name="menu.form.mainLanguage"/></label></th>
				<td>
				 <select name="bean.defaultLanguage" id="mainLanguage" class="formTip" titleMsg="<s:text name="menu.form.help.mainLanguage"/>" style="width:200px;">
				  <s:iterator var="item" value="#attr.languages">
			     <option title="<s:url value="html/images/flags/%{#item.label}.png"/>" value="<s:property value="#item.label"/>" <s:if test="#attr.bean.defaultLanguage==#item.label">selected="true"</s:if>> <s:property value="#item.description"/></option>
			    </s:iterator>
			   </select>
			   <div class="helpinfo" for="mainLanguage"></div>
				</td>
			 </tr>
			 <tr>
		    <th valign="top"><label for="filelogo"><s:text name="menu.form.logo"/></label></th>
				<td>
				 <input type="file" id="filelogo" class="formTip" name="bean.logo.datafile" value="" titleMsg="<s:text name="menu.form.help.logo"/>">
				 <input type="hidden" name="bean.logo.id" value="<s:property value="#attr.bean.logo.id"/>"/>
				 <input type="hidden" name="bean.logo.path" value="<s:property value="#attr.bean.logo.path"/>"/>
				 <input type="hidden" name="bean.logo.filename" value="<s:property value="#attr.bean.logo.filename"/>"/>
				 <div class="helpinfo" for="filelogo"></div>
				 <s:if test="#attr.bean.logo.id>0">
					 <div class="current-file">
					  <div class="current-file-text"><s:text name="common.upload.currentFile"/> <a href="<s:url value="%{#attr.bean.logo.getUrl()}"/>" class="fancyImage"><s:property value="#attr.bean.logo.filename"/></a></div> 
					  <div class="file-delredo" rel="bean.logo.id"></div>
					 </div>
				 </s:if>
				</td>
			 </tr>
			 <tr>
		    <th valign="top"><label for="texts" id="texts" titleMsg="<s:text name="menu.form.help.texts"/>"><s:text name="menu.form.texts"/></label></th>
				<td>
				 <div style="float:left">
				  <s:set name="otherLangs" value="false"/>
					 <s:iterator var="lang" value="#attr.languages">
					  <s:if test="!#lang.mainLanguage && !#lang.menuLanguage">
					   <s:if test="!#otherLangs">
					   <s:set name="otherLangs" value="true"/>
					    <a class="others-languages-tab"><s:text name="menu.lang.other"/></a>
					     <div class="others-languages">
					   </s:if>
					  </s:if>
					  <a class="fancyDetail" href="<s:url action="menuTextLang"><s:param name="bean.idMenu" value="#attr.bean.id"/><s:param name="bean.lang" value="#lang.label"/></s:url>">
					   <img id="menutext-<s:property value="#lang.label"/>" class="flag-list <s:if test="#lang.label==#attr.bean.defaultLanguage">mainLang</s:if> <s:if test="!#attr.bean.textLangs.contains(#lang.label)">opaque</s:if>" src="<s:url value="html/images/flags/%{#lang.label}.png"/>" title="<s:property value="#lang.description"/>"/>
					  </a>
					 </s:iterator>
					 <s:if test="#otherLangs"></div></s:if>
				 </div>
				 <div class="helpinfo" for="texts"></div>
				</td>
			 </tr>
			 
			 
			 <!-- Submit -->
			 <tr><td colspan="2" height="5"></td></tr>
		   <tr>
				<th>&nbsp;</th>
				<td valign="top">
					<input type="submit" class="form-submit" value="<s:text name="table.form.submit.edit"/>">
				</td>
			 </tr>
			
			 <%@include file="../../include/table/formEnd.jsp"  %>
		  </form>
		  
		  <div class="clear">&nbsp;</div>
		 </div>
		 <div class="clear">&nbsp;</div>
		</div>


  <div class="clear">&nbsp;</div>
  <%@include file="../../include/footer.jsp"  %>
 
</body>
</html>