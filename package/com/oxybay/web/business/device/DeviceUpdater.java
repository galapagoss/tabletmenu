package com.oxybay.web.business.device;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.oxybay.web.beans.menu.device.DeviceBean;
import com.oxybay.web.resources.keys.CustomKeys;
import com.oxybay.web.resources.keys.CustomKeys.GCMMessage;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 *
 */
public class DeviceUpdater {
	
	private static DeviceUpdater instance;
	
	private static final Executor threadPool = Executors.newFixedThreadPool(5);
	
	private static final int MULTICAST_SIZE = 1000;
	
	private Sender sender;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	public static final DeviceUpdater getInstance() {
		if (instance == null) {
			instance = new DeviceUpdater();
		}
		return instance;
	}

	protected DeviceUpdater() {
		sender = new Sender(CustomKeys.GCM_API_KEY);
	}
	
	
	public void notifyUpdate(List<DeviceBean> devices) {
		int total = devices.size();
	  List<String> partialDevices = new ArrayList<String>(total);
	  int counter = 0;
	  for (DeviceBean device : devices) {
	    counter++;
	    partialDevices.add(device.getDeviceId());
	    int partialSize = partialDevices.size();
	    if (partialSize == MULTICAST_SIZE || counter == total) {
	      asyncSend(partialDevices);
	      partialDevices.clear();
	    }
	  }
	}

	private void asyncSend(List<String> partialDevices) {
    // make a copy
    final List<String> devices = new ArrayList<String>(partialDevices);
    threadPool.execute(new Runnable() {

      public void run() {
        Message message = new Message.Builder().collapseKey(""+GCMMessage.UPDATEAVAILABLE).build();
        MulticastResult multicastResult;
        try {
          multicastResult = sender.send(message, devices, 5);
        } catch (Exception e) {
          log.error("Error posting messages", e);
          return;
        }
        List<Result> results = multicastResult.getResults();
        log.info("results size="+results.size());
        // analyze the results
        for (int i = 0; i < devices.size(); i++) {
          String regId = devices.get(i);
          Result result = results.get(i);
          String messageId = result.getMessageId();
          log.info("messageId="+messageId);
          if (messageId != null) {
            log.info("Succesfully sent message to device: " + regId + "; messageId = " + messageId);
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId != null) {
              // same device has more than on registration id: update it
              log.info("canonicalRegId " + canonicalRegId);
              //TODO
              //Datastore.updateRegistration(regId, canonicalRegId);
            }
          } else {
            String error = result.getErrorCodeName();
            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
              // application has been removed from device - unregister it
              log.info("Unregistered device: " + regId);
              //TODO
              //Datastore.unregister(regId);
            } else {
              log.error("Error sending message to " + regId + ": " + error);
            }
          }
        }
      }});
  }

}