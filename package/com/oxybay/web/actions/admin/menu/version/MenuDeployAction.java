/**
 * MenuDeployAction.java
 * 28/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.version;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.menu.device.DeviceBean;
import com.oxybay.web.beans.menu.version.MenuVersionBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.business.device.DeviceUpdater;
import com.oxybay.web.business.menu.MenuDeployBsn;
import com.oxybay.web.resources.keys.CustomKeys;
import com.oxybay.web.resources.utils.Utils;

public class MenuDeployAction extends AdminAction {

	private static final long serialVersionUID = -5849619075207562511L;
	
	public static final String FILE_SQL 			= "deploy.sql";
	public static final String FILE_ZIP 			= "attach.zip";
	public static final String FILE_CMD				= "cmd.txt";

	/* result message */
	private String message = "";
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		
		MenuDeployBsn deployBsn = new MenuDeployBsn(bsnData);
		int version = deployBsn.checkLockDeploy(userAccount.getDomain().getId()); 
		if (version==0) {
			message = getText("menu.deploy.messages.error.locked",new String[]{""+MenuDeployBsn.LOCKING_MINUTES});
			return ERROR;
		}
		
		
		
		try {
			String basePathFiles = bsnData.getResources().getInfo().getBaseDir() + File.separator + 
														 bsnData.getResources().getProperties().getProperty(CustomKeys.CONFIG_PARAM_FILE_FOLDER) + File.separator + 
														 userAccount.getDomain().getId()+ File.separator + 
														 bsnData.getResources().getProperties().getProperty(CustomKeys.CONFIG_PARAM_VERSION_FOLDER)+ File.separator;
			String folderFiles = MenuVersionBean.BASE_FOLDER+version;
			ArrayList<AttachBean> toCreate = new ArrayList<AttachBean>();
			ArrayList<AttachBean> toDel = new ArrayList<AttachBean>();
			String script = "";
			
			/* Remove Refuse Files */
			File baseFolder = new File(basePathFiles+folderFiles);
			if (baseFolder.exists())
				FileUtils.deleteDirectory(baseFolder);
			
			/* Create SQL Script */
			File sql = new File(basePathFiles + folderFiles + File.separator + version+"_"+FILE_SQL); 
			script = "";
			script += deployBsn.dropTables();
			script += deployBsn.createTables();
			script += deployBsn.getDataValues(userAccount.getDomain().getId());
			FileUtils.writeStringToFile(sql, script,"UTF-16");
			
			/* Create ZIP File */
			File zip = null;
			deployBsn.loadUpdatedAttach(userAccount.getDomain().getId(), toCreate, toDel);
			if (!toCreate.isEmpty()) {
				zip = new File(basePathFiles +folderFiles + File.separator + version+"_"+FILE_ZIP);
				ZipArchiveOutputStream zipOut = null;
				try {
					// Output Stream ZIP
					zipOut = new ZipArchiveOutputStream(new FileOutputStream(zip));
					// Loop Attachments
					for(AttachBean attach : toCreate) {
						File file = new File(attach.getRealPath(bsnData.getResources()));
						// Set Entry Tar
						ZipArchiveEntry entry = new ZipArchiveEntry(attach.getPath()+File.separator+attach.getFilename());
						entry.setSize(file.length());
						zipOut.putArchiveEntry(entry);
						// Write Stream for Entry Tar
						byte[] buf = new byte[1024];
						FileInputStream fileIn = null;
						try {
							fileIn = new FileInputStream(file);
							int len;
							while ((len = fileIn.read(buf)) > 0)
								zipOut.write(buf,0,len);
						} finally {
							if (fileIn!=null) {fileIn.close();}
						}
						zipOut.closeArchiveEntry();
					}
				} finally {
					if (zipOut!=null) {zipOut.flush();zipOut.close();}
				}
			}
			
			
			/* Create Shell Command File */
			File cmd = null;
			if (!toDel.isEmpty()) {
				script = "";
				cmd = new File(basePathFiles + folderFiles + File.separator + version+"_"+FILE_CMD);
				for(AttachBean attach : toDel) 
					script += "rm "+attach.getPath()+File.separator+attach.getFilename();
				FileUtils.writeStringToFile(cmd, script);
			}
			
			/* finalyze deploy */
			if (!deployBsn.finalyzeDeploy(version,userAccount.getDomain().getId(), sql, zip, cmd))
				throw new Exception("ERROR TO FINALYZE DEPLOY "+version);
			
			log.info("deploy for domain:"+userAccount.getDomain().getId());
			sendUpdateToDevices(userAccount.getDomain().getId());
			
			message = getText("menu.deploy.messages.ok",new String[]{""+version});
			return SUCCESS;
			
		} catch (Exception e) {
			log.error(Utils.trace(e));
			message = getText("menu.deploy.messages.error");
			return ERROR;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "menu.versions";
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	private void sendUpdateToDevices(int domainId) {
		
		DeviceBsn deviceBsn = new DeviceBsn();
		List<DeviceBean> devices;
		try {
			devices = deviceBsn.getDomainDevice(domainId);
			DeviceUpdater.getInstance().notifyUpdate(devices);
		} finally {
			deviceBsn.destroy();
		}

	}
	
}