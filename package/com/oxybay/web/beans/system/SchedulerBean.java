/**
 * 
 */
package com.oxybay.web.beans.system;

import java.util.Date;

import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.resources.scheduler.SchedulerThread;

/**
 * @author ing. Alberto Plebani, Gaetano Negrone
 *
 */
public class SchedulerBean {

	@TableField("process_label")
	private String processLabel = "";	
	
	private String description 	= "";
	
	@TableField("execution_time")
	private long executionTime 	= 0;
	
	@TableField("start_time")
	private Date startTime 			= null;
	
	@TableField("process_type")
	private int processType 		= -1;
	
	@TableField("interval_time")
	private int intervalTime		= -1;
	
	private boolean result			= false;
	
	
	
	public SchedulerBean() {}
	
	public SchedulerBean(String processLabel,	String description, long executionTime, Date startTime, int processType, int intervalTime, boolean result) {
		this.processLabel	 = processLabel;
		this.description	 = description;
		this.executionTime = executionTime;
		this.startTime		 = startTime;
		this.processType	 = processType;
		this.intervalTime	 = intervalTime;
		this.result				 = result;
	}

	public int getProcessType() {
		return processType;
	}

	public void setProcessType(int processType) {
		this.processType = processType;
	}

	public String getProcessLabel() {
		return processLabel;
	}

	public void setProcessLabel(String processLabel) {
		this.processLabel = processLabel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
	
	/**
	 * <P>
	 * Get result field
	 * <P>
	 * @return Returns the result.
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * <P>
	 * Set result field
	 * <P> 
	 * @param result The result to set.
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	

	public void loadFromThread(SchedulerThread schedulerThread, boolean result) {
		this.processLabel	 = schedulerThread.getProcessLabel();
		this.description	 = schedulerThread.getDescription();
		this.executionTime = schedulerThread.getExecutionTime();
		this.startTime		 = new java.util.Date(schedulerThread.getStartTime());
		this.processType	 = schedulerThread.getProcessType();
		this.intervalTime	 = (int)schedulerThread.getMillisInterval()/1000;
		this.result				 = result;
	}

	
}
