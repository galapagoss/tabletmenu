/**
 * LongParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import com.oxybay.web.beans.system.table.FieldBean;
import com.oxybay.web.resources.keys.ConfigKeys;

public class FileParam extends BaseStatParam {
	

	/**
	 * @param field
	 */
	public FileParam(FieldBean field) {
		super(field);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#psSet(java.sql.PreparedStatement, int)
	 */
	@Override
	public void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value!=null && value instanceof File) {
			File file = (File)value;
			FileInputStream stream = new FileInputStream(file);
			ps.setBinaryStream(count, stream, (int)file.length());
		} else 
			ps.setNull(count, Types.BLOB);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#rsGet(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		InputStream stream = rs.getBinaryStream(count);
		if (stream==null)
			return null;
		
		FileOutputStream out =null;
		try {
			File file = File.createTempFile(ConfigKeys.SITE_LABEL, ConfigKeys.SITE_VERSION+"_"+(int)(Math.random()*1000));
			file.deleteOnExit();
			out = new FileOutputStream(file);
			byte buf[] = new byte[1024];
			int len;
			while ((len = stream.read(buf))>0)
				out.write(buf,0,len);
			return file;
		} catch(Exception e) { 
			
		} finally {
			if (out!=null) out.close();
			if (stream!=null) stream.close();
		}
		
		return null;
	}

}
