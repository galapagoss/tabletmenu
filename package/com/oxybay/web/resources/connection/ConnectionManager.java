/**
 * ConnectionManager.java
 * 14/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.connection;

import java.sql.Connection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.oxybay.web.resources.keys.ConfigKeys;
import com.oxybay.web.resources.utils.Utils;

public class ConnectionManager {

	/* logger */
	private Logger log;
	/* connection pools */
	private ConnectionPool[] pools = new ConnectionPool[ConfigKeys.JDBC_DB_LIST.length];

	
	/**
	 * Constructor
	 */
	public ConnectionManager(Logger log) {
		this.log = log;
		for(int i=0; i<ConfigKeys.JDBC_DB_LIST.length; i++) 
			try {
				pools[i] = new ConnectionPool(ConfigKeys.JDBC_DB_LIST[i]);
				if (pools[i]==null) throw new Exception();
			} catch(Exception e) {
				this.log.error("ERROR TO OBTAIN DB CONNECTION: "+ConfigKeys.JDBC_DB_LIST[i]);
			}
	}
	

	
	/**
	 * Get DB Connection
	 * @param dbPos
	 * @param who
	 * @return
	 * @throws Exception
	 */
	public Connection getDBConnection(int dbPos, String who, String stack) throws Exception {
		return pools[dbPos].getConnection(who, stack);
	}
	
	
	/**
	 * Release all connections associated
	 * @param connections
	 */
	public void releaseDBConnections(Map<Integer, Connection> connections) {
		for(Entry<Integer, Connection> item: connections.entrySet()) {
			try {pools[item.getKey().intValue()].releaseConnection(item.getValue(), log);} catch(Exception e) { Utils.trace(e);}
		}
	}



	/**
	 * @return the pools
	 */
	public ConnectionPool[] getPools() {
		return pools;
	}
	
	
}
