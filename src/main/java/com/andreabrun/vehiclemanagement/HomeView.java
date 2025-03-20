package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.view.VehicleSummaryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/home", layout = MainView.class)
public class HomeView extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;

	DialogAddVehicle dialogAddVehicle;
	Button buttonOpenAddVehicle;
	
	public HomeView() {
		
		init();
		
		FlexLayout vehiclesLayout = new FlexLayout();

		for(VehicleContainer vc : vsbean.getData()) {
			VerticalLayout vehicleSingleLayout = new VehicleSummaryView(vc);
			vehiclesLayout.add(vehicleSingleLayout);
		}
		
		vehiclesLayout.setWidthFull();
		vehiclesLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		add(vehiclesLayout);
		
		
		dialogAddVehicle = new DialogAddVehicle();
		buttonOpenAddVehicle = new Button("Aggiungi veicolo");
		buttonOpenAddVehicle.addClickListener( e -> {
			dialogAddVehicle.open();
		});
		buttonOpenAddVehicle.getStyle().set("margin", "5px");
		add(dialogAddVehicle);
		
		add(buttonOpenAddVehicle);
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
	}
	
}
