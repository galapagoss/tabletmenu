<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table cellspacing="0" cellpadding="0" border="0" id="paging-table">
 <tbody>
 <tr>
  <td>
   <s:if test="#attr.page.existPrevPage">
    <a class="page-button page-far-left" onclick="pager(1)"></a>
    <a class="page-button page-left" onclick="pager(<s:property value="#attr.page.page-1"/>)"></a>
   </s:if>
   <s:else>
    <a class="page-button page-far-left page-button-disabled"></a>
    <a class="page-button page-left page-button-disabled"></a>
   </s:else>
	 <div id="page-info"><s:text name="table.pager.page"/> <strong><s:property value="#attr.page.page"/></strong> / <s:property value="#attr.page.numPages"/></div>
	 <s:if test="#attr.page.existNextPage">
    <a class="page-button page-far-right" onclick="pager(<s:property value="#attr.page.page+1"/>)"></a>
    <a class="page-button page-right" onclick="pager(<s:property value="#attr.page.numPages"/>)"></a>
   </s:if>
   <s:else>
    <a class="page-button page-far-right page-button-disabled"></a>
    <a class="page-button page-right page-button-disabled"></a>
   </s:else>
  </td>
	<td>
	 <div class="pageFooter-items">
	  <div class="items4page"><s:text name="table.pager.items4page"/></div>
	  <div>
		 <select name="page.itemsForPage" class="styledselect_pages" onchange="formSubmit()">
		  <s:iterator var="row" value="@com.oxybay.web.resources.keys.CustomKeys@TABLE_ROWSTOVIEW">
		   <option value="<s:property value="#row"/>" <s:if test="#attr.page.itemsForPage==#row">selected="true"</s:if>><s:property value="#row"/></option>
		  </s:iterator>
		 </select>
		 </div>
	 </div>
	</td>
 </tr>
 </tbody>
</table>