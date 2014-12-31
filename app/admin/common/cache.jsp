<%@page import="java.text.NumberFormat"%>
<%@page import="it.dotland.tci.resources.Keys"%>
<%@page import="java.util.Date"%>
<%@page import="it.dotland.tci.utils.Utils"%>
<%@page import="it.dotland.tci.beans.system.Caching.CacheElemBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.dotland.tci.resources.Resources"%>
<%@page import="it.dotland.tci.beans.system.Caching.CacheBean"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<style>
TD, TH { text-align:center;}
</style>
<body>

<p align="center">
<% 
  NumberFormat nf = NumberFormat.getPercentInstance();
  String baseClass = "it.dotland.tci.";
  Resources resources = Resources.getInstance();
  if (resources != null) {
  	CacheBean cache = (CacheBean)resources.getServletContext().getAttribute(Keys.APP_KEY_CACHE);
  	if (cache!=null) {
  		%>
  		
<h1>Cache Monitor</h1>
<table cellspacing="0" cellpadding="3" border="1" width="100%">
<tr>
 <td colspan="6">Hit Rate: <%=nf.format(cache.getHitRate()) %></td>
</tr>
<tr>
 <th colspan="2">Business</th>
 <th colspan="2">Object</th>
 <th colspan="2">Times</th>
</tr>
<tr>
 <th width="30%">Method</th>
 <th width="15%">Params</th>
 <th width="30%">Object</th>
 <th width="10%">Hash</th>
 <th width="10%">Created</th>
 <th width="5%">Expire(sec)</th>
</tr>
<% for(Iterator<CacheElemBean> i = cache.getIterator(); i.hasNext();) { %>
 <% CacheElemBean elem = i.next(); %>
 <tr>
  <td align="left"><%=elem.getKey().replaceAll("("+baseClass+")|(#(.*))","") %></td>
  <td><%=elem.getKey().replaceAll("((.*)#)","") %></td>
  <td align="left"><%=elem.getObject().getClass().toString().replaceAll("((.*)"+baseClass+")","") %></td>
  <td><%=elem.getObject().hashCode() %></td>
  <td nowrap="nowrap"><%=Utils.formatDate(new Date(elem.getReleaseTime()),"HH:mm:ss") %></td>
  <td><%=(int)((elem.getValidTime()-(System.currentTimeMillis()-elem.getReleaseTime()))/1000) %></td>
 </tr>
<% } %>
</table>  		
  		
  		
  		
<% 
  	} else { out.print("CACHE NOT ACTIVE");}
  } else { out.print("RESOURCE NOT INITIALIZED");}
%>
</p>
</body>
</html>