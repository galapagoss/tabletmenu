/**
 * Discount.java
 * 12/set/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.discount;

import java.sql.Time;
import java.util.Date;

import com.oxybay.web.resources.keys.ConfigKeys;
import com.oxybay.web.resources.utils.Utils;

public class DiscountBean {
	
	public static final String SQL_DISCOUNT_TAG				 		= "T";
	public static final String SQL_DISCOUNT_TAG_PRODUCT		= "P";
	public static final String SQL_DISCOUNT_TAG_CATEGORY	= "C";
	public static final String SQL_DISCOUNT_PRODUCT				= "P";
	public static final String SQL_DISCOUNT_CATEGORY			= "C";

	public enum DiscountType { PRODUCT("product_2_discount","id_product"),CATEGORY("category_2_discount","id_category"),TAG("tag_2_discount","id_tag");
	 public String table;
	 public String field;
	 private DiscountType(String t, String f) { table=t; field=f;}
	}
	
	/* id */
	private long id = 0;
	/* recurring */
	private byte recurring = 0;
	/* from time */
	private Time fromTime = null;
	/* to time */
	private Time toTime = null;
	/* fixed price */
	private float fixedPrice = 0f;
	/* discount percentage */
	private int discountPrice = 0;
	/* active */
	private boolean active = false;
	
	/* extra type (for inheritance) */
	private DiscountType type = DiscountType.PRODUCT;
	/* extra label (for inheritance) */
	private String label = "";
	
	
	/**
	 * Empty Constructor
	 */
	public DiscountBean() {
	}

	/**
	 * @param id
	 * @param recurring
	 * @param fromTime
	 * @param toTime
	 * @param fixedPrice
	 * @param discountPrice
	 * @param active
	 */
	public DiscountBean(long id, byte recurring, Time fromTime, Time toTime,float fixedPrice, int discountPrice, boolean active) {
		this.id = id;
		this.recurring = recurring;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.fixedPrice = fixedPrice;
		this.discountPrice = discountPrice;
		this.active = active;
	}

	
	/**
	 * @param id
	 * @param recurring
	 * @param fromTime
	 * @param toTime
	 * @param fixedPrice
	 * @param discountPrice
	 * @param active
	 * @param type
	 * @param label
	 */
	public DiscountBean(long id, byte recurring, Time fromTime, Time toTime, float fixedPrice, int discountPrice, boolean active, DiscountType type, String label) {
		this.id = id;
		this.recurring = recurring;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.fixedPrice = fixedPrice;
		this.discountPrice = discountPrice;
		this.active = active;
		this.type = type;
		this.label = label;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the recurring
	 */
	public byte getRecurring() {
		return recurring;
	}

	/**
	 * @param recurring the recurring to set
	 */
	public void setRecurring(byte recurring) {
		this.recurring = recurring;
	}

	
	
	/**
	 * @return the fromTime
	 */
	public Time getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * @return the toTime
	 */
	public Time getToTime() {
		return toTime;
	}

	/**
	 * @param toTime the toTime to set
	 */
	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	/**
	 * @return the fixedPrice
	 */
	public float getFixedPrice() {
		return fixedPrice;
	}

	/**
	 * @param fixedPrice the fixedPrice to set
	 */
	public void setFixedPrice(float fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	/**
	 * @return the discountPrice
	 */
	public int getDiscountPrice() {
		return discountPrice;
	}

	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the type
	 */
	public DiscountType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DiscountType type) {
		this.type = type;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Check if weekday passed is selected
	 * @param weekday
	 * @return
	 */
	public boolean isRecurringDay(int weekday) {
		return (this.recurring & (int)Math.pow(2, weekday)) >0;
	}
	
	
	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTimeStr(String fromTime) {
		Date d = Utils.tryStrToDate(fromTime, ConfigKeys.TIME_FORMAT_SHORT);
		if (d != null)
			this.fromTime = new Time(d.getTime()); 
	}

	/**
	 * @param toTime the toTime to set
	 */
	public void setToTimeStr(String toTime) {
		Date d = Utils.tryStrToDate(toTime, ConfigKeys.TIME_FORMAT_SHORT);
		if (d != null)
			this.toTime = new Time(d.getTime());
	}
	
	/**
	 * Get Discount Type from SQL Value
	 * @param type
	 * @return
	 */
	public static DiscountType getTypeFromSql(String type) {
		if (SQL_DISCOUNT_TAG.equals(type))
			return DiscountType.TAG;
		if (SQL_DISCOUNT_CATEGORY.equals(type))
			return DiscountType.CATEGORY;
		return DiscountType.PRODUCT;
	}
}
