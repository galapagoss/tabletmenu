/**
 * TableOperations.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.beans.system.table.FieldBean;
import com.oxybay.web.beans.system.table.TableCacheMap;
import com.oxybay.web.beans.system.table.TableDataDefBean;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableFieldSkip;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableRef;
import com.oxybay.web.business.system.table.annotations.TableType;
import com.oxybay.web.business.system.table.annotations.TableUpdate;
import com.oxybay.web.business.system.table.annotations.filters.TableFilterMoreFields;
import com.oxybay.web.business.system.table.annotations.filters.TableFilterReference;
import com.oxybay.web.business.system.table.filter.TableFilters;
import com.oxybay.web.business.system.table.statement.ObjectParam;

public class TableOperations {

	public static final String 		NULLKEY											= "NULL";
	
	private static final int 			CACHE_CAPACITY							= 20;
	private static final String 	FUNCTION_DATENOW						= "UTC_TIMESTAMP()";
	private static final String 	FUNCTION_CALCFOUNDROWS			= "SQL_CALC_FOUND_ROWS";
	
	
	private final String					EXCEPTION_ANNOTNOTFOUND			= "Table Annotations Not Found";
	private final String					EXCEPTION_KEYNOTFOUND				= "Key Field Not Found";
	private final String					EXCEPTION_FIELDSNOTFOUND		= "Table Fields Not Found";
	private final String					EXCEPTION_COLOBJECTNOTFOUND	= "Collection Class Object Not Found";
	private final String					EXCEPTION_COLOBJECTNOTMATCH	= "Collection Class Object Not Match with Definition Object";
	
	/* bsn data */
	private BsnDataInfo bsnData = null;
	/* table data definitions */
	private TableDataDefBean dataDef = null;
	
	/* bean data class */
	private Class<?> classBean = null;
	/* bean data collection */
	private Collection<?> collection = null;
	
	/* cache */
	private static TableCacheMap cache = new TableCacheMap(CACHE_CAPACITY);
	
	
	/**
	 * Constructor
	 * @param rootBsn
	 */
	public TableOperations(BsnDataInfo bsnData, Class<?> objClass) throws Exception {
		this.bsnData = bsnData;
		this.dataDef = cache.get(objClass);
		if (this.dataDef==null) {
			this.dataDef = new TableDataDefBean(objClass);
			if (dataDef.getTableDef()==null) 
				throw new Exception(EXCEPTION_ANNOTNOTFOUND+"("+(dataDef.getClassDef()!=null ? dataDef.getClassDef().toString() : "null")+")");
			analyzeFields(dataDef,false);
			cache.put(objClass, this.dataDef);
		}
		
	}
	

	/**
	 * Return Class wrapping primitive Type
	 * @param c
	 * @return
	 */
	private static Class<?> wrapClass(Class<?> c) {
   if (c.isPrimitive()) {
  	 if (c == int.class) 				return Integer.class;
  	 if (c == long.class) 			return Long.class;
  	 if (c == float.class) 			return Float.class;
  	 if (c == double.class) 		return Double.class;
  	 if (c == short.class) 			return Short.class;
  	 if (c == boolean.class) 		return Boolean.class;
  	 if (c == byte.class) 			return Byte.class;
  	 if (c == char.class) 			return Character.class;
  	 if (c == void.class) 			return Void.class;
   }
   return null;
  }
	
	
	/**
	 * Get All declared Fields (inheritance too)
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> type) {
    List<Field> fields = new ArrayList<Field>();
    for (Class<?> c = type; c != null; c = c.getSuperclass()) {
        fields.addAll(Arrays.asList(c.getDeclaredFields()));
    }
    return fields;
	}

	
	/**
	 * Analyze Fields
	 * @param obj
	 * @param op
	 */
	private void analyzeFields(TableDataDefBean data, boolean basicMode) throws Exception {
		for(Field field: TableOperations.getAllFields(data.getClassDef())) {
			if (!Modifier.isFinal(field.getModifiers())) {
				TableType type = field.getAnnotation(TableType.class);
				if (type==null || type.value()!=TableType.SKIP) {
					TableField tableLabel = field.getAnnotation(TableField.class);
					// Label string
					String label = (tableLabel!=null) ? tableLabel.value() : field.getName();
					// Value String
					String ins = "?";
					if (type!=null && type.value()==TableType.MD5) 
						ins = "MD5(?)";
					if (type!=null && (type.value()==TableType.CREATING || type.value()==TableType.UPDATING || type.value()==TableType.DELETING)) 
						ins = FUNCTION_DATENOW;
					// Table Update
					TableUpdate tableUpdate = field.getAnnotation(TableUpdate.class);
					short tabUpdate = tableUpdate==null ? TableUpdate.NONE : tableUpdate.value();
					
					FieldBean fieldBean = new FieldBean(data.getAlias(),label, ins, field, tabUpdate, field.getAnnotation(TableFieldSkip.class));
					if (type!=null) 
						switch (type.value()) {
							case TableType.KEY:		
								fieldBean.setFieldAttr(FieldBean.FIELD_ATTRIBUTE_KEY);
								data.setKeyField(fieldBean);
								break;
							case TableType.DELETING:		
								fieldBean.setFieldAttr(FieldBean.FIELD_ATTRIBUTE_DELETE);
								data.setDeleteField(fieldBean);
								break;
							case TableType.CREATING:		
								fieldBean.setFieldAttr(FieldBean.FIELD_ATTRIBUTE_CREATE);
								break;
							case TableType.UPDATING:		
								fieldBean.setFieldAttr(FieldBean.FIELD_ATTRIBUTE_UPDATE);
								data.setUpdateField(fieldBean);
								break;
						}
					// Ordering
					TableOrder tableOrder = field.getAnnotation(TableOrder.class);
					if (tableOrder!=null) {
						data.getOrderList().put(tableOrder.id(), "ISNULL(NULLIF("+fieldBean.getTableLabel()+",'"+tableOrder.exclude()+"')),"+fieldBean.getTableLabel()+" "+tableOrder.type());
					}
					if (!basicMode && fieldBean.getDbParam() instanceof ObjectParam) {
						TableDataDefBean nestedData = new TableDataDefBean(fieldBean.getField().getType());
						if (nestedData.getClassDef()!=null) {
							TableRef tableRef = field.getAnnotation(TableRef.class);
							if (tableRef!=null) {
								nestedData.setAliasPrefix(fieldBean.getField().getName());
								if(!tableRef.value().equals("")) 
									fieldBean.setConditionTableRef(" AND "+tableRef.value().replace("#.", nestedData.getAlias()+"."));
								analyzeFields(nestedData, true);
								dataDef.getNestedTable().put(fieldBean, nestedData);
							}
						}
					}
					data.getTableFields().put(field.getName(),fieldBean);
				}
			}
		}
	}
	
	
	/**
	 * Analyze Class data bean
	 * @param obj
	 */
	private void analyzeClassData(Object obj) throws Exception {
		if (obj instanceof AbstractMap<?, ?>) { // Abstract Map
			collection = ((AbstractMap<?,?>)obj).values();
			for(Iterator<? extends Object> i = collection.iterator(); i.hasNext(); ) {
				classBean = i.next().getClass();
				break;
			}
		} else if (obj instanceof AbstractCollection<?>) { // Abstract Collection 
			collection = ((AbstractCollection<?>)obj);
			for(Iterator<? extends Object> i = collection.iterator(); i.hasNext(); ) {
				classBean = i.next().getClass();
				break;
			}
		}
		else { // Single Object
			classBean = obj.getClass();
			List<Object> list = new ArrayList<Object>();
			list.add(obj);
			collection = list;
		}
		
		if (classBean==null) 
			throw new Exception(EXCEPTION_COLOBJECTNOTFOUND);
		if (classBean != dataDef.getClassDef())
			throw new Exception(EXCEPTION_COLOBJECTNOTMATCH+" ("+(classBean!=null ? classBean.toString() : "null")+"!="+(dataDef.getClassDef()!=null ? dataDef.getClassDef().toString() : "null")+")");
	}
	
	/**
	 * Load Bean data from key value
	 * @param objBean
	 * @param db
	 * @throws Exception
	 */
	public boolean loadBean(Object objBean, int db, String customFilter) throws Exception {
		if (dataDef.getKeyField()==null) 
			throw new Exception(EXCEPTION_KEYNOTFOUND);
		
		List<FieldBean> fieldsToUse = new ArrayList<FieldBean>();
		TableQueryFields queryFields = new TableQueryFields(dataDef.getTableFields());
		String fieldList = queryFields.getFieldsForLoading(false,fieldsToUse);
		if (fieldList.equals("")) 
			throw new Exception(EXCEPTION_FIELDSNOTFOUND);
		
		String tableJoin = dataDef.getTableDef().table();
		Map<FieldBean,List<FieldBean>> listNestedFieldsToUse = new HashMap<FieldBean, List<FieldBean>>();
		for(Entry<FieldBean, TableDataDefBean> nestedEntry : dataDef.getNestedTable().entrySet()) {
			if (!nestedEntry.getKey().isSkipRead()) {
				List<FieldBean> nestedFieldsToUse = new ArrayList<FieldBean>();
				TableQueryFields nestedFields = new TableQueryFields(nestedEntry.getValue().getTableFields());
				fieldList += ","+nestedFields.getFieldsForLoading(true,nestedFieldsToUse);
				listNestedFieldsToUse.put(nestedEntry.getKey(),nestedFieldsToUse);
				tableJoin += " LEFT OUTER JOIN "+nestedEntry.getValue().getTableDef().table()+" AS "+nestedEntry.getValue().getAlias()+" ON "+nestedEntry.getKey().getTableLabel()+"="+nestedEntry.getValue().getKeyField().getTableLabel()+" "+nestedEntry.getKey().getConditionTableRef();
			}
		}
		
		String query = "SELECT "+fieldList+" FROM "+tableJoin+" WHERE "+dataDef.getKeyField().getTableLabel()+"="+dataDef.getKeyField().getTableIns();
		if (customFilter!=null && !customFilter.equals(""))
			query += " OR ("+customFilter+")";
		query += " LIMIT 1";
		
		PreparedStatement ps = bsnData.getConnection(db).prepareStatement(query);
		dataDef.getKeyField().getDbParam().psSet(ps, 1, objBean);
		bsnData.getLog().info(ps);
		ResultSet rs = ps.executeQuery();
		int count=0;
		if (rs.next()) {
			for(FieldBean field: fieldsToUse){
				count++;
				field.getDbParam().rsGet(rs, count, objBean);
			}
			for(Entry<FieldBean, TableDataDefBean> nestedEntry : dataDef.getNestedTable().entrySet()) {
				if (!nestedEntry.getKey().isSkipRead()) {
					Object nestedObj = FieldBean.getter(nestedEntry.getKey().getField().getName(),objBean);
					List<FieldBean> nestedFieldsToUse = listNestedFieldsToUse.get(nestedEntry.getKey());
					if (nestedFieldsToUse!=null)
						for(FieldBean field: nestedFieldsToUse) {
							count++;
							field.getDbParam().rsGet(rs, count, nestedObj);
						}
				}
			}
			return true;
		}
		return false;
	}

	
	/**
	 * List Bean from filter object
	 * @param clazz
	 * @param filter
	 * @param db
	 * @throws Exception
	 */
	public void list(PaginatorBean filter, int db, boolean simpleMode, String customFilters) throws Exception {
		List<Object> res = new ArrayList<Object>();
		List<FieldBean> fieldsToUse = new ArrayList<FieldBean>();
		TableQueryFields queryFields = new TableQueryFields(dataDef.getTableFields());
		String fieldList = queryFields.getFieldsForLoading(false,fieldsToUse);
		if (fieldList.equals("")) 
			throw new Exception(EXCEPTION_FIELDSNOTFOUND);
		
		String tableJoin = dataDef.getTableDef().table();
		Map<FieldBean,List<FieldBean>> listNestedFieldsToUse = new HashMap<FieldBean, List<FieldBean>>();
		if (!simpleMode) {
			for(Entry<FieldBean, TableDataDefBean> nestedEntry : dataDef.getNestedTable().entrySet()) {
				if (!nestedEntry.getKey().isSkipRead()) {
					List<FieldBean> nestedFieldsToUse = new ArrayList<FieldBean>();
					TableQueryFields nestedFields = new TableQueryFields(nestedEntry.getValue().getTableFields());
					fieldList += ","+nestedFields.getFieldsForLoading(true,nestedFieldsToUse);
					listNestedFieldsToUse.put(nestedEntry.getKey(),nestedFieldsToUse);
					tableJoin += " LEFT OUTER JOIN "+nestedEntry.getValue().getTableDef().table()+" AS "+nestedEntry.getValue().getAlias()+" ON "+nestedEntry.getKey().getTableLabel()+"="+nestedEntry.getValue().getKeyField().getTableLabel()+" "+nestedEntry.getKey().getConditionTableRef();
				}
			}
		}
		
		String query = "SELECT "+FUNCTION_CALCFOUNDROWS+" "+fieldList+" FROM "+tableJoin+" WHERE 1=1";
		if (dataDef.getDeleteField()!=null) {
			query += " AND "+dataDef.getDeleteField().getTableLabel()+" IS NULL";
		}
		
		List<TableFilters> filtersToUse = new ArrayList<TableFilters>();
		//Filters loop
		if (!simpleMode && filter!=null) {
			for(Field field : TableOperations.getAllFields(filter.getClass()) ) {
				if (!Modifier.isFinal(field.getModifiers()) && TableFilters.class.isAssignableFrom(field.getType())) {
					TableFilterReference filterDef = field.getAnnotation(TableFilterReference.class);
					List<String> refFields = new ArrayList<String>();
					try {
						if (filterDef!=null) 
							refFields.add(dataDef.getNestedTable().get(dataDef.getTableFields().get(filterDef.value())).getTableFields().get(field.getName()).getTableLabel());
						else
							refFields.add(dataDef.getTableFields().get(field.getName()).getTableLabel());
					} catch (Exception e) {
						bsnData.getLog().error("Error to obtain reference for field "+field.getName()+" of filter "+filter.getClass().toString());
					}
					if (!refFields.isEmpty()) {
						TableFilterMoreFields moreFields = field.getAnnotation(TableFilterMoreFields.class);
						if (moreFields!=null && moreFields.value()!=null)
							for(String moreItem : moreFields.value()) {
								refFields.add(dataDef.getTableFields().get(moreItem).getTableLabel());
							}
						
						TableFilters tableFilter = (TableFilters)FieldBean.getter(field.getName(), filter); 
						String filterQuery = tableFilter.generateQuery(refFields);
						if (!filterQuery.equals("")) {
							query += filterQuery;
							filtersToUse.add(tableFilter);
						}
					}
					
				}
			}
		}
		
		if (simpleMode && !customFilters.equals(""))
			query += " AND "+customFilters;
		
		// ORDER 
		int orderID = (filter.getOrder()>0) ? filter.getOrder() : dataDef.getTableDef().order();
		if (orderID>0) 
			query += " ORDER BY "+dataDef.getOrderList().get(orderID);
		// LIMIT
		if (!simpleMode)
			query += " LIMIT "+filter.calculateQueryRange();
		
		
		Connection con = bsnData.getConnection(db);
		PreparedStatement ps = con.prepareStatement(query);
		int counter = 0;
		for(TableFilters tableFilters : filtersToUse) 
			counter = tableFilters.setPreparedStatement(ps, counter);
		
		bsnData.getLog().info(ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int count=0;
			Object objBean = dataDef.getClassDef().newInstance();
			for(FieldBean field: fieldsToUse){
				count++;
				field.getDbParam().rsGet(rs, count, objBean);
			}
			if (!simpleMode)
				for(Entry<FieldBean, TableDataDefBean> nestedEntry : dataDef.getNestedTable().entrySet()) {
					if (!nestedEntry.getKey().isSkipRead()) {
						Object nestedObj = FieldBean.getter(nestedEntry.getKey().getField().getName(),objBean);
						List<FieldBean> nestedFieldsToUse = listNestedFieldsToUse.get(nestedEntry.getKey());
						if (nestedFieldsToUse!=null)
							for(FieldBean field: nestedFieldsToUse) {
								count++;
								field.getDbParam().rsGet(rs, count, nestedObj);
							}
					}
				}
			res.add(objBean);
		}
		
		filter.setItems(res);
		filter.setTotal(bsnData.listCount(con));
	}
	
	
	/**
	 * Simple listing
	 * @param db
	 * @param order
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<Object> listSimple(int db, int order, String filter) throws Exception {
		PaginatorBean page = new PaginatorBean();
		page.setOrder(order);
		list(page, db, true, filter);
		return page.getItems();
	}
	
	/**
	 * Get Bean List
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public List<Object> getBeanList(String[] idList) throws Exception {
		List<Object> res = new ArrayList<Object>();
		for(String item : idList) {
			Object obj = dataDef.getClassDef().newInstance();
			bsnData.getLog().info("FieldName " + wrapClass(dataDef.getKeyField().getField().getType()));
			FieldBean.setter(dataDef.getKeyField().getField().getName(), obj, wrapClass(dataDef.getKeyField().getField().getType()).getConstructor(new Class[] {String.class }).newInstance(item));
			res.add(obj);
		}
		return res;
	}
	
	/**
	 * Delete table bean
	 * @param objBeab
	 * @param db
	 * @return
	 */
	public int delete(Object objBean, int db) throws Exception {
		if (dataDef.getKeyField()==null) 
			throw new Exception(EXCEPTION_KEYNOTFOUND);
		
		analyzeClassData(objBean);
		
		// Query
		String query = "";
		// Logic or Physic deletion
		if (dataDef.getDeleteField() != null) 
			query = "UPDATE "+dataDef.getTableDef().table()+" SET " +dataDef.getDeleteField().getTableLabel()+"="+dataDef.getDeleteField().getTableIns();
		else 
			query = "DELETE FROM "+dataDef.getTableDef().table();
		query += " WHERE " + dataDef.getKeyField().getTableLabel()+" IN (";
		for(int i=0; i<collection.size(); i++) {
			if (i>0) query += ",";
			query += dataDef.getKeyField().getTableIns();
		}
		query += ")";
		
		PreparedStatement ps = bsnData.getConnection(db).prepareStatement(query);
		int psSetCont = 0;
		// Set Params into the query
		for(Iterator<? extends Object> i = collection.iterator(); i.hasNext();) {
			Object bean = i.next();
			psSetCont++;
			dataDef.getKeyField().getDbParam().psSet(ps, psSetCont, bean);
		}
		bsnData.getLog().info(ps);
		return ps.executeUpdate();
	}

	/**
	 * Update table bean
	 * @param objBean
	 * @param skipNull
	 * @param db
	 * @return
	 */
	public int update(Object objBean, int db, String selFields) throws Exception {
		if (dataDef.getKeyField()==null) 
			throw new Exception(EXCEPTION_KEYNOTFOUND);

		List<FieldBean> fieldsToUse = new ArrayList<FieldBean>();
		TableQueryFields queryFields = new TableQueryFields(dataDef.getTableFields());
		String fieldList = queryFields.getFieldsForUpdating(selFields,dataDef.getUpdateField(),dataDef.getDeleteField(),fieldsToUse);
		
		if (fieldList.equals(""))
			throw new Exception("Table Fields not found");
		
		String query = "UPDATE "+dataDef.getTableDef().table()+" SET " + fieldList + " WHERE " + dataDef.getKeyField().getTableLabel() +"=" + dataDef.getKeyField().getTableIns();
		
		// Prepared Statement
		PreparedStatement ps = bsnData.getConnection(db).prepareStatement(query);
		bsnData.getLog().info(ps);
		int psSetCont = 0;
		// Set Params into the query
		for(FieldBean field: fieldsToUse) {
			psSetCont++;
			bsnData.getLog().info("RS SET: "+field.getField().getName());
			field.getDbParam().psSet(ps, psSetCont, objBean);
		}
		
		psSetCont++;
		dataDef.getKeyField().getDbParam().psSet(ps, psSetCont, objBean);
		
		bsnData.getLog().info(ps);
		return ps.executeUpdate();
	}
	
	/**
	 * Insert bean data into db table
	 * @param bean
	 * @return
	 */
	public int insert(Object objBean, int db) throws Exception {
		
		analyzeClassData(objBean);
		
		List<FieldBean> fieldsToUse = new ArrayList<FieldBean>();
		TableQueryFields queryFields = new TableQueryFields(dataDef.getTableFields());
		String[] fieldList = queryFields.getFieldsForInserting(fieldsToUse);
		
		if (fieldList[0].equals(""))
			throw new Exception(EXCEPTION_FIELDSNOTFOUND);
		
		String insValue = "";
		for(int i=0; i<collection.size(); i++) {
			if (i>0) insValue += ",";
			insValue += "("+fieldList[1]+")";
		}
		
		// Create Prepared Statement
		String query = "INSERT INTO "+dataDef.getTableDef().table()+" ("+fieldList[0]+") VALUES "+insValue;
		
		PreparedStatement ps = bsnData.getConnection(db).prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		int psSetCont = 0;
		// Set Params into the query
		for(Iterator<?> i = collection.iterator(); i.hasNext();) {
			Object bean = i.next();
			for(FieldBean field : fieldsToUse) {
				psSetCont++;
				field.getDbParam().psSet(ps, psSetCont, bean);
			}
		}
		
		bsnData.getLog().info(ps);
		int res = ps.executeUpdate();
		
		// Retrieve generated keys
		if (dataDef.getKeyField()!=null) {
			ResultSet rs = ps.getGeneratedKeys();
			Iterator<?> i = collection.iterator();
			while(rs.next() && i.hasNext()) 
				dataDef.getKeyField().getDbParam().rsGet(rs, 1, i.next());
		}
		
		return res;
	}
	

}
