package com.andreabrun.vehiclemanagement;

import javax.xml.bind.JAXBException;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public ConfigurationView() {
		add( new H1("Configuration title"));

		Button button = new Button("Create entity");
		button.addAttachListener( e -> {
			Vehicle v = new Vehicle("Nome", "Marca", "Modello");
			
			// TODO: Sistemare salvataggio su file xml
//			try {
//				if(v != null)
//					VehicleXMLService.marshalToXML(v, "filename.xml");
//			} catch (JAXBException e1) {
//				e1.printStackTrace();
//			}
			
		});
		
		add(button);
	}
}
