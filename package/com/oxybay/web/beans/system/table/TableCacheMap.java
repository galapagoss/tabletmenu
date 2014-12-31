/**
 * TableCacheList.java
 * 20/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table;

import java.util.LinkedHashMap;

public class TableCacheMap extends LinkedHashMap<Class<?>, TableDataDefBean> {

	private static final long serialVersionUID = -5960011368648754699L;

	/* capacity */
	private int capacity = 0;

	/**
	 * @param capacity
	 */
	public TableCacheMap(int capacity) {
		super();
		this.capacity = capacity;
	}

	/* (non-Javadoc)
	 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
	 */
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<Class<?>, TableDataDefBean> eldest) {
		return size() > this.capacity;
	}
	
	
}
