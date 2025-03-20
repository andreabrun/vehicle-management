package com.andreabrun.vehiclemanagement.form;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.dom.Style;

public class VehicleFormView extends VerticalLayout implements InputForm<Vehicle> {

    private static final long serialVersionUID = 1L;

    private BeanValidationBinder<Vehicle> binder;
    
    private Vehicle vehicle;
    private VehicleContainer vc;
    
    private List<Component> inputComponents;
    private Button buttonSave;
    private Button buttonClear;
    private Div buttonsContainer;
    
    private boolean enabled = true;
    
    public VehicleFormView() {
		this(new Vehicle(), null);
    }

	public VehicleFormView(Vehicle vehicle, VehicleContainer vc) {
		
		this.vehicle = vehicle;
		this.vc = vc;
		
        binder = new BeanValidationBinder<>(Vehicle.class);

        inputComponents = InputFormUtils.createInputComponents(Vehicle.class, binder, vc);
        
        binder.readBean(this.vehicle);
        
        for(Component c : inputComponents) {
        	add(c);
        }
        
        buttonsContainer = new Div();
        
        buttonSave = new Button(MessagesUtils.SAVE);
		buttonSave.addClickListener(this::executeSave);
		buttonSave.getElement().removeProperty("disabled");
		StyleUtils.applyStyle(buttonSave, StyleUtils.FORM_LEFT_BUTTON_STYLE);
		buttonsContainer.add(buttonSave);
		
		buttonClear = new Button(MessagesUtils.CLEAR);
		buttonClear.addClickListener(this::executeClear);
		buttonClear.getElement().removeProperty("disabled");
		StyleUtils.applyStyle(buttonClear, StyleUtils.FORM_RIGHT_BUTTON_STYLE);
		buttonsContainer.add(buttonClear);
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
                return true;
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		return false;
	}
	
	private void setInputComponentsEnabled(boolean enabled) {
		for(Component c : inputComponents) {
			if(!enabled)
				c.getElement().setProperty("disabled", "true");
			else 
				c.getElement().removeProperty("disabled");
		}
	}
	
	public void applyStyleToInputComponents(Style style) {
		for(Component c : inputComponents) {
			StyleUtils.applyStyle(c, style);
        }
	}
	
	public void executeEnable(boolean enabled) {
		this.setInputComponentsEnabled(enabled);
		if(enabled) {
			add(buttonsContainer);
		} else {
			remove(buttonsContainer);
		}
		this.enabled = enabled;
	}
	
	public void executeSave(ClickEvent<?> e) {
		boolean res = this.save();
		if(res) {
			Notification.show(MessagesUtils.VEHICLE_SAVED_SUCCESS);
		} else {
			Notification.show(MessagesUtils.ERROR);
		}
	}
	
	public void executeClear(ClickEvent<?> e) {
		this.clear();
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public boolean save() {
		if(this.validate()) {
			this.vc.persist();
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		binder.readBean(this.vehicle);
	}
}