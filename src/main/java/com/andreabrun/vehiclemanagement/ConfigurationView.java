package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.dialog.DialogAddNewVehicle;
import com.andreabrun.vehiclemanagement.dialog.DialogDeleteVehicle;
import com.andreabrun.vehiclemanagement.dialog.DialogEditVehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout implements VehicleManagementPage {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	private VehicleContainer vc;
	
	FlexLayout vehiclesConfiguration;
	Button buttonOpenAddVehicle;
	Button buttonOpenDeleteVehicle;
	Button buttonOpenEditVehicle;
	
	DialogAddNewVehicle dialogAddNewVehicle;
	DialogDeleteVehicle dialogDeleteVehicle;
	DialogEditVehicle dialogEditVehicle;
	
	public ConfigurationView() {
		init();
		initComponents();
	}
	
	public void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		this.vc = vsbean.getSelected();
		
		SelectedVehicleContainerListener listener = new SelectedVehicleContainerListener(this);
		vsbean.addPropertyChangeListener(listener);
	}
		
	public void setVehicleContainer(VehicleContainer vc) {
		this.vc = vc;
	}
	
	public void updateComponents() {
		clearComponents();
		initComponents();
	}
	
	private void clearComponents() {
		this.removeAll();
	}
	
	private void initComponents() {
		initDialogs();
		add(dialogAddNewVehicle);
		add(dialogDeleteVehicle);
		add(dialogEditVehicle);
		
		initVehiclesConfiguration();
		add(vehiclesConfiguration);
	}
	
	
	private void initVehiclesConfiguration() {
		
		vehiclesConfiguration = new FlexLayout();

		// ADD NEW VEHICLE
		buttonOpenAddVehicle = new Button("Aggiungi veicolo");
		buttonOpenAddVehicle.addClickListener( e -> {
			dialogAddNewVehicle.open();
		});
		buttonOpenAddVehicle.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenAddVehicle);
		
		// DELETE VEHICLE
		buttonOpenDeleteVehicle = new Button("Elimina veicolo");
		buttonOpenDeleteVehicle.addClickListener( e -> {
			dialogDeleteVehicle.open();
		});
		buttonOpenAddVehicle.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenDeleteVehicle);
		
		// DELETE VEHICLE
		buttonOpenEditVehicle = new Button("Modifica veicolo");
		buttonOpenEditVehicle.addClickListener( e -> {
			dialogEditVehicle.open();
		});
		buttonOpenEditVehicle.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenEditVehicle);
		
		vehiclesConfiguration.setWidthFull();
		vehiclesConfiguration.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		// LAYOUT DEBUG
		//vehiclesConfiguration.getStyle().set("background-color", "yellow").set("border-style", "solid");
		
	}
	
	private void initDialogs() {
		dialogAddNewVehicle = new DialogAddNewVehicle();
		dialogDeleteVehicle = new DialogDeleteVehicle(vc);
		dialogEditVehicle = new DialogEditVehicle(vc);
	}
}
