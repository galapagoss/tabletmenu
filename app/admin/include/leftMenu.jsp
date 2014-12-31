<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="sidebar">
  <ul>
   
   <!-- Home -->
   <li class="nosubmenu <s:if test="#attr.selectedMenu=='home'">current</s:if>">
    <a href="<s:url action="home"/>"><span class="menu-icon1 icon-home"></span> <s:text name="leftmenu.home"/></a>
   </li>
   
   <!-- Menu -->
	 <li <s:if test="#attr.selectedMenu.startsWith('menu.')">class="current"</s:if>>
		<a href="#"><span class="menu-icon1 icon-menu"></span> <s:text name="leftmenu.menu"/></a>
		<ul>
		 <li <s:if test="#attr.selectedMenu=='menu.manage'">class="current"</s:if>><a href="<s:url action="menuManager"/>"><span class="menu-icon2 icon-manage"></span> <s:text name="leftmenu.menu.manage"/></a></li>
		 <li <s:if test="#attr.selectedMenu=='menu.versions'">class="current"</s:if>><a href="<s:url action="menuVersion"/>"><span class="menu-icon2 icon-versions"></span> <s:text name="leftmenu.menu.versions"/></a></li>
		 <li <s:if test="#attr.selectedMenu=='menu.devices'">class="current"</s:if>><a href="<s:url action="device"/>"><span class="menu-icon2 icon-devices"></span> <s:text name="leftmenu.menu.devices"/></a></li>
		 <li <s:if test="#attr.selectedMenu=='menu.categories'">class="current"</s:if>><a href="<s:url action="category"/>"><span class="menu-icon2 icon-categories"></span> <s:text name="leftmenu.menu.categories"/></a></li>
		 <li <s:if test="#attr.selectedMenu=='menu.products'">class="current"</s:if>><a href="<s:url action="product"/>"><span class="menu-icon2 icon-products"></span> <s:text name="leftmenu.menu.products"/></a></li>
		 <li <s:if test="#attr.selectedMenu=='menu.daily'">class="current"</s:if>><a href="<s:url action="daily"/>"><span class="menu-icon2 icon-daily"></span> <s:text name="leftmenu.menu.daily"/></a></li>
		 <li <s:if test="#attr.selectedMenu=='menu.tag'">class="current"</s:if>><a href="<s:url action="tag"/>"><span class="menu-icon2 icon-tags"></span> <s:text name="leftmenu.menu.tag"/></a></li>
		</ul>
   </li>  

	 <!-- Settings -->
   <li <s:if test="#attr.selectedMenu.startsWith('settings.')">class="current"</s:if>>
    <a href="#"><span class="menu-icon1 icon-settings"></span> <s:text name="leftmenu.settings"/></a>
    <ul>
     <li <s:if test="#attr.selectedMenu=='settings.users'">class="current"</s:if>><a href="<s:url action="user"/>"><span class="menu-icon2 icon-users"></span><s:text name="leftmenu.settings.users"/></a></li>
     <li <s:if test="#attr.selectedMenu=='settings.languages'">class="current"</s:if>><a href="<s:url action="menuLanguage"/>"><span class="menu-icon2 icon-languages"></span><s:text name="leftmenu.settings.languages"/></a></li>
    </ul>
   </li>
   
   <!-- Tools -->
   <li <s:if test="#attr.selectedMenu.startsWith('tools.')">class="current"</s:if>>
    <a href="#"><span class="menu-icon1 icon-tools"></span> <s:text name="leftmenu.tools"/></a>
    <ul>
     <li <s:if test="#attr.selectedMenu=='tools.cache'">class="current"</s:if>><a href="<s:url action="cacheMonitor"/>"><span class="menu-icon2 icon-cache"></span><s:text name="leftmenu.tools.cache"/></a></li>
     <li <s:if test="#attr.selectedMenu=='tools.connections'">class="current"</s:if>><a href="<s:url action="dbMonitor"/>"><span class="menu-icon2 icon-connections"></span><s:text name="leftmenu.tools.connections"/></a></li>
    </ul>
   </li>
      
   <!-- Help -->
   <li class="nosubmenu <s:if test="#attr.selectedMenu=='help'">current</s:if>">
    <a href="<s:url action="home"/>"><span class="menu-icon1 icon-help"></span> <s:text name="leftmenu.help"/></a>
   </li>

  </ul>
  <a id="menucollapse" href="#collapse">◀ <s:text name="leftmenu.collapse"/></a>
</div>