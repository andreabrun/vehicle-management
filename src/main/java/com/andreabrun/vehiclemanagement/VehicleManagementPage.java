package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;

public interface VehicleManagementPage {
	
	public void init();
	
	public void updateComponents();
	
	public void setVehicleContainer(VehicleContainer vc);
}
