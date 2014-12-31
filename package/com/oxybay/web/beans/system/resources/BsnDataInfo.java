/**
 * BsnDataInfo.java
 * 14/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.ConfigKeys;
import com.oxybay.web.resources.utils.Utils;

public class BsnDataInfo {

	/* resources */
	private Resources resources;
	/* log */
	private Logger log;
	/* connections */
	private Map<Integer, Connection> connections = null;;
	
	
	/**
	 * @param resources
	 * @param log
	 * @param connections
	 */
	public BsnDataInfo(Resources resources, Logger log) {
		this.resources = resources;
		this.log = log;
		this.connections = new HashMap<Integer, Connection>();
	}


	/**
	 * @return the resources
	 */
	public Resources getResources() {
		return resources;
	}


	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}


	/**
	 * @return the connections
	 */
	public Map<Integer, Connection> getConnections() {
		return connections;
	}
	
	/**
	 * Get Connection
	 * @param db
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection(int db, int offset) throws Exception {
		
		Connection con = this.connections.get(db);
		if (con!=null)
			return con;
		
		int baseOffset = 3;
		int iterations = 4;
		String who = "";
		String partStack = "";
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		for(int i=baseOffset+offset; i<stack.length && i<(baseOffset+offset+iterations); i++) {
			if (who.equals(""))
				who = stack[i].getClassName()+".<b>"+stack[i].getMethodName()+"()</b>";
			else {
				if (!partStack.equals(""))
					partStack += ",";
				partStack += stack[i].getClassName()+".<b>"+stack[i].getMethodName()+"()</b>";
			}
		}
    con = this.resources.getDb().getDBConnection(db, who, partStack);
    this.connections.put(new Integer(db), con);
    return con;
	}
	
	/**
	 * Get Db Connection
	 * @param db
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection(int db) throws Exception {
    return getConnection(db, 1);
	}
	
	/**
	 * Get connection to Main DB
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
    return getConnection(ConfigKeys.JDBC_DB_MAIN, 1);
	}
	
	
	/**
	 * Get Count List
	 * @return
	 */
	public int listCount(Connection con) {
		 int items = 0;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
		   ps = con.prepareStatement("SELECT FOUND_ROWS()");
		   rs = ps.executeQuery();
		   if (rs.next()) 
		  	 items = rs.getInt(1);

		   rs.close(); rs = null;
		   ps.close(); ps = null;
		 } catch(Exception ex) {
	     Utils.trace(ex);
		 } finally {
			 try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			 try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		 }
		 
		 return items;
	}
	
	/**
	 * Get Last ID
	 * @return
	 */
	public long lastID(Connection con) {
		long id = 0;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
		   ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
		   rs = ps.executeQuery();
		   if (rs.next()) 
		  	 id = rs.getLong(1);

		   rs.close(); rs = null;
		   ps.close(); ps = null;
		 } catch(Exception ex) {
			 Utils.trace(ex);
		 } finally {
			 try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			 try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		 }
		 
		 return id;
	}
}
