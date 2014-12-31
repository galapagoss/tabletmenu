<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="div-messages">
<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror escape="false" theme="oxybay"/>
   </div>
</s:if>
<s:if test="hasActionMessages()">
   <div class="messages">
      <s:actionmessage escape="false" theme="oxybay"/>
   </div>
</s:if>
</div>