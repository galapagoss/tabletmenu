/**
 * DBMonitorAction.java
 * 25/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.system.connections.ConnectionInfo;
import com.oxybay.web.beans.system.connections.ConnectionInfoMonitorBean;
import com.oxybay.web.resources.keys.ConfigKeys;

public class DBMonitorAction extends AdminAction {

	private static final long serialVersionUID = -1169728264189498562L;

	/* connections */
	private List<ConnectionInfoMonitorBean> connections = new ArrayList<ConnectionInfoMonitorBean>();
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		
		for(int i=0; i<ConfigKeys.JDBC_DB_LIST.length; i++) {
			if (resources.getDb().getPools()[i] != null)
				for(Entry<String, ConnectionInfo> item : resources.getDb().getPools()[i].getConnections().entrySet())
					connections.add(new ConnectionInfoMonitorBean(ConfigKeys.JDBC_DB_LIST[i], item.getValue()));
		}
		
		
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "tools.connections";
	}

	/**
	 * @return the connections
	 */
	public List<ConnectionInfoMonitorBean> getConnections() {
		return connections;
	}

	
}
