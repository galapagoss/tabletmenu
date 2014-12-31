/**
 * TableQueryFields.java
 * 18/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table;

import java.util.List;
import java.util.Map;

import com.oxybay.web.beans.system.table.FieldBean;

public class TableQueryFields {

	/** table Fields definition*/
	private Map<String,FieldBean> listFields = null;

	
	/**
	 * @param listFields
	 */
	public TableQueryFields(Map<String,FieldBean> listFields) {
		this.listFields = listFields;
	}
	
	
	/**
	 * Append Field
	 * @param list
	 * @param item
	 * @return
	 */
	private String appendField(String list, String item) {
		return (list.equals("")) ? item : ","+item;
	}
	
	/**
	 * Get Fields String for loading query
	 * @return
	 */
	public String getFieldsForLoading(boolean noKey, List<FieldBean> toUse) {
		String strFields = "";
		for(FieldBean field: listFields.values()) {
			if (!(noKey && field.getFieldAttr()==FieldBean.FIELD_ATTRIBUTE_KEY) && !field.isSkipRead()) {
				strFields += appendField(strFields, field.getTableLabel());
				toUse.add(field);
			}
		}
		
		return strFields;
	}
	
	/**
	 * Get Fields Strings (datafields & valuefields) for inserting query
	 * @return
	 */
	public String[] getFieldsForInserting(List<FieldBean> toUse) {
		String[] strFields = {"",""};
		for(FieldBean field: listFields.values()) 
			if (field.getFieldAttr()!=FieldBean.FIELD_ATTRIBUTE_KEY && field.getFieldAttr()!=FieldBean.FIELD_ATTRIBUTE_UPDATE && field.getFieldAttr()!=FieldBean.FIELD_ATTRIBUTE_DELETE && !field.isSkipInsert()){
				strFields[0] += appendField(strFields[0], field.getTableLabel());
				strFields[1] += appendField(strFields[1], field.getTableIns());
				if (field.getFieldAttr()!=FieldBean.FIELD_ATTRIBUTE_CREATE )
					toUse.add(field);
			}
		
		return strFields;
	}
	
	/**
	 * get Fields String (datafields & valuefields) for updating query
	 * @param selFields
	 * @return
	 */
	public String getFieldsForUpdating(String selFields, FieldBean updateField, FieldBean deleteField, List<FieldBean> toUse) {
		String strFields = "";
		if (selFields==null) {
			for(FieldBean field: listFields.values()) 
				if (field.getFieldAttr()==FieldBean.FIELD_ATTRIBUTE_NONE && !field.isSkipUpdate()) {
					toUse.add(field);
					strFields += appendField(strFields, field.getTableLabel()+"="+field.getTableIns());
				}
			
		} else {
			for(String fieldName : selFields.split(",")) {
				FieldBean field = listFields.get(fieldName);
				strFields += appendField(strFields, field.getTableLabel()+"="+field.getTableIns());
				toUse.add(field);
			}
		}
		if (updateField!=null)
			strFields += appendField(strFields, updateField.getTableLabel()+"="+updateField.getTableIns());
		if (deleteField!=null)
			strFields += appendField(strFields, deleteField.getTableLabel()+"="+TableOperations.NULLKEY);

		return strFields;
	}

}
