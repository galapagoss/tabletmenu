/**
 * MenuDeployBsn.java
 * 28/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;
import com.oxybay.web.resources.keys.CustomKeys;

public class MenuDeployBsn extends RootBsn {
	
	public static final int 			LOCKING_MINUTES 		= 5;
	public static final String		RN									= "\r\n";
	public static final String 		COLUMN_ROW_COUNTER	= "roworder";
	
	private static final Set<Integer> QUOTES_TYPES = new HashSet<Integer>(Arrays.asList(Types.VARCHAR,Types.TIMESTAMP,Types.TIME,Types.LONGVARCHAR,Types.CHAR,Types.DATE,Types.LONGVARBINARY,Types.VARBINARY)); 

	/**
	 * @param data
	 */
	public MenuDeployBsn(BsnDataInfo data) {
		super(data);
		
	}
	
	
	/**
	 * Start Deploy & Lock
	 * @param idDomain
	 * @return
	 */
	private int startDeploy(int idDomain) {
		int res = 0;
		int newVersion = getNewVersion(idDomain);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("INSERT INTO menu_version(id_menu,id_domain,version,creation_date) " +
																						"SELECT _id,domain,?,UTC_TIMESTAMP() FROM menu WHERE domain=? AND deleted IS NULL AND active=? LIMIT 1");
			ps.setInt(1, newVersion);
			ps.setInt(2, idDomain);
			ps.setBoolean(3, true);
			if (ps.executeUpdate()>0)
				res = newVersion;
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	/**
	 * Get New Id Version
	 * @param idDomain
	 * @return
	 */
	public int getNewVersion(int idDomain) {
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/* Delete old attempts */
			ps = getConnection().prepareStatement("SELECT (COUNT(*)+1) FROM menu_version WHERE id_domain=?");
			ps.setInt(1, idDomain);
			rs = ps.executeQuery();
			if(rs.next())
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
	 * Check if deploy has been locking
	 * @param idDomain
	 * @return
	 */
	public synchronized int checkLockDeploy(int idDomain) {
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/* Delete old attempts */
			ps = getConnection().prepareStatement("DELETE FROM menu_version WHERE creation_date<(UTC_TIMESTAMP()-INTERVAL ? minute) AND deploy_end IS NULL");
			ps.setInt(1, LOCKING_MINUTES);
			ps.executeUpdate();
			ps.close(); ps = null;
			
			ps = getConnection().prepareStatement("SELECT _id FROM menu_version WHERE id_domain=? AND deploy_end IS NULL");
			ps.setInt(1, idDomain);
			rs = ps.executeQuery();
			if(!rs.next())
				res = startDeploy(idDomain);
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Finalyze Deploy
	 * @param idVersion
	 * @param fileSql
	 * @param fileZip
	 * @param fileCmd
	 * @return
	 */
	public boolean finalyzeDeploy(int idVersion, int idDomain, File fileSql, File fileZip, File fileCmd) {
		boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("UPDATE menu_version SET file_sql=?,file_sql_size=?,file_zip=?,file_zip_size=?,file_op=?,file_op_size=?,deploy_end=UTC_TIMESTAMP() WHERE id_domain=? AND version=?");
			ps.setString(1, fileSql.getName());
			ps.setLong(2, fileSql.length());
			if (fileZip==null) {
				ps.setNull(3, Types.VARCHAR);
				ps.setLong(4, 0);
			} else {
				ps.setString(3, fileZip.getName());
				ps.setLong(4, fileZip.length());
			}
			if (fileCmd==null) {
				ps.setNull(5, Types.VARCHAR);
				ps.setLong(6, 0);
			} else {
				ps.setString(5, fileCmd.getName());
				ps.setLong(6, fileCmd.length());
			}
			ps.setInt(7, idDomain);
			ps.setInt(8, idVersion);
			if (ps.executeUpdate()>0) {
				ps.close(); ps = null;
				ps = getConnection().prepareStatement("UPDATE menu SET last_version=(SELECT _id FROM menu_version WHERE id_domain=? AND version=?) WHERE domain=? AND active=? AND deleted IS NULL");
				ps.setInt(1, idDomain);
				ps.setInt(2, idVersion);
				ps.setInt(3, idDomain);
				ps.setBoolean(4, true);
				res = ps.executeUpdate()>0;
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return res;
	}
	

	/**
	 * Backup Tables
	 * @return
	 */
	public String dropTables() throws Exception {
		return "DROP TABLE IF EXISTS daily_menu_2_item;" + RN +
					 "DROP TABLE IF EXISTS daily_menu;" + RN +
					 "DROP TABLE IF EXISTS product_discount;" + RN +
					 "DROP TABLE IF EXISTS product_2_tag;" + RN +
					 "DROP TABLE IF EXISTS product_2_category;" + RN +
					 "DROP TABLE IF EXISTS product_2_language;" + RN +
					 "DROP TABLE IF EXISTS product;" + RN +
					 "DROP TABLE IF EXISTS category_2_language;" + RN +
					 "DROP TABLE IF EXISTS category;" + RN +
					 "DROP TABLE IF EXISTS tag;" + RN +
					 "DROP TABLE IF EXISTS menu;" + RN +
					 "DROP TABLE IF EXISTS attachment;" + RN +
					 "DROP TABLE IF EXISTS currency;" + RN +
					 "DROP TABLE IF EXISTS language;" + RN +
					 "DROP TABLE IF EXISTS domain;" + RN + RN;
	}
	
	/**
	 * Create tables
	 * @param table
	 * @return
	 */
	public String createTables() {
		// Domain
		String res = "CREATE TABLE \"domain\" ( \"_id\" int(10)  NOT NULL , \"label\" varchar(5) NOT NULL, \"description\" varchar(50) NOT NULL DEFAULT '', \"unlock_key\" varchar(32) NOT NULL DEFAULT '', PRIMARY KEY (\"_id\"));"+RN;
		// Language
		res += "CREATE TABLE \"language\" ( \"label\" varchar(5) NOT NULL, \"description\" varchar(50) NOT NULL DEFAULT '', \"_id\" int(10)  NOT NULL , PRIMARY KEY (\"label\"));"+RN;
		// Language Labels
		res += "CREATE TABLE \"language_label\" ( \"lang\" varchar(5) NOT NULL, \"label\" varchar(20) NOT NULL, \"value\" varchar(50) NOT NULL, PRIMARY KEY (\"lang\",\"label\") " +
					 "CONSTRAINT \"FK_language_label1\" FOREIGN KEY (\"lang\") REFERENCES \"language\" (\"label\"));" + RN +
					 "CREATE INDEX \"language_label_FK_language_label1\" ON \"language_label\" (\"lang\");" + RN;
		// Currency
		res += "CREATE TABLE \"currency\" ( \"_id\" int(10)  NOT NULL , \"label\" varchar(5) NOT NULL, \"symbol\" varchar(3) NOT NULL DEFAULT '', \"digit\" int(10)  NOT NULL , \"separator_decimal\" varchar(1) NOT NULL DEFAULT '', \"separator_thousand\" varchar(3) NOT NULL DEFAULT '', PRIMARY KEY (\"_id\"));"+RN;
		// Attachment
		res += "CREATE TABLE \"attachment\" ( \"_id\" int(10)  NOT NULL , \"id_domain\" int(10)  NOT NULL, \"path\" varchar(255) NOT NULL DEFAULT '', \"filename\" varchar(100) NOT NULL DEFAULT '', \"filetype\" varchar(4) NOT NULL DEFAULT '', \"width\" int(10)  DEFAULT NULL, \"height\" int(10)  DEFAULT NULL, \"size\" bigint(20)  NOT NULL DEFAULT '0', PRIMARY KEY (\"_id\") " +
					 "CONSTRAINT \"FK_attachment_domain\" FOREIGN KEY (\"id_domain\") REFERENCES \"domain\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE );" + RN +
		 			 "CREATE INDEX \"attachment_FK_attachment_domain\" ON \"attachment\" (\"id_domain\");" + RN;
		// Menu
		res += "CREATE TABLE \"menu\" ( \"_id\" int(10)  NOT NULL , \"domain\" int(10)  NOT NULL, \"title\" varchar(100) NOT NULL, \"logo\" int(10)  DEFAULT NULL, \"id_currency\" int(10)  NOT NULL, \"version\" int(10)  DEFAULT NULL, \"version_date\" timestamp NULL DEFAULT NULL, \"main_lang\" varchar(5) NOT NULL, PRIMARY KEY (\"_id\") " +
					 "CONSTRAINT \"FK_menu1\" FOREIGN KEY (\"domain\") REFERENCES \"domain\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE, " +
					 "CONSTRAINT \"FK_menu3\" FOREIGN KEY (\"logo\") REFERENCES \"attachment\" (\"_id\") ON DELETE SET NULL ON UPDATE SET NULL, " +
					 "CONSTRAINT \"FK_menu4\" FOREIGN KEY (\"main_lang\") REFERENCES \"language\" (\"label\"));" + RN +
					 "CREATE INDEX \"menu_FK_menu1\" ON \"menu\" (\"domain\");" + RN +
					 "CREATE INDEX \"menu_FK_menu3\" ON \"menu\" (\"logo\");" + RN +
					 "CREATE INDEX \"menu_FK_menu4\" ON \"menu\" (\"main_lang\");" + RN;
		// Tag
		res += "CREATE TABLE \"tag\" ( \"_id\" int(10)  NOT NULL , \"label\" varchar(50) NOT NULL DEFAULT '', PRIMARY KEY (\"_id\"));" + RN;
		// Category
		res += "CREATE TABLE \"category\" ( \"_id\" int(10)  NOT NULL , \"img\" int(10)  DEFAULT NULL, \"item_order\" smallint(6) DEFAULT NULL, PRIMARY KEY (\"_id\") " +
					 "CONSTRAINT \"FK_category2\" FOREIGN KEY (\"img\") REFERENCES \"attachment\" (\"_id\") ON DELETE SET NULL ON UPDATE SET NULL );" + RN +
		 			 "CREATE INDEX \"category_FK_category2\" ON \"category\" (\"img\");" + RN;
		// Category Language
		res += "CREATE TABLE \"category_2_language\" ( \"_id\" int(10)  NOT NULL , \"id_category\" int(10)  NOT NULL, \"lang\" varchar(5) NOT NULL, \"title\" varchar(50) NOT NULL, \"subtitle\" text NOT NULL, \"body\" text NOT NULL, PRIMARY KEY (\"_id\") " +
					 "CONSTRAINT \"FK_category_2_language1\" FOREIGN KEY (\"lang\") REFERENCES \"language\" (\"label\") ON DELETE CASCADE ON UPDATE CASCADE, " +
					 "CONSTRAINT \"FK_category_2_language2\" FOREIGN KEY (\"id_category\") REFERENCES \"category\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE );" + RN +
					 "CREATE INDEX \"category_2_language_FK_category_2_language1\" ON \"category_2_language\" (\"lang\");" + RN +
					 "CREATE INDEX \"category_2_language_FK_category_2_language2\" ON \"category_2_language\" (\"id_category\");" + RN;
		// Product
		res += "CREATE TABLE \"product\" (\"_id\" int(10)  NOT NULL , \"img\" int(10)  DEFAULT NULL, \"price\" decimal(10,2)  NOT NULL DEFAULT '0.00', \"item_order\" smallint(6) DEFAULT NULL, PRIMARY KEY (\"_id\") " +
					 "CONSTRAINT \"FK_product2\" FOREIGN KEY (\"img\") REFERENCES \"attachment\" (\"_id\") ON DELETE SET NULL ON UPDATE SET NULL);" + RN +
					 "CREATE INDEX \"product_FK_product2\" ON \"product\" (\"img\");" + RN;
		// Product Language
		res += "CREATE TABLE \"product_2_language\" (\"_id\" int(10)  NOT NULL , \"id_product\" int(10)  NOT NULL, \"lang\" varchar(5) NOT NULL, \"title\" varchar(50) NOT NULL, \"subtitle\" text NOT NULL, \"body\" text NOT NULL, PRIMARY KEY (\"_id\") " +
					 "CONSTRAINT \"FK_product_2_language1\" FOREIGN KEY (\"lang\") REFERENCES \"language\" (\"label\") ON DELETE CASCADE ON UPDATE CASCADE, " +
					 "CONSTRAINT \"FK_product_2_language2\" FOREIGN KEY (\"id_product\") REFERENCES \"product\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE);" + RN +
					 "CREATE INDEX \"product_2_language_FK_product_2_language1\" ON \"product_2_language\" (\"lang\");" + RN +
					 "CREATE INDEX \"product_2_language_FK_product_2_language2\" ON \"product_2_language\" (\"id_product\");" + RN;
		// Product category
		res += "CREATE TABLE \"product_2_category\" (\"id_product\" int(10)  NOT NULL, \"id_category\" int(10)  NOT NULL, PRIMARY KEY (\"id_product\",\"id_category\") " +
					 "CONSTRAINT \"FK_product_2_category1\" FOREIGN KEY (\"id_category\") REFERENCES \"category\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE, " +
					 "CONSTRAINT \"FK_product_2_category2\" FOREIGN KEY (\"id_product\") REFERENCES \"product\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE);" + RN +
					 "CREATE INDEX \"product_2_category_FK_product_2_category1\" ON \"product_2_category\" (\"id_category\");" + RN +
					 "CREATE INDEX \"product_2_category_FK_product_2_category2\" ON \"product_2_category\" (\"id_product\");" + RN;
		// Product Tag
		res += "CREATE TABLE \"product_2_tag\" (\"id_product\" int(10)  NOT NULL, \"id_tag\" int(10)  NOT NULL, \"typetag\" varchar(1)  NOT NULL, PRIMARY KEY (\"id_product\",\"id_tag\") " +
		 			 "CONSTRAINT \"FK_product_2_tag1\" FOREIGN KEY (\"id_tag\") REFERENCES \"tag\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE, " +
		 			 "CONSTRAINT \"FK_product_2_tag2\" FOREIGN KEY (\"id_product\") REFERENCES \"product\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE);" + RN +
		 			 "CREATE INDEX \"product_2_tag_FK_product_2_tag1\" ON \"product_2_tag\" (\"id_tag\");" + RN +
		 			 "CREATE INDEX \"product_2_tag_FK_product_2_tag2\" ON \"product_2_tag\" (\"id_product\");" + RN;
		// Product Discount
		res += "CREATE TABLE \"product_discount\" (\"_id\" int(10)  NOT NULL ,\"id_product\" int(10)  NOT NULL, \"recurring\" int(10) NOT NULL, \"fromtime\" timestamp NOT NULL, \"totime\" timestamp NOT NULL, \"price\" decimal(10,2)  NOT NULL DEFAULT '0.00', \""+COLUMN_ROW_COUNTER+"\" int(10)  NOT NULL, PRIMARY KEY (\"_id\",\"id_product\") " +
		 			 "CONSTRAINT \"FK_product_discount1\" FOREIGN KEY (\"id_product\") REFERENCES \"product\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE);" + RN +
		 			 "CREATE INDEX \"product_discount_FK_product_discount1\" ON \"product_discount\" (\"id_product\");" + RN;
		// Daily Menu
		res += "CREATE TABLE \"daily_menu\" (\"_id\" int(10)  NOT NULL ,\"week_day\" int(10)  NOT NULL, \"day_type\" int(10) NOT NULL, \"price\" decimal(10,2)  NOT NULL DEFAULT '0.00', PRIMARY KEY (\"_id\"));" + RN;
		// Daily Menu for Item (Category/Product/Tag)
		res += "CREATE TABLE \"daily_menu_2_item\" (\"daily_menu\" int(10)  NOT NULL, \"id_category\" int(10) NULL, \"id_product\" int(10) NULL, \"id_tag\" int(10) NULL, \"item_order\" int(10)  NOT NULL, PRIMARY KEY (\"daily_menu\",\"id_category\",\"id_product\",\"id_tag\") " +
					 "CONSTRAINT \"FK_daily_menu_2_item1\" FOREIGN KEY (\"id_category\") REFERENCES \"category\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE, " +
					 "CONSTRAINT \"FK_daily_menu_2_item2\" FOREIGN KEY (\"id_product\") REFERENCES \"product\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE, " +
					 "CONSTRAINT \"FK_daily_menu_2_item3\" FOREIGN KEY (\"id_tag\") REFERENCES \"tag\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE, " +
		 			 "CONSTRAINT \"FK_daily_menu_2_item4\" FOREIGN KEY (\"daily_menu\") REFERENCES \"daily_menu\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE);" + RN +
		 			 "CREATE INDEX \"daily_menu_2_item_FK_daily_menu_2_item1\" ON \"daily_menu_2_item\" (\"id_category\");" + RN +
		 			 "CREATE INDEX \"daily_menu_2_item_FK_daily_menu_2_item2\" ON \"daily_menu_2_item\" (\"id_product\");" + RN +
		 			 "CREATE INDEX \"daily_menu_2_item_FK_daily_menu_2_item3\" ON \"daily_menu_2_item\" (\"id_tag\");" + RN +
		 			 "CREATE INDEX \"daily_menu_2_item_FK_daily_menu_2_item4\" ON \"daily_menu_2_item\" (\"daily_menu\");" + RN;
		return res;
	}
	
	/**
	 * Select table to obtain data values
	 * @param table
	 * @param idDomain
	 * @return
	 * @throws Exception
	 */
	public String getDataValues(int idDomain) throws Exception {
		//Domain
		String res = getDataTable("domain","SELECT _id,label,description,MD5(CONCAT('"+CustomKeys.LP_BASE_PREFIX+"',unlock_key,'"+CustomKeys.LP_BASE_SUFIX+"'))as unlock_key FROM domain WHERE _id="+idDomain);
		// Language
		res += getDataTable("language","SELECT c.label,c.description,c._id FROM language c INNER JOIN menu a ON 1=1 INNER JOIN menu_2_language b ON a._id=b.id_menu AND c.label=b.lang AND b.deleted IS NULL WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Language Labels
		res += getDataTable("language_label","SELECT l.lang as lang,a.label,IFNULL(b.value,a.default_value)as value FROM menu m INNER JOIN menu_2_language l ON m._id=l.id_menu AND l.deleted IS NULL INNER JOIN language_label a LEFT OUTER JOIN language_label_custom b ON a._id=b.id_label AND b.domain=m.domain AND l.lang=b.lang WHERE m.domain="+idDomain+" AND m.active=1 AND m.deleted IS NULL");
		// Menu
		res += getDataTable("menu","SELECT a._id,a.domain,a.title,a.logo,a.id_currency,b.version,(b.deploy_end) as version_date,a.main_lang FROM menu a LEFT OUTER JOIN menu_version b ON a.last_version=b._id WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL LIMIT 1");
		// Currency
		res += getDataTable("currency","SELECT b._id,b.label,b.symbol,b.digit,b.separator_decimal,b.separator_thousand FROM menu a INNER JOIN currency b ON a.id_currency=b._id WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL LIMIT 1");
		// Attachment
		res += getDataTable("attachment","SELECT c._id,c.id_domain,c.path,c.filename,c.filetype,c.width,c.height,c.size FROM menu a INNER JOIN view_active_attachment b ON a._id=b.id_menu INNER JOIN attachment c ON b.img=c._id WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Tag
		res += getDataTable("tag","SELECT DISTINCT (a.id) as _id,a.label FROM tag a LEFT OUTER JOIN product_2_tag b ON a.id=b.id_tag LEFT OUTER JOIN category_2_tag c ON a.id=c.id_tag WHERE a.id_domain="+idDomain+" AND (b.id_tag IS NOT NULL OR c.id_tag IS NOT NULL)");
		// Category
		res += getDataTable("category","SELECT b._id,b.img,b.item_order FROM menu a INNER JOIN category b ON a._id=b.id_menu AND b.deleted IS NULL AND b.active=1 WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Category Language
		res += getDataTable("category_2_language","SELECT c._id,c.id_category,c.lang,c.title,c.subtitle,c.body FROM menu a INNER JOIN category b ON a._id=b.id_menu AND b.deleted IS NULL AND b.active=1 INNER JOIN menu_2_language l ON a._id=l.id_menu AND l.deleted IS NULL INNER JOIN category_2_language c ON c.lang=l.lang AND c.id_category=b._id AND c.deleted IS NULL WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Product
		res += getDataTable("product","SELECT DISTINCT b._id,b.img,b.price,b.item_order FROM menu a INNER JOIN product b ON a._id=b.id_menu AND b.deleted IS NULL INNER JOIN product_2_category d ON b._id=d.id_product INNER JOIN category e ON d.id_category=e._id AND e.active=1 AND e.deleted IS NULL WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Product Language
		res += getDataTable("product_2_language","SELECT DISTINCT c._id,c.id_product,c.lang,c.title,c.subtitle,c.body FROM menu a INNER JOIN product b ON a._id=b.id_menu AND b.deleted IS NULL AND b.active=1 INNER JOIN menu_2_language l ON a._id=l.id_menu AND l.deleted IS NULL INNER JOIN product_2_language c ON c.lang=l.lang AND c.id_product=b._id AND c.deleted IS NULL INNER JOIN product_2_category d ON b._id=d.id_product INNER JOIN category e ON d.id_category=e._id AND e.active=1 AND e.deleted IS NULL WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Product category
		res += getDataTable("product_2_category","SELECT c.id_product,c.id_category FROM menu a INNER JOIN product b ON a._id=b.id_menu AND b.deleted IS NULL AND b.active=1 INNER JOIN product_2_category c ON c.id_product=b._id INNER JOIN category e ON c.id_category=e._id AND e.active=1 AND e.deleted IS NULL WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Product Tag
		res += getDataTable("product_2_tag","SELECT b.id_product,b.id_tag,b.typetag FROM menu a INNER JOIN view_active_inherit_tags b ON a._id=b.id_menu WHERE a.domain="+idDomain+" AND a.active=1 AND a.deleted IS NULL");
		// Product Discount
		res += getDataTable("product_discount","SELECT c.id as _id,b.id_product,c.recurring,c.fromtime,c.totime,CAST(IF(c.fixed_price<>-1,c.fixed_price,d.price*(1+c.perc_discount/100))as Decimal(10,2))as price,0 as "+COLUMN_ROW_COUNTER+" FROM menu a INNER JOIN view_inherit_discounts b ON a._id=b.id_menu INNER JOIN discount_policy c ON b.id_discount=c.id INNER JOIN product d ON b.id_product=d._id WHERE a.domain="+idDomain+" AND a.active=1 AND c.active=1 ORDER BY b.type_discount DESC,b.typetag DESC,c.priority_order");
		// Daily Menu
		res += getDataTable("daily_menu","SELECT _id,week_day,day_type,price FROM daily_menu WHERE domain="+idDomain+" AND active=1 AND deleted IS NULL");
		// Daily Menu item
		res += getDataTable("daily_menu_2_item","SELECT a1._id as daily_menu, b1.category_id as id_category,NULL as id_product,NULL as id_tag,b1.item_order FROM daily_menu a1 INNER JOIN category_2_daily_menu b1 ON a1._id=b1.daily_menu WHERE a1.domain="+idDomain+" AND a1.active=1 AND a1.deleted IS NULL UNION ALL SELECT a2._id, NULL,b2.product_id,NULL,b2.item_order FROM daily_menu a2 INNER JOIN product_2_daily_menu b2 ON a2._id=b2.daily_menu WHERE a2.domain="+idDomain+" AND a2.active=1 AND a2.deleted IS NULL UNION ALL SELECT a3._id,NULL,NULL,b3.tag_id,b3.item_order FROM daily_menu a3 INNER JOIN tag_2_daily_menu b3 ON a3._id=b3.daily_menu WHERE a3.domain="+idDomain+" AND a3.active=1 AND a3.deleted IS NULL");
		return res;
	}
	
	/**
	 * Get Data from table
	 * @param query
	 * @return
	 * @throws Exception
	 */
	private String getDataTable(String tableName, String query) throws Exception {
		int column = 0;
		Set<Integer> quotes = new HashSet<Integer>();
		String res = "";
		String statStr = "";
		String dataStr = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		debug("*** table "+tableName);
		try {
			ps = getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			int cont=0;
			int columnCounter = -1;
			while (rs.next()) {
				cont++;
				if (statStr.equals("")) {
					ResultSetMetaData rsmd = rs.getMetaData();
					column = rsmd.getColumnCount();
					statStr = "INSERT INTO "+tableName+"(";
					for(int i=0; i<column; i++) {
						if (i>0)
							statStr += ",";
						statStr += "`"+rsmd.getColumnLabel(i+1)+"`";
						getData().getLog().info(rsmd.getColumnLabel(i+1)+" "+rsmd.getColumnType(i+1)+" "+rsmd.getColumnTypeName(i+1));
						if (COLUMN_ROW_COUNTER.equals(rsmd.getColumnLabel(i+1)))
							columnCounter = i;
						
						if (QUOTES_TYPES.contains(rsmd.getColumnType(i+1)))
							quotes.add(i);
					}
					statStr += ") VALUES ";
				}
				dataStr = "(";
				for(int i=0; i<column; i++) {
					if (i>0)
						dataStr += ",";
					if (i==columnCounter)
						dataStr += cont;
					else {
						String value = rs.getString(i+1);
						if (value!=null && quotes.contains(i))
							value= "'"+value.replace("'", "''")+"'";
						dataStr += value==null ? "NULL" : value;
					}
				}
				dataStr += ")";
				
				res += statStr+dataStr+";"+RN;
			}
					
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return res+RN;
	}
	
	
	/**
	 * Get Updated Attachments (Deleted and to create)
	 * @param idDomain
	 * @param newAttachs
	 * @param delAttachs
	 */
	public void loadUpdatedAttach(int idDomain, List<AttachBean> newAttachs, List<AttachBean> delAttachs) throws Exception {	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT deleted,_id,id_domain,path,filename FROM (SELECT IFNULL((SELECT creation_date FROM menu_version WHERE id_domain=? AND deploy_end IS NOT NULL ORDER BY _id DESC LIMIT 1),?)as date_version) a " +
																						"INNER JOIN ( " +
																						"	SELECT _id,id_domain,path,filename,creation_date,update_date,deleted FROM attachment WHERE id_domain=? " +
																						" UNION ALL " +
																						" SELECT 0,id_domain,path,filename,NULL,NULL,deleted FROM attachment_deleted WHERE id_domain=? " +
																						") b " +
																						"WHERE (b._id>0 AND (b.creation_date>a.date_version OR IFNULL(b.update_date>a.date_version,0) OR IFNULL(b.deleted>a.date_version,0)) " +
																						"      AND NOT (b.creation_date>a.date_version AND IFNULL(b.deleted>a.date_version,0)) ) " +
																						"			 OR (b._id=0 AND b.deleted>a.date_version) ");
			ps.setInt(1,idDomain);
			ps.setString(2, MenuVersionBsn.DATE_DEFAULT_START);
			ps.setInt(3,idDomain);
			ps.setInt(4,idDomain);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getTimestamp("deleted")==null)
					newAttachs.add(new AttachBean(rs.getInt("_id"), rs.getInt("id_domain"), rs.getString("path"), rs.getString("filename")));
				else
					delAttachs.add(new AttachBean(rs.getInt("_id"), rs.getInt("id_domain"), rs.getString("path"), rs.getString("filename")));
			}
			
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
}
