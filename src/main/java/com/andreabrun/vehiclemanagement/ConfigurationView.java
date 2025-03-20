package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicleDocumentType;
import com.andreabrun.vehiclemanagement.dialog.DialogDeleteVehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.ImagePickerFormView;
import com.andreabrun.vehiclemanagement.form.VehicleFormView;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout implements VehicleManagementVehicleContainerPage {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	private VehicleContainer vc;
	
	FlexLayout vehiclesConfiguration;
	Button buttonOpenDeleteVehicle;
	Button buttonEditVehicle;
	Button buttonOpenAddVehicleDocumentType;
	
	DialogDeleteVehicle dialogDeleteVehicle;
	DialogAddVehicleDocumentType dialogAddVehicleDocumentType;
	
	FlexLayout vehiclesConfigurationForms;
	VehicleFormView vehicleFormView;
	ImagePickerFormView imagePickerFormView;
	
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
		add(dialogDeleteVehicle);
		
		initVehiclesConfiguration();
		add(vehiclesConfiguration);
		
		initVehiclesConfigurationForms();
		add(vehiclesConfigurationForms);
		
	}
	
	
	private void initVehiclesConfiguration() {
		
		vehiclesConfiguration = new FlexLayout();

		
		// DELETE VEHICLE
		buttonOpenDeleteVehicle = new Button("Elimina veicolo");
		buttonOpenDeleteVehicle.addClickListener( e -> {
			dialogDeleteVehicle.open();
		});
		buttonOpenDeleteVehicle.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenDeleteVehicle);
		
		
		// EDIT VEHICLE
		buttonOpenAddVehicleDocumentType = new Button("Aggiungi nuovo tipo di documento");
		buttonOpenAddVehicleDocumentType.addClickListener( e -> {
			dialogAddVehicleDocumentType.open();
		});
		buttonOpenAddVehicleDocumentType.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenAddVehicleDocumentType);
		
		vehiclesConfiguration.setWidthFull();
		vehiclesConfiguration.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		buttonEditVehicle = new Button("Modifica");
		buttonEditVehicle.addClickListener( e -> {
			if(vehicleFormView != null) {
				if(vehicleFormView.isEnabled())
					vehicleFormView.executeEnable(false);
				else vehicleFormView.executeEnable(true);
			}
		});
		vehiclesConfiguration.add(buttonEditVehicle);
		
		// LAYOUT DEBUG
		//vehiclesConfiguration.getStyle().set("background-color", "yellow").set("border-style", "solid");
		
	}
	
	private void initVehiclesConfigurationForms() {
		
		vehiclesConfigurationForms = new FlexLayout();
		vehiclesConfigurationForms.setWidthFull();
		vehiclesConfigurationForms.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		if(this.vc != null) {
			vehicleFormView = new VehicleFormView(vc.getVehicle(), vc);
			
			StyleUtils.applyStyle(vehicleFormView, StyleUtils.VEHICLE_FORM_VIEW_STYLE);
			vehicleFormView.applyStyleToInputComponents(StyleUtils.VEHICLE_FORM_VIEW_INPUT_STYLE);
			
			vehiclesConfigurationForms.add(vehicleFormView);
			vehicleFormView.executeEnable(false);
			
			imagePickerFormView = new ImagePickerFormView(vc);
			vehiclesConfigurationForms.add(imagePickerFormView);
		}
		
	}	
	
	private void initDialogs() {
		dialogDeleteVehicle = new DialogDeleteVehicle(vc);
		dialogAddVehicleDocumentType = new DialogAddVehicleDocumentType(vc);
	}
}
