package com.andreabrun.vehiclemanagement.form;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class VehicleFormView extends VerticalLayout implements InputForm<Vehicle> {

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 300;

    private BeanValidationBinder<Vehicle> binder;
    
    private Vehicle vehicle;
    private VehicleContainer vc;
    
    public VehicleFormView() {
		this(new Vehicle(), null);
    }

	public VehicleFormView(Vehicle vehicle, VehicleContainer vc) {
		
		this.vehicle = vehicle;
		this.vc = vc;
		
        binder = new BeanValidationBinder<>(Vehicle.class);

        List<Component> inputComponents = InputFormUtils.createInputComponents(Vehicle.class, binder, vc);
        
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