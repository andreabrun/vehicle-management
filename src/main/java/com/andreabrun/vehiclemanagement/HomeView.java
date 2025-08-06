package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.events.PageChangedEventPublisher;
import com.andreabrun.vehiclemanagement.listeners.PageChangedListener;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.view.VehicleSummaryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/home", layout = MainView.class)
public class HomeView extends VerticalLayout implements VehicleManagementVehicleContainerPage {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	private final PageChangedEventPublisher eventPublisher = new PageChangedEventPublisher();

	DialogAddVehicle dialogAddVehicle;
	Button buttonOpenAddVehicle;
	
	public HomeView() {
		
		init();
		
		initComponents();
	}
	
	public void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		eventPublisher.addListener(new PageChangedListener());
	}
	
	private void clearComponents() {
		this.removeAll();
	}
	
	public void updateComponents() {
		clearComponents();
		initComponents();
	}
	
	private void initComponents() {
		
		initDialogs();
		add(dialogAddVehicle);
		
		FlexLayout configurationLayout = new FlexLayout();

		buttonOpenAddVehicle = new Button(MessagesUtils.VEHICLE_ADD_NEW);
		buttonOpenAddVehicle.addClickListener( e -> {
			dialogAddVehicle.open();
		});
		buttonOpenAddVehicle.getStyle().set("margin", "5px");
		
		configurationLayout.add(buttonOpenAddVehicle);
		
		configurationLayout.setWidthFull();
		configurationLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

		add(configurationLayout);
		
		FlexLayout vehiclesLayout = new FlexLayout();

		for(VehicleContainer vc : vsbean.getData()) {
			VerticalLayout vehicleSingleLayout = new VehicleSummaryView(vc);
			vehiclesLayout.add(vehicleSingleLayout);
		}
		
		vehiclesLayout.setWidthFull();
		vehiclesLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		add(vehiclesLayout);
	}
	
	private void initDialogs() {
		dialogAddVehicle = new DialogAddVehicle(this, eventPublisher);
	}

	@Override
	public void setVehicleContainer(VehicleContainer vc) {
		// Not used in HomeView
	}
	
}
