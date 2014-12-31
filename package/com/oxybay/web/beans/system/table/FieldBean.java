/**
 * FieldBean.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import com.oxybay.web.business.system.table.annotations.TableFieldSkip;
import com.oxybay.web.business.system.table.annotations.TableUpdate;
import com.oxybay.web.business.system.table.statement.DataParam;
import com.oxybay.web.business.system.table.statement.BaseStatParam;

public class FieldBean {

	public static final short FIELD_ATTRIBUTE_NONE 			= 0;
	public static final short FIELD_ATTRIBUTE_KEY 			= 1;
	public static final short FIELD_ATTRIBUTE_CREATE		= 2;
	public static final short FIELD_ATTRIBUTE_UPDATE		= 3;
	public static final short FIELD_ATTRIBUTE_DELETE		= 4;
	
	/* name of table */
	private String nameTable = "";
	/* table label */
	private String tableLabel = "";
	/* table insert label */
	private String tableIns = "?";
	/* conditions for table reference */
	private String conditionTableRef = "";
	/* field attribute */
	private short fieldAttr = FIELD_ATTRIBUTE_NONE;
	/* table update annotation */
	private short tableUpdate = TableUpdate.NONE;
	
	/** operations skipping **/
	/* read */
	private boolean skipRead = false;
	/* insert */
	private boolean skipInsert = false;
	/* update */
	private boolean skipUpdate = false;
	

	/* field */
	private Field field = null;
	/* db param interface */
	private BaseStatParam dbParam;
	
	
	
	
	/**
	 * @param field
	 * @param tableUpdate
	 */
	public FieldBean(Field field, short tableUpdate) {
		this.field = field;
		this.tableUpdate = tableUpdate;
	}

	/**
	 * @param tableLabel
	 * @param tableIns
	 * @param field
	 * @param tableUpdate
	 * @param skipping
	 */
	public FieldBean(String nameTable,String tableLabel, String tableIns, Field field, short tableUpdate, TableFieldSkip skipping) throws Exception {
		this.nameTable = nameTable;
		this.tableLabel = tableLabel;
		this.tableIns = tableIns;
		this.field = field;
		this.tableUpdate = tableUpdate;
		if (skipping!=null) {
			this.skipInsert = skipping.insert();
			this.skipRead = skipping.read();
			this.skipUpdate = skipping.update();
		}
		this.dbParam = DataParam.getInterfaceParam(this,false);
	}
	
	/**
	 * @return the tableLabel
	 */
	public String getTableLabel() {
		return nameTable+"."+tableLabel;
	}
	

	/**
	 * @return the tableIns
	 */
	public String getTableIns() {
		return tableIns;
	}

	/**
	 * @return the field
	 */
	public Field getField() {
		return field;
	}
	
	
	
	/**
	 * @return the dbParam
	 */
	public BaseStatParam getDbParam() {
		return dbParam;
	}


	/**
	 * Value Getter
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object getter(Object obj) throws Exception {
		return FieldBean.getter(field.getName(), obj);
	}
	
	/**
	 * Setter
	 * @param obj
	 * @param value
	 * @throws Exception
	 */
	public void setter(Object obj, Object value) throws Exception {
		FieldBean.setter(field.getName(), obj, value);
	}

	/**
	 * Static Getter
	 * @param name
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object getter(String name, Object obj) throws Exception {
		return new PropertyDescriptor(name, obj.getClass()).getReadMethod().invoke(obj);
	}
	
	/**
	 * Static Setter
	 * @param name
	 * @param obj
	 * @param value
	 * @throws Exception
	 */
	public static void setter(String name, Object obj, Object value) throws Exception {
		new PropertyDescriptor(name, obj.getClass()).getWriteMethod().invoke(obj,new Object[]{value});
	}

	/**
	 * @return the fieldAttr
	 */
	public short getFieldAttr() {
		return fieldAttr;
	}

	/**
	 * @param fieldAttr the fieldAttr to set
	 */
	public void setFieldAttr(short fieldAttr) {
		this.fieldAttr = fieldAttr;
	}

	/**
	 * @return the conditionTableRef
	 */
	public String getConditionTableRef() {
		return conditionTableRef;
	}

	/**
	 * @param conditionTableRef the conditionTableRef to set
	 */
	public void setConditionTableRef(String conditionTableRef) {
		this.conditionTableRef = conditionTableRef;
	}

	/**
	 * @return the tableUpdate
	 */
	public short getTableUpdate() {
		return tableUpdate;
	}

	/**
	 * @return the skipRead
	 */
	public boolean isSkipRead() {
		return skipRead;
	}

	/**
	 * @return the skipInsert
	 */
	public boolean isSkipInsert() {
		return skipInsert;
	}

	/**
	 * @return the skipUpdate
	 */
	public boolean isSkipUpdate() {
		return skipUpdate;
	}

	
}

