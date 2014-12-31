/**
 * MenuVersionBsn.java
 * 26/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import com.oxybay.web.beans.menu.version.MenuVersionDiffBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class MenuVersionBsn extends RootBsn {
	
	public static final String DATE_DEFAULT_START = "1000/01/01";

	/**
	 * @param data
	 */
	public MenuVersionBsn(BsnDataInfo data) {
		super(data);
	}

	/**
	 * 
	 */
	public MenuVersionBsn() {
		super();
	}
	
	
	/**
	 * Check Updates to deploy
	 * @param idDomain
	 * @return
	 */
	public MenuVersionDiffBean checkUpdates(int idDomain, int idMenu, Date start) {
		MenuVersionDiffBean diff = new MenuVersionDiffBean();
		// Languages
		diff.setLangUpdates(checkUpdLanguages(idMenu, idDomain, start));
		// Attachments
		diff.setAttachUpdates(checkUpdAttach(idDomain, start));
		// Category
		diff.setCatUpdates(checkUpdCategory(idMenu, start));
		// Category Translations
		diff.setCatTransUpdates(checkUpdCategoryTranslations(idMenu, start));
		// Menu
		diff.setMenuUpdates(checkUpdMenu(idDomain, start));
		// Product
		diff.setProdUpdates(checkUpdProduct(idMenu, start));
		// Product Translactions
		diff.setProdTransUpdates(checkUpdProductTranslations(idMenu, start));
		return diff;
	}

	/**
	 * Check attach updates
	 * @param idDomain
	 * @param start
	 * @return
	 */
	private int checkUpdAttach(int idDomain, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as domain_version, ? as date_version) a " +
																						"INNER JOIN attachment b " +
																						"WHERE b.id_domain=a.domain_version " +
																						"      AND (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idDomain);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Check Categories Updatez
	 * @param idMenu
	 * @param start
	 * @return
	 */
	private int checkUpdCategory(int idMenu, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as menu_version, ? as date_version) a " +
																						"INNER JOIN category b " +
																						"WHERE b.id_menu=a.menu_version " +
																						"      AND (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idMenu);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Check Categories Translations updates
	 * @param idMenu
	 * @param start
	 * @return
	 */
	private int checkUpdCategoryTranslations(int idMenu, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as menu_version, ? as date_version) a " +
																						"INNER JOIN category c ON c.id_menu=a.menu_version " +
																						"INNER JOIN category_2_language b ON c._id=b.id_category " +
																						"WHERE (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idMenu);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	/**
	 * Check Menu Updates
	 * @param idDomain
	 * @param start
	 * @return
	 */
	private int checkUpdMenu(int idDomain, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as domain_version, ? as date_version) a " +
																						"INNER JOIN menu b " +
																						"WHERE b.domain=a.domain_version " +
																						"      AND (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idDomain);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Check Product Updates
	 * @param idMenu
	 * @param start
	 * @return
	 */
	private int checkUpdProduct(int idMenu, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as menu_version, ? as date_version) a " +
																						"INNER JOIN product b " +
																						"WHERE b.id_menu=a.menu_version " +
																						"      AND (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idMenu);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Check Product Translations updates
	 * @param idMenu
	 * @param start
	 * @return
	 */
	private int checkUpdProductTranslations(int idMenu, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as menu_version, ? as date_version) a " +
																						"INNER JOIN product c ON c.id_menu=a.menu_version " +
																						"INNER JOIN product_2_language b ON c._id=b.id_product " +
																						"WHERE (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idMenu);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Check Languages Update
	 * @param idMenu
	 * @param idDomain
	 * @param start
	 * @return
	 */
	private int checkUpdLanguages(int idMenu, int idDomain, Date start) {	
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT COUNT(*) FROM (SELECT ? as menu_version, ? as date_version) a " +
																						"INNER JOIN ( " +
																						" SELECT IF(b1.creation_date IS NULL OR a1.creation_date>b1.creation_date,a1.creation_date,b1.creation_date)as creation_date,update_date,(NULL)as deleted " +
																						" FROM menu_2_language a1 " +
																						" LEFT OUTER JOIN language_label_custom b1 ON b1.domain=? AND b1.lang=a1.lang " +
																						" WHERE a1.id_menu=? AND a1.deleted IS NULL " +
	// TODO	ERO ARRIVATO QUI
																						") b " +
																						"WHERE (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ");
			ps.setInt(1,idMenu);
			if (start==null) 	ps.setString(2, DATE_DEFAULT_START); 	else ps.setTimestamp(2, new Timestamp(start.getTime()));
			ps.setInt(3, idDomain);
			ps.setInt(4,idMenu);
			rs = ps.executeQuery();
			if (rs.next()) 
				res = rs.getInt(1);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
}
