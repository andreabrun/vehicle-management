package com.andreabrun.vehiclemanagement.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.andreabrun.vehiclemanagement.VehicleManagementVehicleContainerPage;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;

public class SelectedVehicleContainerListener implements PropertyChangeListener {
	
	VehicleManagementVehicleContainerPage vmp;
	
	public SelectedVehicleContainerListener(VehicleManagementVehicleContainerPage page) {
		this.vmp = page;
	}
	
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	if(evt.getPropertyName().equals(VehicleSessionBean.SELECTED)) {
    		if(vmp != null) {
            	vmp.setVehicleContainer((VehicleContainer) evt.getNewValue());
            	vmp.updateComponents();
            }
    	}
    }
}
