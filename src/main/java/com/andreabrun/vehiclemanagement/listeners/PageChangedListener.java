package com.andreabrun.vehiclemanagement.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.andreabrun.vehiclemanagement.VehicleManagementVehicleContainerPage;
import com.andreabrun.vehiclemanagement.events.PageChangedEvent;

@Component
public class PageChangedListener {
	
	@EventListener
    public void onPageChanged(PageChangedEvent evt) {
		if(evt.getSource() instanceof VehicleManagementVehicleContainerPage) {
			VehicleManagementVehicleContainerPage vmp = (VehicleManagementVehicleContainerPage) evt.getSource();
			vmp.updateComponents();
        }
    }
}
