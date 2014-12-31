/**
 * CheckUpdateAction.java
 * 28/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.api;

import org.apache.struts2.ServletActionContext;

import com.oxybay.web.beans.menu.device.api.APIDeviceResultBean;
import com.oxybay.web.beans.menu.version.update.MenuUpdateBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.resources.keys.APIKeys;
import com.oxybay.web.resources.keys.APIKeys.Actions;
import com.oxybay.web.resources.utils.Utils;

public class CheckUpdateAction extends APIDeviceAction {
	
	private static final long serialVersionUID = 5045125569114810435L;
	
	/* device version */
	private int version = 0;
	/* custom data */
	private int versionFound = 0;

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#executeAPI()
	 */
	@Override
	protected APIDeviceResultBean executeAPI() throws Exception {
		final String baseUrl = Utils.getBaseURL(request)+ServletActionContext.getServletContext().getContextPath();
		DeviceBsn deviceBsn = new DeviceBsn(bsnData);
		MenuUpdateBean res = deviceBsn.getUpdate(device, menu, version, baseUrl);
		if (res != null)
			versionFound = res.getVersion();
		
		return new APIDeviceResultBean(res);
	}
	
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#checkParams()
	 */
	@Override
	protected boolean checkParams() {
		return menu>0;
	}



	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#checkMD5()
	 */
	@Override
	protected boolean checkMD5() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#getAction()
	 */
	@Override
	protected Actions getAction() {
		return Actions.CHECKUPDATE;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#getCustom()
	 */
	@Override
	protected String getCustom() {
		return version+(versionFound>0 ? APIKeys.CUSTOMDATA_STRING_SEPARATOR+versionFound: "");
	}



	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

}
