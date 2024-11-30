package com.andreabrun.vehiclemanagement.entities.services;

public interface Persistable {

	public void persist();
	
	private String getFileName() {
		return null;
	}
	
}
