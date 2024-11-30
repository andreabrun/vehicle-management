package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.view.VehicleSummaryView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/home", layout = MainView.class)
public class HomeView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public HomeView() {
		
		FlexLayout vehiclesLayout = new FlexLayout();
		
		VehicleContainer vc1 = new VehicleContainer(new Vehicle("Hondina", "Honda", "Jazz"));
		vc1.persist();
		VerticalLayout vehicleSingleLayout1 = new VehicleSummaryView(vc1, 400);
		vehiclesLayout.add(vehicleSingleLayout1);
		
		VehicleContainer vc2 = new VehicleContainer(new Vehicle("Terrano", "Nissan", "Terrano"));
		vc2.persist();
		VerticalLayout vehicleSingleLayout2 = new VehicleSummaryView(vc2, 400);
		vehiclesLayout.add(vehicleSingleLayout2);
		
		VehicleContainer vc3 = new VehicleContainer(new Vehicle("Honda CB500F", "Honda", "CB 500 F"));
		vc3.persist();
		VerticalLayout vehicleSingleLayout3 = new VehicleSummaryView(vc3, 400);
		vehiclesLayout.add(vehicleSingleLayout3);
		
		VehicleContainer vc4 = new VehicleContainer(new Vehicle("Eliminator", "Kawasaki", "EL252"));
		vc4.persist();
		VerticalLayout vehicleSingleLayout4 = new VehicleSummaryView(vc4, 400);
		vehiclesLayout.add(vehicleSingleLayout4);
		
		vehiclesLayout.setWidthFull();
		vehiclesLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		//vehiclesLayout.getStyle().set( "border" , "6px dotted DarkOrange" ) ; 
		
		add(vehiclesLayout);
		
	}
}
