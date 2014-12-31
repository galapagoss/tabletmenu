/**
 * BooleanParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import com.oxybay.web.beans.system.table.FieldBean;

public class BooleanParam extends BaseStatParam {

	
	/**
	 * @param field
	 */
	public BooleanParam(FieldBean field) {
		super(field);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#psSet(java.sql.PreparedStatement, int, java.lang.Object)
	 */
	@Override
	public void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value == null)
			ps.setNull(count, Types.BOOLEAN);
		else
			ps.setBoolean(count, (Boolean)value);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#rsGet(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		return rs.getBoolean(count);
	}

}
