/**
 * TableBsn.java
 * 18/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table;

import java.util.List;

import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;
import com.oxybay.web.resources.keys.ConfigKeys;

public class TableBsn extends RootBsn {
	
	/* table operation object */
	private TableOperations tableOp = null;

	/**
	 * @param data
	 */
	public TableBsn(BsnDataInfo data, Class<? extends Object> objClass) throws Exception {
		super(data);
		this.tableOp = new TableOperations(data, objClass);
	}
	
	/**
	 * Load a list of beans matching passed filters and from selected DB
	 * @param filter
	 * @param db
	 * @throws Exception
	 */
	public void list(PaginatorBean filter, int db) throws Exception {
		tableOp.list(filter, db, false,"");
	}
	
	/**
	 * Load a list of beans matching passed filters and from MAIN DB
	 * @param filter
	 * @throws Exception
	 */
	public void list(PaginatorBean filter) throws Exception {
		list(filter, ConfigKeys.JDBC_DB_MAIN);
	}

	
	/**
	 * Load a simple list of beans from selected DB
	 * @param db
	 * @param order
	 * @param customFilters
	 * @return
	 * @throws Exception
	 */
	public List<Object> listSimple(int db, int order, String customFilters) throws Exception {
		return tableOp.listSimple(db, order, customFilters);
	}
	/**
	 * Load a simple list of beans from Main DB
	 * @param order
	 * @param customFilters
	 * @return
	 * @throws Exception
	 */
	public List<Object> listSimple(int order, String customFilters) throws Exception {
		return listSimple(ConfigKeys.JDBC_DB_MAIN, order, customFilters);
	}
	
	/**
	 * Load Bean from selected DB connection
	 * @param obj
	 * @param db
	 * @param customFilters
	 * @throws Exception
	 */
	public boolean loadBean(Object obj, int db, String customFilters) throws Exception {
		return tableOp.loadBean(obj, db, customFilters);
	}
	
	/**
	 * Load Bean from Main DB
	 * @param obj
	 * @throws Exception
	 */
	public boolean loadBean(Object obj) throws Exception {
		return loadBean(obj, ConfigKeys.JDBC_DB_MAIN, null);
	}
	
	/**
	 * Load Bean from Main DB with Custom Filters
	 * @param obj
	 * @throws Exception
	 */
	public boolean loadBean(Object obj, String customFilters) throws Exception {
		return loadBean(obj, ConfigKeys.JDBC_DB_MAIN, customFilters);
	}
	
	/**
	 * Insert Object Bean/List into selected DB
	 * @param obj
	 * @param db
	 * @return row inserted
	 * @throws Exception
	 */
	public int insertBean(Object obj, int db) throws Exception {
		return tableOp.insert(obj, db);
	}
	
	/**
	 * Insert Object Bean/List into Main DB
	 * @param obj
	 * @return row inserted
	 * @throws Exception
	 */
	public int insertBean(Object obj) throws Exception {
		return insertBean(obj, ConfigKeys.JDBC_DB_MAIN);
	}
	
	
	/**
	 * Get Bean Object List from id list
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public List<Object> getBeanList(String[] idList) throws Exception {
		return tableOp.getBeanList(idList);
	}
	
	/**
	 * Delete Object Bean/List from selected DB
	 * @param obj
	 * @param db
	 * @return row deleted
	 * @throws Exception
	 */
	public int deleteBean(Object obj, int db) throws Exception {
		return tableOp.delete(obj, db);
	}
	
	/**
	 * Delete Object Bean/List from Main DB
	 * @param obj
	 * @return row deleted
	 * @throws Exception
	 */
	public int deleteBean(Object obj) throws Exception {
		return deleteBean(obj, ConfigKeys.JDBC_DB_MAIN);
	}
	
	/**
	 * Update Object Bean/List from selected DB
	 * @param obj
	 * @param db
	 * @return row deleted
	 * @throws Exception
	 */
	public int updateBean(Object obj, int db) throws Exception {
		return updateBean(obj, db, null);
	}
	
	/**
	 * Update Object Bean/List from Main DB
	 * @param obj
	 * @return row deleted
	 * @throws Exception
	 */
	public int updateBean(Object obj) throws Exception {
		return updateBean(obj, ConfigKeys.JDBC_DB_MAIN);
	}
	
	/**
	 * Update Object Bean/List from selected DB
	 * @param obj
	 * @param db
	 * @parma fields
	 * @return row deleted
	 * @throws Exception
	 */
	public int updateBean(Object obj, int db, String fields) throws Exception {
		return tableOp.update(obj, db, fields);
	}
	
	/**
	 * Update Object Bean/List from Main DB
	 * @param obj
	 * @param fields
	 * @return row deleted
	 * @throws Exception
	 */
	public int updateBean(Object obj, String fields) throws Exception {
		return updateBean(obj, ConfigKeys.JDBC_DB_MAIN, fields);
	}
}
