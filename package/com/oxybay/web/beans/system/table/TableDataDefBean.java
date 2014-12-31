/**
 * RecursiveTableData.java
 * 18/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.oxybay.web.business.system.table.annotations.TableDef;

public class TableDataDefBean {

	/* table definitions */
	private TableDef tableDef = null;
	/* key field */
	private FieldBean keyField = null;
	/* table fields */
	private Map<String,FieldBean> tableFields = null;
	/* bean def class */
	private Class<?> classDef = null;
	/* delete field */
	private FieldBean deleteField = null;
	/* update field */
	private FieldBean updateField = null;
	/* nested table*/
	private HashMap<FieldBean, TableDataDefBean> nestedTable = null;
	/* ordering list */
	private HashMap<Integer,String> orderList = null;
	/* alias for joined tables */
	private String alias = "";
	
	
	/**
	 * @param classDef
	 */
	public TableDataDefBean(Class<?> classDef) {
		this.classDef = classDef;
		this.tableDef = classDef.getAnnotation(TableDef.class);
		this.alias = this.tableDef.table();
		this.tableFields = new LinkedHashMap<String, FieldBean>();
		this.nestedTable = new HashMap<FieldBean, TableDataDefBean>();
		this.orderList = new HashMap<Integer, String>();
	}


	/**
	 * @return the tableDef
	 */
	public TableDef getTableDef() {
		return tableDef;
	}


	/**
	 * @param tableDef the tableDef to set
	 */
	public void setTableDef(TableDef tableDef) {
		this.tableDef = tableDef;
	}


	/**
	 * @return the keyField
	 */
	public FieldBean getKeyField() {
		return keyField;
	}


	/**
	 * @param keyField the keyField to set
	 */
	public void setKeyField(FieldBean keyField) {
		this.keyField = keyField;
	}



	/**
	 * @return the tableFields
	 */
	public Map<String, FieldBean> getTableFields() {
		return tableFields;
	}


	/**
	 * @return the classDef
	 */
	public Class<?> getClassDef() {
		return classDef;
	}


	/**
	 * @return the deleteField
	 */
	public FieldBean getDeleteField() {
		return deleteField;
	}


	/**
	 * @param deleteField the deleteField to set
	 */
	public void setDeleteField(FieldBean deleteField) {
		this.deleteField = deleteField;
	}


	/**
	 * @return the nestedTable
	 */
	public HashMap<FieldBean, TableDataDefBean> getNestedTable() {
		return nestedTable;
	}


	/**
	 * @return the updateField
	 */
	public FieldBean getUpdateField() {
		return updateField;
	}


	/**
	 * @param updateField the updateField to set
	 */
	public void setUpdateField(FieldBean updateField) {
		this.updateField = updateField;
	}


	/**
	 * @return the orderList
	 */
	public HashMap<Integer, String> getOrderList() {
		return orderList;
	}


	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}


	/**
	 * @param aliasPrefix 
	 */
	public void setAliasPrefix(String prefix) {
		this.alias = prefix+"_"+tableDef.table();
	}

	
}
