/**
 * ObjectParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.oxybay.web.beans.system.table.FieldBean;
import com.oxybay.web.business.system.table.TableOperations;
import com.oxybay.web.business.system.table.annotations.TableType;
import com.oxybay.web.business.system.table.annotations.TableUpdate;

public class ObjectParam extends BaseStatParam {
	
	/* key field nested object*/
	private FieldBean key = null;
	
	/* key param interface */
	private BaseStatParam keyInterf = null;
	
	/**
	 * Constructor
	 * @param classField
	 */
	public ObjectParam(FieldBean field) throws Exception {
		super(field);
		Class<?> classField = field.getField().getType();
		for(Field subField: TableOperations.getAllFields(classField)) 
			if (!Modifier.isFinal(subField.getModifiers())) {
				TableType type = subField.getAnnotation(TableType.class);
				if (type!=null && type.value()==TableType.KEY) {
					key = new FieldBean(subField,TableUpdate.EMPTYZERO_NULL);
					break;
				}
			}
		if (key==null)
			throw new Exception("Key Field in nested object ("+field.getField().getName()+" - "+(classField!=null ? classField.toString() : "null")+") not found");
		
		keyInterf = DataParam.getInterfaceParam(key, true);
		
		if (keyInterf==null)
			throw new Exception("Key Interface ("+field.getField().getName()+" - "+(key.getClass()!=null ? key.getClass().toString() : "null")+") in nested object ("+(classField!=null ? classField.toString() : "null")+") not found");
	}

	
	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#psSet(java.sql.PreparedStatement, int, java.lang.Object)
	 */
	@Override
	public void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value==null)
			keyInterf.psSetValue(ps, count, value);
		else
			keyInterf.psSet(ps, count, value);
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.BaseStatParam#rsGetValue(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		return null;
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.BaseStatParam#rsGet(java.sql.ResultSet, int, java.lang.Object)
	 */
	@Override
	public void rsGet(ResultSet rs, int count, Object obj) throws Exception {
		Object objNested = FieldBean.getter(field.getField().getName(),obj);
		if (objNested==null) {
			Constructor<? extends Object> cons = field.getField().getType().getConstructor(new Class<?>[]{});
			objNested = cons.newInstance(new Object[]{});
			FieldBean.setter(field.getField().getName(), obj, objNested);
		}
		FieldBean.setter(key.getField().getName(), objNested, keyInterf.rsGetValue(rs, count));
	}
	
}
