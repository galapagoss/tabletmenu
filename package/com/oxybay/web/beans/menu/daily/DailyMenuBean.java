package com.oxybay.web.beans.menu.daily;

import java.util.Date;
import java.util.List;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="daily_menu",order=1)
public class DailyMenuBean {
	
	/* id */
	@TableType(TableType.KEY)
	@TableField("_ID")
	private int id = 0;
	
	/* active */
	private boolean active = false;
	
	/* price */
	private float price = 0;
	
	@TableField("week_day")
	@TableOrder(id=1,type=TableOrder.TYPE_ASC,exclude="0")
	private int weekDay = 0;
	
	@TableField("day_type")
	@TableOrder(id=1,type=TableOrder.TYPE_ASC,exclude="0")
	private int dayType = 0;
	
	/* creation */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private Date creationDate = null;
	/* update date */
	@TableType(TableType.UPDATING)
	@TableField("update_date")
	private Date updateDate = null;
	/* deleted */
	@TableType(TableType.DELETING)
	private Date deleted = null;
	
	@TableType(TableType.SKIP)
	private List<DailyMenuItem> items;

	public DailyMenuBean() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the weekDay
	 */
	public int getWeekDay() {
		return weekDay;
	}

	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the deleted
	 */
	public Date getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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
	 * @return the dayType
	 */
	public int getDayType() {
		return dayType;
	}

	/**
	 * @param dayType the dayType to set
	 */
	public void setDayType(int dayType) {
		this.dayType = dayType;
	}

	/**
	 * @return the items
	 */
	public List<DailyMenuItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<DailyMenuItem> items) {
		this.items = items;
	}

}