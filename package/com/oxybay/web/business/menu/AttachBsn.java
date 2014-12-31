/**
 * AttachBsn.java
 * 15/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.beans.system.table.FieldBean;
import com.oxybay.web.business.system.common.ImageConvertBsn;
import com.oxybay.web.business.system.common.ImageResize;
import com.oxybay.web.business.system.root.RootBsn;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.CustomKeys;
import com.oxybay.web.resources.utils.SimpleImageInfo;
import com.oxybay.web.resources.utils.Utils;

public class AttachBsn extends RootBsn {
	
	/* folder */
	private String folder = "";
	/* table business */
	private TableBsn tableBsn = null;
	/* list attach prepared */
	private ArrayList<AttachBean> prepared = new ArrayList<AttachBean>();
	/* id domain */
	private int idDomain = 0;
	
	/**
	 * 
	 */
	public AttachBsn(DomainBean domain) throws Exception{
		super();
		init(domain);
	}

	/**
	 * @param data
	 */
	public AttachBsn(BsnDataInfo data, DomainBean domain) throws Exception {
		super(data);
		init(domain);
	}
	
	/**
	 * Init
	 */
	private void init(DomainBean domain) throws Exception{
		this.idDomain = domain.getId();
		this.folder = ""+this.getData().getResources().getProperties().get(CustomKeys.CONFIG_PARAM_FILE_FOLDER)+File.separator+this.idDomain+File.separator+this.getData().getResources().getProperties().get(CustomKeys.CONFIG_PARAM_ATTACH_FOLDER)+File.separator;
		tableBsn = new TableBsn(getData(), AttachBean.class);
	}

	/**
	 * Get Path File
	 * @param folder
	 * @param filename
	 * @return
	 */
	private String getPath(String folderPath, String filename) {
		return getData().getResources().getInfo().getBaseDir() + File.separator + this.folder + File.separator + folderPath + File.separator + filename;
	}

	/**
	 * Prepare File
	 * @param action
	 * @param file
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public boolean prepareFile(ActionSupport action, Object obj, String fieldName){
		AttachBean upload = null;
		ImageResize resizeSpec = null;
		try {
			upload = (AttachBean)FieldBean.getter(fieldName, obj);
			resizeSpec = obj.getClass().getDeclaredField(fieldName).getAnnotation(ImageResize.class);
		} catch(Exception e) {
			traceError(e);
			return false;
		}
		
		// Skip
		if (upload.getId()>=0 && upload.getDatafile()==null)
			return false;
		
		// Manage Old File 
		File oldFile = null;
		String pathOldFile = "";
		if (upload.getId()!=0) {
			AttachBean oldAttach = new AttachBean(Math.abs(upload.getId()));
			try {
				tableBsn.loadBean(oldAttach);
				if (oldAttach==null || oldAttach.getFilename().equals("")) {
					upload.setId(Math.abs(upload.getId()));
					action.addActionError(action.getText("common.upload.error.loadOldVersion",new String[]{fieldName}));
					return false;
				}
				oldFile = new File( getPath(oldAttach.getPath(), oldAttach.getFilename()));
				pathOldFile = oldAttach.getPath();
				
				// If ID is negative delete data from DB
				if (upload.getId()<0) {
					if (tableBsn.deleteBean(oldAttach)>0){
						upload.setId(0);
						deleteFile(oldFile);
						return true;
					} else {
						action.addActionError(action.getText("common.upload.error.delDB",new String[]{fieldName}));
						upload.setId(Math.abs(upload.getId()));
						return false;
					}
				}
					
			} catch (Exception e) {
				traceError(e);
			}
		}
		
		/* Insert/Update new Attach*/
		if (upload.getId()>=0) {
			String attachExt = "";
			int dotPos = upload.getDatafileFileName().lastIndexOf(".");
			if (dotPos>=0)
				attachExt = upload.getDatafileFileName().substring(dotPos+1).toLowerCase();
	
			// Copy File
			String folderFile = Utils.formatDate(new Date(), "yyyyMMdd");
			File dest = new File(getPath(folderFile, upload.getDatafileFileName()));
			try {
				dest = Utils.moveUploadedFile(upload.getDatafile(), dest, false);
			} catch(Exception e) {
				action.addActionError(action.getText(e.getMessage())+" ("+fieldName+")");
				return false;
			}
			
			// Image Info & Resizing
			int w = 0,h = 0;
			if ("|jpg|jpe|jpeg|gif|png|bmp|tiff|".contains("|"+attachExt+"|")) {
				// Resize
				if (resizeSpec!=null) {
					ImageConvertBsn convBsn = new ImageConvertBsn();
					if (!convBsn.imageResize(dest, resizeSpec))
						traceError(new Exception("ERROR to resize ("+resizeSpec.w()+","+resizeSpec.h()+","+resizeSpec.crop()+") image "+dest.getAbsolutePath()));
				}
				
				// Info
				try {
					SimpleImageInfo imageInfo = new SimpleImageInfo(dest);
					w = imageInfo.getWidth();
					h = imageInfo.getHeight();
				} catch(Exception e) {}
			}
			
			// Save on DB
			AttachBean attach = new AttachBean(oldFile!=null ? upload.getId() : 0, this.idDomain, folderFile, dest.getName(), attachExt, w, h, dest.length(), dest);
			int res = 0;
			try {
				if (oldFile==null)
					res = tableBsn.insertBean(attach);
				else
					res = tableBsn.updateBean(attach);
			} catch (Exception e) {
				traceError(e);
			}
			if (res>0) {
				prepared.add(attach);
				// Delete Old Attach Version
				if (oldFile!=null) {
					deleteFile(oldFile);
					traceDeletedFile(idDomain, pathOldFile, oldFile.getName());
				}
			} else {
				action.addActionError(action.getText("common.upload.error.saveDB",new String[]{fieldName}));
				return false;
			}
				
			try {
				FieldBean.setter(fieldName, obj, attach);
			} catch(Exception e) {
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Delete File
	 * @param file
	 */
	private void deleteFile(File file) {
		if (!file.delete())
			file.deleteOnExit();
	}
	
	/**
	 * Trace Deleted File
	 * @param idDomain
	 * @param path
	 * @param filename
	 * @return
	 */
	public boolean traceDeletedFile(int idDomain, String path, String filename){
		boolean	res = false;
		PreparedStatement	ps 	= null;
		try{
			getConnection();
			ps = getConnection().prepareStatement("INSERT INTO attachment_deleted (id_domain,path,filename,deleted) VALUES (?,?,?,UTC_TIMESTAMP())");
			ps.setInt(1, idDomain);
			ps.setString(2, path);
			ps.setString(3, filename);
			res = (ps.executeUpdate()>=0);
		}catch(Exception ex){
			traceQueryError(ps,ex);
		}finally{
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
}
