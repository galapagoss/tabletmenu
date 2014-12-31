/**
 * DateTagFix.java
 * 31/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTagFix {

	/**
	 * Fix Date to use in JSTL Date with "nice" attribute
	 * @param date
	 * @return
	 */
	public static final Date fixToNice(Date date) {
		return DateTagFix.fixToZone(date, TimeZone.getDefault());
	}
	
	/**
	 * Fix Date changing Timezone and DayLight saving
	 * @param date
	 * @param zone
	 * @return
	 */
	public static final Date fixToZone(Date date, TimeZone zone) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.MILLISECOND, zone.getOffset(new Date().getTime()));
		return cal.getTime();
	}

}
