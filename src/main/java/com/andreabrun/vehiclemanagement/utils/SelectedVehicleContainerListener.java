package com.andreabrun.vehiclemanagement.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.andreabrun.vehiclemanagement.VehicleManagementPage;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;

public class SelectedVehicleContainerListener implements PropertyChangeListener {
	
	VehicleManagementPage vmp;
	
	public SelectedVehicleContainerListener(VehicleManagementPage page) {
		this.vmp = page;
	}
	
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(vmp != null) {
        	vmp.setVehicleContainer((VehicleContainer) evt.getNewValue());
        	vmp.updateComponents();
        }
    }
}
