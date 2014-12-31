/**
 * Home
 * @author Gaetano
 */
package com.oxybay.web.actions;


import java.util.ArrayList;

import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.beans.profile.UserBeanFilter;
import com.oxybay.web.beans.profile.common.LanguageBean;
import com.oxybay.web.business.system.table.TableBsn;


public class HomeAction extends BaseAction {
	
	private static final long serialVersionUID = 8340153914468376278L;	

	private UserBeanFilter filter = new UserBeanFilter();

	@Override
	public String executeAction() throws Exception {
		
		UserBean user = new UserBean();
		String res = "";
		
		if (dispatch.equals("insert")) {
			user.setEmail("email3");
			user.setNickname("nick3");
			user.setPassword("pwd3");
			user.setLanguage(new LanguageBean("it_IT"));
			TableBsn tableBsn = new TableBsn(bsnData, user.getClass());
			tableBsn.insertBean(user);
			
		} else if (dispatch.equals("update")) {
			user.setId(5);
			user.setEmail("email[MOD]");
			user.setNickname("nick[MOD]");
			user.setPassword("pwd[MOD]");
			user.setLanguage(new LanguageBean("it_IT"));
			TableBsn tableBsn = new TableBsn(bsnData, user.getClass());
			res = ""+tableBsn.updateBean(user);
			
		} else if (dispatch.equals("update2")) {
			user.setId(5);
			user.setEmail("email solo mail");
			user.setNickname("nick solo nick");
			TableBsn tableBsn = new TableBsn(bsnData, user.getClass());
			res = ""+tableBsn.updateBean(user,"email,nickname");
			
		} else if (dispatch.equals("delete")) {
			user.setId(5);
			TableBsn tableBsn = new TableBsn(bsnData, user.getClass());
			res = ""+tableBsn.deleteBean(user);
		
		} else if (dispatch.equals("load")) {
			user.setId(5);
			TableBsn tableBsn = new TableBsn(bsnData, user.getClass());
			tableBsn.loadBean(user);
			
		} else {
			UserBeanFilter filter = new UserBeanFilter();
			filter.getDescription().setSearch("Italiano");
			//TableBsn tableBsn = new TableBsn(bsnData, UserBean.class);
			ArrayList<Object> users = null;//tableBsn.list(filter);
			request.setAttribute("users", users);
			return "home2";
		}
		
		request.setAttribute("res", res);
		request.setAttribute("user", user);
		
		return "home";
	}

	/**
	 * @return the filter
	 */
	public UserBeanFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(UserBeanFilter filter) {
		this.filter = filter;
	}

	@Override
	public boolean canAccess() {
		return true;
	}

	@Override
	public boolean isUserRequired() {
		return false;
	}
	
}
