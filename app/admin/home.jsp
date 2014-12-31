<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:useBean id="userAccount" type="com.oxybay.web.beans.profile.UserBean" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="com.oxybay.web.beans.profile.common.Permission"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="include/preHeader.jsp"  %>
</head>

<body> 
<!--  start nav-outer-repeat................................................................................................. START -->
<%@include file="include/topMenu.jsp"  %>
<!--  start nav-outer-repeat................................................... END -->

  <div class="clear"></div>
 
<!-- start content-outer ........................................................................................................................START -->
<div id="content-outer">

<%@include file="include/leftMenu.jsp"  %>


<!-- start content -->
<div id="content">

	<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
	<tr>
		<th rowspan="3" class="sized"><img src="html/images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
		<th class="topleft"></th>
		<td id="tbl-border-top">&nbsp;</td>
		<th class="topright"></th>
		<th rowspan="3" class="sized"><img src="html/images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
	</tr>
	<tr>
		<td id="tbl-border-left"></td>
		<td>
		<!--  start content-table-inner ...................................................................... START -->
		<div id="content-table-inner">
		
			<!--  start table-content  -->
			<div id="table-content">
			<h2>USER</h2>
			
			<b>ID</b>: <%=userAccount.getId() %> <br/>
			<b>Email</b>: <%=userAccount.getEmail() %> <br/>
			<b>Nickname</b>: <%=userAccount.getNickname() %> <br/>
			<b>Language</b>: <%=(userAccount.getLanguage()!=null ? userAccount.getLanguage().getLabel() : "null") %> <br/>
			<% if (userAccount.getLanguage()!=null) { %>
			 <blockquote>
			  <b>Desc</b>: <%=userAccount.getLanguage().getDescription() %> <br/>
			 </blockquote>
			<% } %>
			
			<b>Type:</b> <%=userAccount.getType() %> <br/>
			<b>Creation</b>: <%=userAccount.getCreationDate() %> <br/>
			<b>Update</b>: <%=userAccount.getUpdateDate() %> <br/>
			<b>Delete</b>: <%=userAccount.getDeleted() %> <br/><br/>
			
			<h3>Permission</h3>
			<ul>
			<% for (Permission permission : userAccount.getPermission()) { %>
			<li><%= permission %></li>
			<% } %>
			</ul>
			
			
			</div>
			<!--  end table-content  -->
	
			<div class="clear"></div>
		 
		</div>
		<!--  end content-table-inner ............................................END  -->
		</td>
		<td id="tbl-border-right"></td>
	</tr>
	<tr>
		<th class="sized bottomleft"></th>
		<td id="tbl-border-bottom">&nbsp;</td>
		<th class="sized bottomright"></th>
	</tr>
	</table>
	<div class="clear">&nbsp;</div>

</div>
<!--  end content -->
<div class="clear">&nbsp;</div>
</div>
<!--  end content-outer........................................................END -->

<div class="clear">&nbsp;</div>
    
<!-- start footer -->         
<%@include file="include/footer.jsp"  %>
<!-- end footer -->
 
</body>
</html>