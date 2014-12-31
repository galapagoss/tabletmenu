<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="nav-outer-repeat"> 
<!--  start nav-outer -->
<div class="nav-outer"> 


		<div id="nav-right">
		  <div class="nav-divider">&nbsp;</div>
		  <div id="nav-domain">
		   <s:if test="#session.userAccount!=null && #session.userAccount.domain!=null">
		    <s:property value="#session.userAccount.domain.description"/>
		   </s:if>
		  </div>
			<div class="nav-divider">&nbsp;</div>
			<div class="showhide-account"><img src="html/images/shared/nav/nav_myaccount.gif" width="93" height="14" alt="" /></div>
			<div class="nav-divider">&nbsp;</div>
			<a href="<s:url action="logout"/>" id="logout"><img src="html/images/shared/nav/nav_logout.gif" width="64" height="14" alt="" /></a>
			<div class="clear">&nbsp;</div>
		
			<!--  start account-content -->	
			<div class="account-content">
			<div class="account-drop-inner">
				<a href="" id="acc-settings">Settings</a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-details">Personal details </a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-project">Project details</a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-inbox">Inbox</a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-stats">Statistics</a> 
			</div>
			</div>
			<!--  end account-content -->
		</div>

</div>
<div class="clear"></div>
<!--  start nav-outer -->
</div>