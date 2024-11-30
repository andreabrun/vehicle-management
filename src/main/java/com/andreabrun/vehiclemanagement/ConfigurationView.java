package com.andreabrun.vehiclemanagement;

import javax.xml.bind.JAXBException;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.form.VehicleFormView;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	
	Vehicle newVehicle;

	public ConfigurationView() {
		
		VerticalLayout vehiclesConfigurationLayout = new VerticalLayout();
		
		VehicleSessionBean attribute = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		
		Button button = new Button("Create entity");
		button.addClickListener( e -> {
			Notification.show(e.toString());
			VehicleFormView vfv = new VehicleFormView(newVehicle);
			add(vfv);
		});
		
		Button buttonPersist = new Button("Persist");
		buttonPersist.addClickListener( e -> {
			try {
				Notification.show("Vehicle persistence: " + newVehicle.getName());
				VehicleXMLService.marshalToXML(newVehicle, Vehicle.class, PersistenceUtils.VEHICLE_PATH + "new-vehicle.xml");
				Notification.show("Vehicle persistence: " + newVehicle.getName());
			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
		});
		
		add(button);
		
		add(buttonPersist);
	}
}
