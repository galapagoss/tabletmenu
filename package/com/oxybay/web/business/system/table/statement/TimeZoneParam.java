/**
 * TimeZoneParam.java
 * 22/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.TimeZone;

import com.oxybay.web.beans.system.table.FieldBean;

public class TimeZoneParam extends BaseStatParam {
	

	/**
	 * @param field
	 */
	public TimeZoneParam(FieldBean field) {
		super(field);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.BaseStatParam#psSetValue(java.sql.PreparedStatement, int, java.lang.Object)
	 */
	@Override
	protected void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value==null)
			ps.setNull(count, Types.VARCHAR);
		else
			ps.setString(count, ((TimeZone)value).getID());
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.BaseStatParam#rsGetValue(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		String res = rs.getString(count);
		return res==null ? null : TimeZone.getTimeZone(res);
	}

}
