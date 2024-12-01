package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.view.VehicleSummaryView;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/home", layout = MainView.class)
public class HomeView extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;

	public HomeView() {
		
		init();
		
		FlexLayout vehiclesLayout = new FlexLayout();

		for(VehicleContainer vc : vsbean.getData()) {
			VerticalLayout vehicleSingleLayout = new VehicleSummaryView(vc, 400);
			vehiclesLayout.add(vehicleSingleLayout);
		}
		
		vehiclesLayout.setWidthFull();
		vehiclesLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		add(vehiclesLayout);
		
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
	}
}
