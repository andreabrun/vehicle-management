package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.AddVehicleFormView;
import com.andreabrun.vehiclemanagement.form.VehicleFormView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	
	Button buttonOpenAddVehicle;
	AddVehicleFormView addVehicleFormView;
	Button buttonCloseAddVehicle;
	
	public ConfigurationView() {
		
		init();

		buttonOpenAddVehicle = new Button("Aggiungi Veicolo");
		buttonOpenAddVehicle.addClickListener( e -> {
			addVehicleFormView = new AddVehicleFormView();
			buttonOpenAddVehicle.setEnabled(false);
			add(addVehicleFormView);
			add(buttonCloseAddVehicle);
		});
		
		buttonCloseAddVehicle = new Button("Annulla");
		buttonCloseAddVehicle.addClickListener( e -> {
			remove(addVehicleFormView);
			buttonOpenAddVehicle.setEnabled(true);
			remove(buttonCloseAddVehicle);
		});
		
		add(buttonOpenAddVehicle);
		
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
	}
}
