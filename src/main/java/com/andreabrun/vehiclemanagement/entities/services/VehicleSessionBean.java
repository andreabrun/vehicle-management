package com.andreabrun.vehiclemanagement.entities.services;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;

public class VehicleSessionBean {
	
	private List<VehicleContainer> data;
	
	private VehicleContainer selected;
	
	public List<VehicleContainer> getData() {
		return this.data;
	}
	
	public void setData(List<VehicleContainer> data) {
		this.data = data;
	}
	
	public VehicleContainer getSelected() {
		return this.selected;
	}

	public void setSelected(VehicleContainer vc) {
		this.selected = vc;
	}
}
