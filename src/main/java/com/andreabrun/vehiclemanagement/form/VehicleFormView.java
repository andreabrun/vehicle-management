package com.andreabrun.vehiclemanagement.form;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class VehicleFormView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private BeanValidationBinder<Vehicle> binder;
    
    public VehicleFormView() {
		this(new Vehicle());
    }

	public VehicleFormView(Vehicle vehicle) {
		
        binder = new BeanValidationBinder<>(Vehicle.class);

        List<Component> inputComponents = InputFormUtils.createInputComponents(Vehicle.class, binder);

        Button submitButton = new Button("Submit");
        
        binder.readBean(vehicle);

        submitButton.addClickListener(event -> {
        	
            if (binder.validate().isOk()) {
                try {
                    binder.writeBean(vehicle);
                    Notification.show("Veicolo creato correttamente!");
                } catch (Exception e) {
                    Notification.show("Errore! " + e.getMessage());
                }
            } else {
                Notification.show("Correggere gli errori nel Form!");
            }
        });
        
        for(Component c : inputComponents) {
        	c.getStyle().set("width", "300px");
        	add(c);
        }
        
        submitButton.getStyle().set("width", "300px");
        add(submitButton);
	}
	
	public void update(Vehicle vehicle) {
		binder.readBean(vehicle);
	}
}