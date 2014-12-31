<%@page import="com.oxybay.web.beans.profile.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:useBean id="users" type="java.util.ArrayList" scope="request" />

<html>
<body>

<% for(Iterator<UserBean> i = users.iterator(); i.hasNext();) { %>
 <% UserBean user = i.next(); %>
 <b>ID</b>: <%=user.getId() %> <br/>
<b>Email</b>: <%=user.getEmail() %> <br/>
<b>Password</b>: <%=user.getPassword() %> <br/>
<b>Nickname</b>: <%=user.getNickname() %> <br/>
<b>Access Num</b>: <%=user.getAccessNum() %> <br/>
<b>Language</b>: <%=(user.getLanguage()!=null ? user.getLanguage().getLabel() : "null") %> <br/>
<% if (user.getLanguage()!=null) { %>
 <blockquote>
  <b>Flag</b>: <%=user.getLanguage().getFlagImg() %> <br/>
  <b>Desc</b>: <%=user.getLanguage().getDescription() %> <br/>
 </blockquote>
<% } %>
<b>Creation</b>: <%=user.getCreationDate() %> <br/>
<b>Update</b>: <%=user.getUpdateDate() %> <br/>
<b>Delete</b>: <%=user.getDeleted() %> <br/>
 <hr>
<% } %>
 
</body>
</html>