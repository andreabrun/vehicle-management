package com.andreabrun.vehiclemanagement.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import io.micrometer.common.util.StringUtils;

public class VehicleFormView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    public VehicleFormView() {
		this(new Vehicle());
    }

	public VehicleFormView(Vehicle vehicle) {
		
        BeanValidationBinder<Vehicle> binder = new BeanValidationBinder<>(Vehicle.class);

        List<Component> inputComponents = InputFormUtils.createInputComponents(Vehicle.class, binder);

        Button submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        binder.readBean(vehicle);

        submitButton.addClickListener(event -> {
            if (binder.validate().isOk()) {
                try {
                    binder.writeBean(vehicle);
                    Notification.show("Vehicle details submitted successfully: " + vehicle.toString());
                } catch (Exception e) {
                    Notification.show("Failed to submit vehicle details: " + e.getMessage());
                }
            } else {
                Notification.show("Please correct the errors in the form.");
            }
        });
        
        for(Component c : inputComponents) {
        	add(c);
        }
        add(submitButton);
	}
}