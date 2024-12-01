package com.andreabrun.vehiclemanagement.entities.services;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;

public class VehicleSessionBean {
	
	public static final String VEHICLECONTAINERS = "vehicleContainers";
	public static final String SELECTED = "vehicleContainerSelected";
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private List<VehicleContainer> data;
	
	private VehicleContainer selected;
	
	public List<VehicleContainer> getData() {
		return this.data;
	}
	
	public void setData(List<VehicleContainer> data) {
		List<VehicleContainer> oldData = this.data;
		this.data = data;
		pcs.firePropertyChange(VEHICLECONTAINERS, oldData, this.data);
	}
	
	public VehicleContainer getSelected() {
		return this.selected;
	}

	public void setSelected(VehicleContainer vc) {
		VehicleContainer oldSelected = this.selected;
		this.selected = vc;
		pcs.firePropertyChange(SELECTED, oldSelected, selected);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    public void init() {
    	
    	List<VehicleContainer> vc = VehicleContainer.findAll();
		this.setData(vc);
		this.selected = null;
		
    }
    
    public void update() {
    	
    	List<VehicleContainer> vc = VehicleContainer.findAll();
		this.setData(vc);
		
		if(!vc.contains(this.selected)) {
			this.selected = null;
		}
		
    }
}
