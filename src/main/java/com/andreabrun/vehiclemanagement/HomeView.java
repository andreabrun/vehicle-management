package com.andreabrun.vehiclemanagement;

import org.atmosphere.util.VoidExecutorService;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
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
		
		Image image1 = new Image("./images/altcaricon.webp", "My Alt Image");
		Image image2 = new Image("./images/altcaricon.webp", "My Alt Image");
		Image image3 = new Image("./images/altcaricon.webp", "My Alt Image");
		Image image4 = new Image("./images/altcaricon.webp", "My Alt Image");
		
		VerticalLayout vehicleSingleLayout1 = new VerticalLayout();
		vehicleSingleLayout1.add(new H2("Honda Jazz"));
		vehicleSingleLayout1.add(image1);
		vehicleSingleLayout1.add(new H4("185000 km"));
		vehicleSingleLayout1.add(new H4("Ultimo tagliando: 04/2024 - 175000 km"));
		vehicleSingleLayout1.add(new H4("Scadenza revisione: 05/2025"));
		vehicleSingleLayout1.add(new H4("Scadenza assicurazione: 15/07/2025"));
		vehicleSingleLayout1.setWidth(400, Unit.PIXELS);
		vehiclesLayout.add(vehicleSingleLayout1);
		
		VerticalLayout vehicleSingleLayout2 = new VerticalLayout();
		vehicleSingleLayout2.add(new H2("Nissan Terrano"));
		vehicleSingleLayout2.add(image2);
		vehicleSingleLayout2.add(new H4("325000 km"));
		vehicleSingleLayout2.add(new H4("Ultimo tagliando: 01/2024 - 300000 km"));
		vehicleSingleLayout2.add(new H4("Scadenza revisione: 11/2024"));
		vehicleSingleLayout2.add(new H4("Scadenza assicurazione: 16/02/2025"));
		vehicleSingleLayout2.setWidth(400, Unit.PIXELS);
		vehiclesLayout.add(vehicleSingleLayout2);
		
		VerticalLayout vehicleSingleLayout3 = new VerticalLayout();
		vehicleSingleLayout3.add(new H2("Honda CB500F"));
		vehicleSingleLayout3.add(image3);
		vehicleSingleLayout3.add(new H4("13000 km"));
		vehicleSingleLayout3.add(new H4("Ultimo tagliando: 09/2024 - 12500 km"));
		vehicleSingleLayout3.add(new H4("Scadenza revisione: 03/2025"));
		vehicleSingleLayout3.add(new H4("Scadenza assicurazione: 15/10/2025"));
		vehicleSingleLayout3.setWidth(400, Unit.PIXELS);
		vehiclesLayout.add(vehicleSingleLayout3);
		
		VerticalLayout vehicleSingleLayout4 = new VerticalLayout();
		vehicleSingleLayout4.add(new H2("Kawasaki EL252"));
		vehicleSingleLayout4.add(image4);
		vehicleSingleLayout4.add(new H4("12000 km"));
		vehicleSingleLayout4.add(new H4("Ultimo tagliando: 06/2023 - 11000 km"));
		vehicleSingleLayout4.add(new H4("Scadenza revisione: 03/2025"));
		vehicleSingleLayout4.add(new H4("Scadenza assicurazione: 07/10/2024"));
		vehicleSingleLayout4.setWidth(400, Unit.PIXELS);
		vehiclesLayout.add(vehicleSingleLayout4);
		
		vehiclesLayout.setWidthFull();
		vehiclesLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		//vehiclesLayout.getStyle().set( "border" , "6px dotted DarkOrange" ) ; 
		
		add(vehiclesLayout);
		
	}
}
