/**
 * LongParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import com.oxybay.web.beans.system.table.FieldBean;
import com.oxybay.web.business.system.table.annotations.TableUpdate;

public class LongParam extends BaseStatParam {
	

	/**
	 * @param field
	 */
	public LongParam(FieldBean field) {
		super(field);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#psSet(java.sql.PreparedStatement, int)
	 */
	@Override
	public void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value == null || (field.getTableUpdate()==TableUpdate.EMPTYZERO_NULL && (Long)value==0))
			ps.setNull(count, Types.BIGINT);
		ps.setLong(count,(Long)value);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#rsGet(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		return rs.getLong(count);
	}

}
