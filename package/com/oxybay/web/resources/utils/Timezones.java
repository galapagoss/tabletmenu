/**
 * Timezones.java
 * 22/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Timezones {
	
	private static final String TIMEZONE_ID_PREFIXES = "^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*";

	/* zones */
	private static List<TimeZone> zones = null;
	
	
	/**
	 * Get TimeZone List
	 */
	public static List<TimeZone> getList() {
		return (zones!=null) ? zones : calculateList();
	}
	
	/**
	 * Obtain TimeZone List
	 * @return
	 */
	private static List<TimeZone> calculateList() {
		List<TimeZone> timeZones = new ArrayList<TimeZone>();
    final String[] timeZoneIds = TimeZone.getAvailableIDs();
    for (final String id : timeZoneIds) {
       if (id.matches(TIMEZONE_ID_PREFIXES)) {
          timeZones.add(TimeZone.getTimeZone(id));
       }
    }
    Collections.sort(timeZones, new Comparator<TimeZone>() {
       public int compare(final TimeZone a, final TimeZone b) {
          return a.getID().compareTo(b.getID());
       }
    });
    cacheData(timeZones);
    return timeZones;
	}
	
	/**
	 * Cache Data
	 * @param zoneList
	 */
	private static synchronized void cacheData(List<TimeZone> zoneList) {
		if (zones==null)
			zones = zoneList;
	}
	
	/**
	 * Get OffSet GMT (with Daylight)
	 * @param zone
	 * @return
	 */
	public static String offsetGMT(TimeZone zone){
		int min = zone.getOffset(new Date().getTime())/(60*1000);
		int h = min/60;
		min = min-h*60;
		return (h>=0 ? "+":"-") + (h<10 ? "0" : "") + Math.abs(h) + (min<10 ? "0"+min : min); 
	}
	
}
