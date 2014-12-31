/**
 * StringParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import com.oxybay.web.beans.system.table.FieldBean;
import com.oxybay.web.business.system.table.annotations.TableUpdate;

public class StringParam extends BaseStatParam {
	
	/**
	 * @param field
	 */
	public StringParam(FieldBean field) {
		super(field);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#psSet(java.sql.PreparedStatement, int, java.lang.Object)
	 */
	@Override
	public void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value == null || (field.getTableUpdate()==TableUpdate.EMPTYZERO_NULL && ((String)value).equals("")))
			ps.setNull(count, Types.VARCHAR);
		else
			ps.setString(count, (String)value);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#rsGet(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		return rs.getString(count);
	}

}
