/**
 * CacheGarbage.java
 * 17/feb/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.scheduler.thread;

import java.util.Iterator;

import com.oxybay.web.beans.system.caching.CacheBean;
import com.oxybay.web.beans.system.caching.CacheElemBean;
import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.VarKeys;
import com.oxybay.web.resources.scheduler.SchedulerThread;

public class CacheGarbage extends SchedulerThread {
	
	/* cache bean */
	private CacheBean cache = null;

	/* (non-Javadoc)
	 * @see it.dotland.tci.resources.scheduler.SchedulerThread#doLogic()
	 */
	@Override
	public boolean doLogic() {
		if (cache != null)
			for(Iterator<CacheElemBean> i = cache.getIterator(); i.hasNext();) {
				CacheElemBean elem = i.next();
				if (elem.isExpired()) {
					cache.removeData(elem.getKey());
				}
			}
			
		return true;
	}

	
	/* (non-Javadoc)
	 * @see it.dotland.tci.resources.scheduler.SchedulerThread#initialize()
	 */
	@Override
	protected boolean initialize() {
		try { cache = (CacheBean)Resources.getInstance().getServletContext().getAttribute(VarKeys.APP_CACHE);} catch(Exception e) {}
		return (cache!=null);
	}

	
}
