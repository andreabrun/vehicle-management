package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;

/**
 * Interfaccia per le pagine che dipendono dal VehicleContainer selezionato
 */
public interface VehicleManagementVehicleContainerPage {
	
	public void init();
	
	public void updateComponents();
	
	public void setVehicleContainer(VehicleContainer vc);
}
