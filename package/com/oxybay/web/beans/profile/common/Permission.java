package com.oxybay.web.beans.profile.common;

import java.io.Serializable;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 */
public enum Permission implements Serializable {
	
	GLOBAL_PERMISSION_VIEW(1), GLOBAL_PERMISSION_ADD(2), GLOBAL_PERMISSION_EDIT(3);
	
	private final int id;

  private Permission(int id) {
  	this.id = id;
  }

  public int getId() {
    return id;
  }
  
  public static Permission getById(int id) {
    for (Permission permission : values()) {
    	if (permission.id == id) {
    		return permission;
      }
    }

    throw new IllegalArgumentException("Specified id does not relate to a valid permission");
  }
  
}