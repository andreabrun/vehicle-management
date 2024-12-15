package com.andreabrun.vehiclemanagement.form;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class VehicleFormView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 300;

    private BeanValidationBinder<Vehicle> binder;
    
    private Vehicle vehicle;
    
    public VehicleFormView() {
		this(new Vehicle());
    }

	public VehicleFormView(Vehicle vehicle) {
		
		this.vehicle = vehicle;
		
        binder = new BeanValidationBinder<>(Vehicle.class);

        List<Component> inputComponents = InputFormUtils.createInputComponents(Vehicle.class, binder);
        
        binder.readBean(this.vehicle);
        
        for(Component c : inputComponents) {
        	c.getStyle().set("width", WIDTH + "px");
        	add(c);
        }
	}
	
	public void update(Vehicle vehicle) {
		this.vehicle = vehicle;
		binder.readBean(this.vehicle);
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public boolean validate() {
		if (binder.validate().isOk()) {
            try {
                binder.writeBean(this.vehicle);
                //Notification.show("Veicolo creato correttamente!");
                return true;
            } catch (Exception e) {
                //Notification.show("Errore! " + e.getMessage());
            	e.printStackTrace();
            }
        }
		return false;
	}
}