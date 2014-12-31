package com.oxybay.web.resources.connection;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.oxybay.web.beans.system.connections.ConnectionInfo;

public class ConnectionPool {
	
	/** the data source */
	private DataSource ds;
	
	/** list of users using the connections */
	private ConcurrentHashMap<String,ConnectionInfo> connections = null;
	

	/**
	 * Constructor
	 * @param source
	 * @throws Exception
	 */
	public ConnectionPool(String source) throws Exception {
		
		Context ctx = new InitialContext();
		Context envCtx = (Context) ctx.lookup("java:comp/env");
		if(envCtx == null )
		  throw new Exception("No Env Context available");
		ds =  (DataSource)envCtx.lookup(source);
		connections = new ConcurrentHashMap<String,ConnectionInfo>();
	}

	/**
	 * <p>Get a connection from the pool</p>
	 * @param who Who is requesting the connection
	 * @return a connection to the pool
	 * @throws Exception
	 */
	public Connection getConnection(String who, String stack) throws Exception {
		long timeDB = System.currentTimeMillis();
		Connection con = ds.getConnection();
		timeDB = System.currentTimeMillis() - timeDB;
		connections.put(""+con.hashCode(),new ConnectionInfo(""+con.hashCode(),who,timeDB,stack));
		return con;
	}
	
	/**
	 * Release the connection from the pool
	 * @param c The connection to release
	 */
	public void releaseConnection(Connection c, Logger log) throws Exception  {
		
		long timeToRelease = System.currentTimeMillis();
		String whoStr = ""+c.hashCode();
		// Release Connection
		c.close();
		
		timeToRelease = System.currentTimeMillis() - timeToRelease;
		// Purge reference into user's connections map
		try { 
			ConnectionInfo relConn = (ConnectionInfo) connections.remove(whoStr);
			if (relConn != null) {
				long workTime = System.currentTimeMillis() - relConn.getCreated();
				if (workTime >= 4000) {
					relConn.setTimeReleaseDB(timeToRelease);
					log.warn("Slow thread - ms = "+workTime + " who = " + relConn.getBsnName() + " start = " + relConn.getCreated() + " dBAccess = (obt:" + relConn.getTimeObtainDB() +" rel:" + relConn.getTimeReleaseDB() + ")");
				}
			} else log.warn("ConcurrentHashMap remove item error: nothing to remove");
		} catch (Exception e) {
			log.warn("ConcurrentHashMap remove item error: Connection is null ! ");
		}
	}

	/**
	 * @return the connections
	 */
	public ConcurrentHashMap<String, ConnectionInfo> getConnections() {
		return connections;
	}
	

}
