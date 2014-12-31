/**
 * @author Gaetano Negrone
 * @Web http://www.dotland.it
 * @Email info@dotland.it
 */

/**
 * Business logic for manage event's visitors
 */

package com.oxybay.web.business.system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.oxybay.web.beans.system.SchedulerBean;
import com.oxybay.web.business.system.root.RootBsn;

public class SchedulerBsn extends RootBsn {
  
  /**
   * Empty Constructor
   */
  public SchedulerBsn() {
  	super();
  }
  
  /**
   * Returns a SchedulerBean containing all the data relative to last processLabel process execution
   * @param processLabel
   * @return null if it doesn't find any execution
   */
  public SchedulerBean getLastExecution(String processLabel) {
  	
  	
  	SchedulerBean ritorna = null;

  	PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      ps = getConnection().prepareStatement("SELECT process_label,description,execution_time,start_time,process_type,interval_time,result " +
      													"FROM log_scheduler " +
      													"WHERE process_label = ? " +
      													"ORDER BY start_time DESC LIMIT 1");
      
      ps.setString(1, processLabel);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
      	processLabel 				= rs.getString("process_label");	
      	String description 	= rs.getString("description");
      	long executionTime 	= rs.getLong("execution_time");
      	Date startTime 			= new Date(rs.getTimestamp("start_time").getTime());
      	int processType 		= rs.getInt("process_type");
      	int interval				= rs.getInt("interval_time");
      	boolean resut 			= rs.getBoolean("result");
      	
      	ritorna = new SchedulerBean(processLabel, description, executionTime, startTime, processType, interval,resut);
      	
      }
      
    } catch(Exception e) {
    	traceQueryError(ps,e);
    } finally {
      try { if (rs != null) { rs.close(); rs = null; } } catch(Exception ex) {}
      try { if (ps != null) { ps.close(); ps = null; } } catch(Exception ex) {}
    }
    
    return ritorna;
  
  }

  /**
   * <p>Add a new account to the user</p>
   * @param  user	User information read from authentication service
   * @return New AccountBean generated; null if an error occurred
   */
  public int insertExecution(SchedulerBean scheduler) {
  	
    int inserito = -1;
  	
  	PreparedStatement ps = null;
    ResultSet rs = null;
   
    try {
      ps = getConnection().prepareStatement("INSERT INTO log_scheduler (process_label, description, execution_time, start_time, process_type, interval_time, result) VALUES (?, ?, ?, ?, ?, ?, ?)");
      ps.setString(1, scheduler.getProcessLabel());
      ps.setString(2, scheduler.getDescription());
      ps.setLong(3, scheduler.getExecutionTime());
      ps.setTimestamp(4, new java.sql.Timestamp(scheduler.getStartTime().getTime()));
      ps.setInt(5, scheduler.getProcessType());
      ps.setLong(6, scheduler.getIntervalTime());
      ps.setBoolean(7, scheduler.isResult());
      
      inserito = ps.executeUpdate();
	      
    } catch(Exception e) {
    	inserito = -1;  
    	traceQueryError(ps,e);
    } finally {
    	try { if (rs != null) { rs.close(); rs = null; } } catch(Exception ex) {}
      try { if (ps != null) { ps.close(); ps = null; } } catch(Exception ex) {}
    }
  
    return inserito;
  }
  
  
}