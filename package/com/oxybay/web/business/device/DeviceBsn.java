package com.oxybay.web.business.device;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.RandomStringUtils;

import com.oxybay.web.beans.menu.device.DeviceBean;
import com.oxybay.web.beans.menu.device.api.DeviceBindBean;
import com.oxybay.web.beans.menu.version.update.MenuUpdateBean;
import com.oxybay.web.beans.menu.version.update.MenuUpdateFilesBean;
import com.oxybay.web.beans.menu.version.MenuVersionBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;
import com.oxybay.web.resources.keys.APIKeys;
import com.oxybay.web.resources.keys.APIKeys.Actions;
import com.oxybay.web.resources.utils.Utils;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 *
 */
public class DeviceBsn extends RootBsn {

	public DeviceBsn() {
		super();
	}
	
	public DeviceBsn(BsnDataInfo data) {
		super(data);
	}
	
	/**
	 * Generate Unlock Key
	 * @return
	 */
	private String generateUnlockKey() {
		String[] casePermitted = {"245","14563","256","12587","12347896","32589","458","74569","856"};
		int old = (int)(Math.random()*9)+1;
		String res = ""+old;
		int count = 0;
		while(res.length()<4 && count<500) {
			count++;
			char elem = casePermitted[old-1].charAt((int)(Math.random()*casePermitted[old-1].length()));
			if (!res.contains(""+elem)) {
				res += elem;
				casePermitted[old-1] = casePermitted[old-1].replace(""+elem, "");
				try{old = Integer.parseInt(""+elem);} catch(Exception e) {}
			}
		}
		return res;
	}


	/**
	 * Generate New Device Domain Key
	 * @param idDomain
	 * @return
	 */
	public boolean generateNewDeviceKey(DomainBean domain) {
		boolean res = false;
		String bindKey = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
		String unlockKey = generateUnlockKey();
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("UPDATE domain SET device_key=?,unlock_key=? WHERE _id=?");
			ps.setString(1, bindKey);
			ps.setString(2, unlockKey);
			ps.setInt(3,domain.getId());
			if (ps.executeUpdate()>0) {
				domain.setBindKey(bindKey);
				domain.setUnlockKey(unlockKey);
				res = true;
			}
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return res;
	}
	
	
	/**
	 * Get Domain Bind Specifics
	 * @param label
	 * @param key
	 * @return
	 */
	public DeviceBindBean bindDevice(String label, String key, String deviceID) {
		if (deviceID==null || deviceID.trim().equals(""))
			return null;
		DeviceBindBean res = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT a._id,m._id as menuid,md5_key,b.deleted " +
																						"FROM domain a " +
																						"INNER JOIN menu m ON m.domain=a._id AND m.active=? AND m.deleted IS NULL " +
																						"LEFT OUTER JOIN device b ON a._id=b.domain AND b.device_id=? " +
																						"WHERE a.device_key=? AND a.label=? " +
																						"ORDER BY m._id DESC LIMIT 1 ");
			ps.setBoolean(1, true);
			ps.setString(2, deviceID);
			ps.setString(3, key);
			ps.setString(4, label);

			rs = ps.executeQuery();
			if (rs.next()) {
				res = new DeviceBindBean();
				res.setDomain(rs.getInt("_id"));
				res.setMenu(rs.getInt("menuid"));
				res.setKey(rs.getString("md5_key"));
				if (rs.getTimestamp("deleted")!=null)
					reActiveDevice(deviceID);
				else if (res.keyInvalid())
					res.setKey(bindDeviceKey(res.getDomain(), deviceID));
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		debug("res:"+res);
		return res;
	}

	/**
	 * Get MD5 Key for device
	 * @param deviceID
	 * @return
	 */
	public String getMD5DeviceKey(String deviceID) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT md5_key FROM device WHERE device_id=? AND deleted IS NULL LIMIT 1");
			ps.setString(1, deviceID);

			rs = ps.executeQuery();
			if (rs.next()) 
				return rs.getString("md5_key");
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return "";
	}
	
	/**
	 * Bind Device and return md5 key
	 * @param idDomain
	 * @param deviceId
	 * @return
	 */
	private String bindDeviceKey(int idDomain, String deviceId) {
		String key = RandomStringUtils.randomAlphanumeric(10);
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("INSERT INTO device (domain,device_id,creation_date,last_update,md5_key) values (?,?,UTC_TIMESTAMP(),UTC_TIMESTAMP(),?)");
			ps.setInt(1,idDomain);
			ps.setString(2, deviceId);
			ps.setString(3, key);
			if (ps.executeUpdate()>0) 
				return key;
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return "";
	}
	
	/**
	 * Log Device Action
	 * @param deviceId
	 * @param action
	 * @param custom
	 * @param success
	 * @return
	 */
	public long logAction(String deviceId, Actions action, String custom, boolean success) {
		long res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("INSERT INTO device_action (id_device,action,action_date,custom_data,success) " +
																						"SELECT id,?,UTC_TIMESTAMP(),?,? FROM device WHERE device_id=? LIMIT 1 ", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1,action.getCode());
			ps.setString(2, custom==null ? "" : custom);
			ps.setBoolean(3, success);
			ps.setString(4, deviceId);
			if(ps.executeUpdate()>=0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) 
					res = rs.getLong(1);
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Save last version update 
	 * @param deviceId
	 * @param idMenu
	 * @param version
	 * @return
	 */
	public boolean setLastVersionUpdate(String deviceId, int idMenu, int version) {
		boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("UPDATE device SET version=(SELECT _id FROM menu_version WHERE id_menu=? AND version=?),last_update=UTC_TIMESTAMP() WHERE device_id=?");
			ps.setInt(1,idMenu);
			ps.setInt(2, version);
			ps.setString(3, deviceId);
			res = (ps.executeUpdate()>0);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * UnBind Device
	 * @param deviceId
	 * @return
	 */
	public boolean unbindDevice(String deviceId) {
		boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("UPDATE device SET deleted=UTC_TIMESTAMP() WHERE device_id=?");
			ps.setString(1, deviceId);
			res = (ps.executeUpdate()>0);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Reactive Device
	 * @param deviceId
	 * @return
	 */
	public boolean reActiveDevice(String deviceId) {
		boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("UPDATE device SET deleted=NULL WHERE device_id=?");
			ps.setString(1, deviceId);
			res = (ps.executeUpdate()>0);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Check API MD5 control
	 * @param deviceId
	 * @param callAction
	 * @param hash
	 * @param idDomain
	 * @return -1 if correct otherwise the last action id
	 */
	public long checkAPImd5(String deviceId, long callAction, String hash, int idDomain) {
		long lastAction = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT a.md5_key,(SELECT id FROM device_action WHERE id_device=a.id AND action<>? ORDER BY id DESC LIMIT 1) as last_action " +
																						"FROM device a WHERE a.device_id=? AND (a.domain=? OR ?) AND a.deleted IS NULL");
			ps.setInt(1, Actions.DOWNLOAD.getCode());
			ps.setString(2, deviceId);
			ps.setInt(3, idDomain);
			ps.setBoolean(4, idDomain==0);
			rs = ps.executeQuery();
			if (rs.next()) {
				String md5 = rs.getString("md5_key");
				lastAction = rs.getLong("last_action");
				//debug("Last Action:"+lastAction);
				//debug("Hash1:"+hash);
				//debug("Hash2:"+Utils.getMD5Crypt(APIKeys.PREFIX_KEY+lastAction+deviceId+lastAction+md5));
				return (Utils.getMD5Crypt(APIKeys.PREFIX_KEY+lastAction+deviceId+lastAction+md5).equals(hash)) ? APIKeys.MD5CHECK_PASSED : lastAction;
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return lastAction;
	}
	
	
	/**
	 * 
	 * @param domainId
	 * @return
	 */
	public List<DeviceBean> getDomainDevice(int domainId) {
		List<DeviceBean> devices = new ArrayList<DeviceBean>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT id, device_id " +
																"FROM device " +
																"WHERE domain = ? AND deleted IS NULL");
			ps.setInt(1,domainId);

			rs = ps.executeQuery();
			while (rs.next()) {
				DeviceBean device = new DeviceBean();
				device.setId(rs.getInt("id"));
				device.setIdDomain(domainId);
				device.setDeviceId(rs.getString("device_id"));
				devices.add(device);
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return devices;
	}
	
	
	/**
	 * Check Update Version
	 * @param idDomain
	 * @param idMenu
	 * @param fromVersion
	 * @return
	 */
	public MenuUpdateBean getUpdate(String idDevice, int idMenu, int fromVersion, String baseUrl) {
		MenuUpdateBean update = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT a.domain,b.version,b.file_sql,b.file_sql_size,b.file_zip,b.file_zip_size,b.file_op,b.file_op_size " +
																						"FROM device a " +
																						"INNER JOIN menu_version b ON a.domain=b.id_domain AND b.version>? AND b.id_menu=? " +
																						"WHERE device_id=? AND a.deleted IS NULL ORDER BY b.version");
			ps.setInt(1,fromVersion);
			ps.setInt(2, idMenu);
			ps.setString(3, idDevice);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (update==null)
					update = new MenuUpdateBean();
				update.setVersion(rs.getInt("version"));
				final String pathUrl = baseUrl+MenuVersionBean.getBaseUrl(rs.getInt("domain"), update.getVersion()); 
				update.setSqlFile(pathUrl+rs.getString("file_sql"));
				update.setSqlFileSize(rs.getLong("file_sql_size"));
				final String zip = rs.getString("file_zip");
				final String op = rs.getString("file_op");
				update.getFiles().add(new MenuUpdateFilesBean((zip!=null ? pathUrl+zip : null), rs.getLong("file_zip_size"), (op!=null ? pathUrl+op : null), rs.getLong("file_op_size")));
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return update;
	}
}
