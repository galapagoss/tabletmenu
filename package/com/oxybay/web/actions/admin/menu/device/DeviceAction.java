/**
 * DeviceAction.java
 * 27/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.device;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.device.DeviceBean;
import com.oxybay.web.beans.menu.device.DeviceFilterBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.business.system.table.TableBsn;

public class DeviceAction extends AdminAction {

	private static final long serialVersionUID = 5807835564932853134L;
	
	private static final Class<DeviceBean> BEANCLASS	= DeviceBean.class;
	
	/* devices filter */
	private DeviceFilterBean page = new DeviceFilterBean();

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		page = sessionFilter(page);
		page.setDomainValue(userAccount.getDomain());
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals("generateKey"))
			generateNewKey();
		
		return list();
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "menu.devices";
	}

	
	/**
	 * Get Item List
	 * @return
	 */
	private String list() throws Exception {
		DomainBean deviceDomain = new DomainBean(userAccount.getDomain().getId());
		TableBsn tableBsn = new TableBsn(bsnData, DomainBean.class);
		tableBsn.loadBean(deviceDomain);
		// If device key is empty
		if (deviceDomain.getBindKey().equals("")) {
			DeviceBsn deviceBsn = new DeviceBsn(bsnData);
			deviceBsn.generateNewDeviceKey(deviceDomain);
		}
		request.setAttribute("deviceDomain", deviceDomain);
		return stdList(page);
	}
	
	/**
	 * Generate New Device Key
	 */
	private void generateNewKey(){
		if (tokenPassed) {
			DeviceBsn deviceBsn = new DeviceBsn(bsnData);
			if (deviceBsn.generateNewDeviceKey(userAccount.getDomain()))
				addActionMessage(getText("device.devicekey.messages.ok.generation"));
			else
				addActionError(getText("device.devicekey.messages.error.generation"));
		}
	}
	
	/**
	 * @return the page
	 */
	public DeviceFilterBean getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(DeviceFilterBean page) {
		this.page = page;
	}

}
