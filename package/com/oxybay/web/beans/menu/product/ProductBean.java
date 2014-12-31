/**
 * ProductBean.java
 * 22/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.menu.discount.DiscountContainerBean;
import com.oxybay.web.business.system.common.ImageResize;
import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableRef;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="product",order=1)
public class ProductBean {

	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* img */
	@TableRef("")
	@ImageResize(w=480,h=360,crop=true)
	private AttachBean img = null;
	/* active */
	private boolean active = false;
	/* price */
	private float price = 0;
	/* main language */
	@TableType(TableType.SKIP)
	private ProductLangBean mainLang = new ProductLangBean();
	/* available languages */
	@TableType(TableType.SKIP)
	private Set<String> languages = new HashSet<String>();
	/* categories */
	@TableType(TableType.SKIP)
	private Set<Integer> categories = new HashSet<Integer>();
	/* tags */
	@TableType(TableType.SKIP)
	private Set<Integer> tags = new HashSet<Integer>();
	/* discounts */
	@TableType(TableType.SKIP)
	private DiscountContainerBean discounts = new DiscountContainerBean();
	/* order */
	@TableField("item_order")
	@TableOrder(id=1,type=TableOrder.TYPE_ASC,exclude="0")
	private int order = 0;
	
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
	
	/* languages incomplete */
	@TableType(TableType.SKIP)
	private boolean langFault = false;
	/* list of tags to associate and create */
	@TableType(TableType.SKIP)
	private String newTags = "";
	/* warning */
	private enum Warning {NOIMAGE, NOTRASLATION};
	
	/**
	 * Constructor
	 */
	public ProductBean() {	}


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
	 * @return the img
	 */
	public AttachBean getImg() {
		return img;
	}


	/**
	 * @param img the img to set
	 */
	public void setImg(AttachBean img) {
		this.img = img;
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
	 * @return the mainLang
	 */
	public ProductLangBean getMainLang() {
		return mainLang;
	}


	/**
	 * @param mainLang the mainLang to set
	 */
	public void setMainLang(ProductLangBean mainLang) {
		this.mainLang = mainLang;
	}


	/**
	 * @return the languages
	 */
	public Set<String> getLanguages() {
		return languages;
	}


	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(Set<String> languages) {
		this.languages = languages;
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
	 * @return the categories
	 */
	public Set<Integer> getCategories() {
		return categories;
	}


	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Integer> categories) {
		this.categories = categories;
	}


	/**
	 * @return the langFault
	 */
	public boolean isLangFault() {
		return langFault;
	}


	/**
	 * @param langFault the langFault to set
	 */
	public void setLangFault(boolean langFault) {
		this.langFault = langFault;
	}

	
	/**
	 * @return the tags
	 */
	public Set<Integer> getTags() {
		return tags;
	}


	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<Integer> tags) {
		this.tags = tags;
	}


	/**
	 * @return the newTags
	 */
	public String getNewTags() {
		return newTags;
	}


	/**
	 * @param newTags the newTags to set
	 */
	public void setNewTags(String newTags) {
		this.newTags = newTags;
	}


	/**
	 * @return the discounts
	 */
	public DiscountContainerBean getDiscounts() {
		return discounts;
	}


	/**
	 * @param discounts the discounts to set
	 */
	public void setDiscounts(DiscountContainerBean discounts) {
		this.discounts = discounts;
	}


	/**
	 * Get Warning
	 * @return
	 */
	public Warning getWarning() {
		if (this.langFault)
			return Warning.NOTRASLATION;
		if (this.img==null)
			return Warning.NOIMAGE;
		return null;
	}
}
