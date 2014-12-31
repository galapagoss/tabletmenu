package com.oxybay.web.beans.menu.daily;

public class DailyMenuItem {
	
	public static enum ItemType {PRODUCT, CATEGORY, TAG}
	
	private int id = 0;
	
	private boolean active;
	
	private String title;
	
	private ItemType type;
	
	private int order;
	
	public DailyMenuItem() {}

	public DailyMenuItem(ItemType type, int id, boolean active, String title, int order) {
		this.type = type;
		this.id = id;
		this.active = active;
		this.title = title;
		this.order = order;
	}

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public ItemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ItemType type) {
		this.type = type;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
