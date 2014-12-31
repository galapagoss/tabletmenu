/**
 * BindAction.java
 * 27/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.api;

import com.oxybay.web.beans.menu.device.api.APIDeviceResultBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.resources.keys.APIKeys;
import com.oxybay.web.resources.keys.APIKeys.Actions;

public class BindAction extends APIDeviceAction {

	private static final long serialVersionUID = -6553843953334374006L;
	
	/* bind key */
	private String key = "";
	
	/* domain label */
	private String label = "";
	/* domain key */
	private String keyLabel = "";
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIAction#executeAPI()
	 */
	@Override
	protected APIDeviceResultBean executeAPI() throws Exception {
		DeviceBsn deviceBsn = new DeviceBsn(bsnData);
		return new APIDeviceResultBean(deviceBsn.bindDevice(label, keyLabel, device));
	}


	
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#checkParams()
	 */
	@Override
	protected boolean checkParams() {
		String[] keys = key.split(APIKeys.BIND_STRING_SEPARATOR);
		if (keys.length==2) {
			label = keys[0];
			keyLabel = keys[1];
			return true;
		}
		return false;
	}





	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIAction#getAction()
	 */
	@Override
	protected Actions getAction() {
		return Actions.BIND;
	}




	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIAction#getCustom()
	 */
	@Override
	protected String getCustom() {
		return "";
	}




	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIAction#checkMD5()
	 */
	@Override
	protected boolean checkMD5() {
		return false;
	}



	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
