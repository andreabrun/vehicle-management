package com.andreabrun.vehiclemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicleDocumentType;
import com.andreabrun.vehiclemanagement.dialog.DialogDeleteVehicle;
import com.andreabrun.vehiclemanagement.dialog.DialogDeleteVehicleDocumentType;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocumentType;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.events.PageChangedEventPublisher;
import com.andreabrun.vehiclemanagement.form.ImagePickerFormView;
import com.andreabrun.vehiclemanagement.form.VehicleFormView;
import com.andreabrun.vehiclemanagement.listeners.PageChangedListener;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.andreabrun.vehiclemanagement.utils.ViewUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout implements VehicleManagementVehicleContainerPage {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	private final PageChangedEventPublisher eventPublisher = new PageChangedEventPublisher();
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
	
	Button buttonDeleteVDTS;
	List<Button> buttonsDeleteVDT;
	boolean enabledDeleteVDT = false;
	Map<Button, DialogDeleteVehicleDocumentType> dialogsDeleteVDTMap;
	
	public ConfigurationView() {
		init();
		initComponents();
	}
	
	public void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		this.vc = vsbean.getSelected();
		
		SelectedVehicleContainerListener listener = new SelectedVehicleContainerListener(this);
		vsbean.addPropertyChangeListener(listener);
		
		eventPublisher.addListener(new PageChangedListener());
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
		
		buttonsDeleteVDT = new ArrayList<Button>();
		dialogsDeleteVDTMap = new HashMap<Button, DialogDeleteVehicleDocumentType>();
		
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
		buttonOpenDeleteVehicle = new Button(MessagesUtils.VEHICLE_DELETE);
		buttonOpenDeleteVehicle.addClickListener( e -> {
			dialogDeleteVehicle.open();
		});
		buttonOpenDeleteVehicle.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenDeleteVehicle);
		
		
		// EDIT VEHICLE
		buttonEditVehicle = new Button(MessagesUtils.VEHICLE_EDIT);
		buttonEditVehicle.addClickListener( e -> {
			if(vehicleFormView != null) {
				if(vehicleFormView.isEnabled())
					vehicleFormView.executeEnable(false);
				else vehicleFormView.executeEnable(true);
			}
		});
		buttonEditVehicle.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonEditVehicle);
		
		// AGGIUNGI TIPO DI DOCUMENTO
		buttonOpenAddVehicleDocumentType = new Button(MessagesUtils.VEHICLE_ADD_DOCUMENT_TYPE);
		buttonOpenAddVehicleDocumentType.addClickListener( e -> {
			dialogAddVehicleDocumentType.open();
		});
		buttonOpenAddVehicleDocumentType.getStyle().set("margin", "5px");
		vehiclesConfiguration.add(buttonOpenAddVehicleDocumentType);
		
		// DELETE VEHICLE DOCUMENT TYPE
		enabledDeleteVDT = false;
		buttonDeleteVDTS = new Button(MessagesUtils.VEHICLE_DELETE_DOCUMENT_TYPE);
		buttonDeleteVDTS.addClickListener( e -> {
			this.executeEnableDeleteVDT();
		});
		vehiclesConfiguration.add(buttonDeleteVDTS);
		
		vehiclesConfiguration.setWidthFull();
		vehiclesConfiguration.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
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
			
			Map<String, VehicleDocumentType> documentTypes = vc.getDocumentTypes();
			FlexLayout vehicleDocumentTypesLayout = new FlexLayout();
			vehicleDocumentTypesLayout.setFlexWrap(FlexWrap.WRAP);
			for(String key : documentTypes.keySet()) {
				
				VerticalLayout layout = new VerticalLayout();
				
				VehicleDocumentType vdt = documentTypes.get(key);
				
				List<Component> outputComponents = ViewUtils.createOutputComponents(VehicleDocumentType.class, vdt);
				layout.add(outputComponents);
				
				Button buttonDeleteVDT = new Button(MessagesUtils.DELETE);
				StyleUtils.applyStyle(buttonDeleteVDT, StyleUtils.DELETE_ASSET_DOCUMENT_BUTTON_STYLE);
				buttonDeleteVDT.getStyle().set("display", "none");
				
				DialogDeleteVehicleDocumentType dialogDeleteVDT = new DialogDeleteVehicleDocumentType(vc, vdt, this, eventPublisher);

				// Add Button,Dialog to Map
				dialogsDeleteVDTMap.put(buttonDeleteVDT, dialogDeleteVDT);
				
				buttonDeleteVDT.addClickListener( e -> {
					DialogDeleteVehicleDocumentType dialog = dialogsDeleteVDTMap.get(e.getSource());
					dialog.open();
				});
				
				// Add button to list of Buttons
				buttonsDeleteVDT.add(buttonDeleteVDT);
				layout.add(buttonDeleteVDT);
				
				StyleUtils.applyStyle(layout, StyleUtils.VEHICLE_DOCUMENT_TYPE_VIEW_STYLE);
				layout.setWidth(300, Unit.PIXELS);
				vehicleDocumentTypesLayout.add(layout);
			}
			vehiclesConfigurationForms.add(vehicleDocumentTypesLayout);
		}
		
	}
	
	private void initDialogs() {
		dialogDeleteVehicle = new DialogDeleteVehicle(vc);
		dialogAddVehicleDocumentType = new DialogAddVehicleDocumentType(vc, this, eventPublisher);
	}
	
	public void executeEnableDeleteVDT() {
		for (Button button : buttonsDeleteVDT) {
			button.getStyle().set("display", enabledDeleteVDT ? "none" : "block");
		}
		enabledDeleteVDT = !enabledDeleteVDT;
	}
}
