/**
 * UpdateCompleteAction.java
 * 30/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.api;

import com.oxybay.web.beans.menu.device.api.APIDeviceResultBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.resources.keys.APIKeys.Actions;

public class UpdateCompleteAction extends APIDeviceAction {

	private static final long serialVersionUID = 7081832004251992971L;
	
	/* version installed */
	private int version = 0;
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#executeAPI()
	 */
	@Override
	protected APIDeviceResultBean executeAPI() throws Exception {
		DeviceBsn deviceBsn = new DeviceBsn(bsnData);
		return new APIDeviceResultBean(deviceBsn.setLastVersionUpdate(device, menu, version));
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
		return Actions.UPDATECOMPLETE;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#getCustom()
	 */
	@Override
	protected String getCustom() {
		return ""+version;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#checkParams()
	 */
	@Override
	protected boolean checkParams() {
		return version>0;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	
	
}
