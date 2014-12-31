/**
 * CurrencyBean.java
 * 21/set/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.common;

import java.text.NumberFormat;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="currency",order=1)
public class CurrencyBean {

	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* label */
	@TableOrder(id=1,type=TableOrder.TYPE_ASC,exclude="")
	private String label = "";
	/* symbol */
	private String symbol = "";
	/* html symbol */
	@TableField("symbol_html")
	private String symbolHTML = "";
	/* struts format */
	@TableField("struts_format")
	private String strutsFormat = "";
	/* digit */
	private int digit = 0;
	/* decimal separator */
	@TableField("separator_decimal")
	private String separatorDecimal = "";
	/* thousand separator */
	@TableField("separator_thousand")
	private String separatorThousand = "";
	
	
	/**
	 * Empty Constructor
	 */
	public CurrencyBean() {	}
	
	
	/**
	 * @param id
	 */
	public CurrencyBean(int id) {
		this.id = id;
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
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the strutsFormat
	 */
	public String getStrutsFormat() {
		return strutsFormat;
	}
	/**
	 * @param strutsFormat the strutsFormat to set
	 */
	public void setStrutsFormat(String strutsFormat) {
		this.strutsFormat = strutsFormat;
	}
	/**
	 * @return the digit
	 */
	public int getDigit() {
		return digit;
	}
	/**
	 * @param digit the digit to set
	 */
	public void setDigit(int digit) {
		this.digit = digit;
	}
	/**
	 * @return the separatorDecimal
	 */
	public String getSeparatorDecimal() {
		return separatorDecimal;
	}
	/**
	 * @param separatorDecimal the separatorDecimal to set
	 */
	public void setSeparatorDecimal(String separatorDecimal) {
		this.separatorDecimal = separatorDecimal;
	}
	/**
	 * @return the separatorThousand
	 */
	public String getSeparatorThousand() {
		return separatorThousand;
	}
	/**
	 * @param separatorThousand the separatorThousand to set
	 */
	public void setSeparatorThousand(String separatorThousand) {
		this.separatorThousand = separatorThousand;
	}


	/**
	 * @return the symbolHTML
	 */
	public String getSymbolHTML() {
		return symbolHTML;
	}


	/**
	 * @param symbolHTML the symbolHTML to set
	 */
	public void setSymbolHTML(String symbolHTML) {
		this.symbolHTML = symbolHTML;
	}

	/**
	 * HTML Formatting
	 * @param value
	 * @param symbol
	 * @return
	 */
	public String format(double value, boolean symbol) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(this.digit);
		return nf.format(value) + (symbol ? " "+this.symbolHTML : "");
	}
}
