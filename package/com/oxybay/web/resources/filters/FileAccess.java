/**
 * FileAccess.java
 * 30/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;

import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.APIKeys;
import com.oxybay.web.resources.keys.CustomKeys;
import com.oxybay.web.resources.keys.VarKeys;
import com.oxybay.web.resources.keys.APIKeys.Actions;

public class FileAccess implements Filter {

	/* version folder name */
	private String versionFolder = null;
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		RequestFacade req =(RequestFacade)arg0;
		
		String[] pathElems = req.getRequestURI().split("/");
		if (pathElems.length>5) {
			int idDomain = 0;
			try {idDomain = Integer.parseInt(pathElems[3]); } catch(Exception e){}
			
			// User is authenticated
			if (checkSessionAuthentication(req, idDomain)) {
				arg2.doFilter(arg0, arg1);
				return;
			}
			
			// Check if it's an updating file
			if (pathElems[4].equals(getVersionFolder()) && checkMD5(req, idDomain)) {
				traceDownload(req, pathElems[6]);
				arg2.doFilter(arg0, arg1);
				return;
			}
		}
		
		// Page 404 Error
		((ResponseFacade)arg1).sendError(404);
	}
	
	
	/**
	 * Check Session Authentication
	 * @param req
	 * @param idDomain
	 * @return
	 */
	private boolean checkSessionAuthentication(RequestFacade req, int idDomain) {
		UserBean user = (UserBean)req.getSession().getAttribute(VarKeys.SESSION_USERACCOUNT);
		return user!=null && (user.getDomain().getId()==idDomain || user.isSysAdmin());
	}
	
	/**
	 * Check MD5 control
	 * @param req
	 * @param idDomain
	 * @return
	 */
	private boolean checkMD5(RequestFacade req, int idDomain) {
		String device = ""+req.getParameter("device");
		String hash = ""+req.getParameter("hash");
		long call = 0;
		try {call = Long.parseLong(req.getParameter("call"));} catch(Exception e) {};
		DeviceBsn deviceBsn = null;
		try {
			deviceBsn = new DeviceBsn();
			return deviceBsn.checkAPImd5(device, call, hash, idDomain)==APIKeys.MD5CHECK_PASSED;
		} finally {
			if (deviceBsn!=null) deviceBsn.destroy();
		}
	}
	
	
	/**
	 * Trace Download
	 * @param device
	 * @param file
	 */
	private void traceDownload(RequestFacade req, String file) {
		DeviceBsn deviceBsn = null;
		try {
			deviceBsn = new DeviceBsn();
			deviceBsn.logAction(""+req.getParameter("device"), Actions.DOWNLOAD, file, true);
		} finally {
			if (deviceBsn!=null) deviceBsn.destroy();
		}
	}
	
	/**
	 * Get Version Folder
	 * @return
	 */
	private String getVersionFolder() {
		if (versionFolder!=null)
			return versionFolder;
		
		try {versionFolder = Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_VERSION_FOLDER);} catch(Exception e) {}
		return versionFolder==null ? "" : versionFolder;
	}
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {		}
}
