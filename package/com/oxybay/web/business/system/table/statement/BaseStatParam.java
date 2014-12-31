/**
 * IntStatParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.oxybay.web.beans.system.table.FieldBean;

public abstract class BaseStatParam {
	
	/* field */
	protected FieldBean field = null;
	
	/*
	 * Constructor
	 */
	public BaseStatParam(FieldBean field){
		this.field = field;
	}
	
	/**
	 * Set Param into preparedStatment (for recursive mode)
	 * @param ps
	 * @param count
	 * @param obj
	 * @throws Exception
	 */
	public void psSet(PreparedStatement ps, int count, Object obj) throws Exception {
		psSetValue(ps, count, FieldBean.getter(field.getField().getName(),obj));
	}

	/**
	 * Set Param into preparedstatement (final routine)
	 * @param ps
	 * @param count
	 */
	protected abstract void psSetValue(PreparedStatement ps, int count, Object value) throws Exception;
	
	/**
	 * Get Param from resultSet (for recursive mode)
	 * @param rs
	 * @param count
	 * @param obj
	 * @throws Exception
	 */
	public void rsGet(ResultSet rs, int count, Object obj) throws Exception {
		FieldBean.setter(field.getField().getName(), obj, rsGetValue(rs, count));
	}
	
	/**
	 * Get Param from ResultSet (final routine)
	 * @param rs
	 * @param count
	 * @return
	 */
	protected abstract Object rsGetValue(ResultSet rs, int count) throws Exception;
}
