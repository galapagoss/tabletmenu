/**
 * CacheMonitorBean.java
 * 24/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.caching.monitor;

import java.util.ArrayList;
import java.util.List;

public class CacheMonitorBean {

	/* requests */
	private long requests = 0;
	/* success requests */
	private long requestsOK = 0;
	/* success hit rate */
	private float hitRate = 0;
	/* bean list */
	private List<CacheMonitorElemBean> items = new ArrayList<CacheMonitorElemBean>();
	
	
	
	/**
	 * Constructor
	 * @param requests
	 * @param requestsOK
	 * @param hitRate
	 * @param items
	 */
	public CacheMonitorBean(long requests, long requestsOK, float hitRate,List<CacheMonitorElemBean> items) {
		this.requests = requests;
		this.requestsOK = requestsOK;
		this.hitRate = hitRate;
		this.items = items;
	}
	/**
	 * @return the requests
	 */
	public long getRequests() {
		return requests;
	}
	/**
	 * @return the requestsOK
	 */
	public long getRequestsOK() {
		return requestsOK;
	}
	/**
	 * @return the items
	 */
	public List<CacheMonitorElemBean> getItems() {
		return items;
	}
	/**
	 * @return the hitRate
	 */
	public float getHitRate() {
		return hitRate;
	}
		
}
