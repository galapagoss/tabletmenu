/**
 * PasswordAction.java
 * 11/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.profile.user;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.business.profile.UserBsn;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class PasswordAction extends AdminAction {

	private static final long serialVersionUID = 7933298136218327185L;
	private static final Class<UserBean> 			BEANCLASS						= UserBean.class;

	/* old password */
	private String oldPassword = "";
	/* user */
	private UserBean bean = new UserBean();
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.BaseAction#executeAction()
	 */
	@Override
	public String executeAdminAction() throws Exception {
		
		if (bean.getId()<=0)
			return GENERAL_ERROR;
		
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		
		return INPUT;
	}
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "";
	}


	/**
	 * Save password
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		UserBsn userBsn = new UserBsn(bsnData); 
		if(userBsn.checkPassword(bean.getId(), oldPassword)) {
			addActionError(getText("user.pwd.messages.update.error.oldPassword"));
			return INPUT;
		}
		if (mainTable.updateBean(bean, "password")>=0)
			addActionMessage(getText("user.pwd.messages.update.ok"));
		return INPUT;
	}

	/**
	 * @return the bean
	 */
	public UserBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(UserBean bean) {
		this.bean = bean;
	}


	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
}
