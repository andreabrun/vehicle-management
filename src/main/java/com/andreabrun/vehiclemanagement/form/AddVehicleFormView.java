package com.andreabrun.vehiclemanagement.form;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class AddVehicleFormView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public final int ADDVEHICLEFORMVIEW_WIDTH = 300;
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	private Vehicle v = null;
	
	private VehicleFormView formAddVehicle;
	private Button buttonClear, buttonPersist;
	
	public AddVehicleFormView() {
		
		init();
		
		buttonClear = new Button("Clear");
		
		buttonPersist = new Button("Save");
		
		formAddVehicle = new VehicleFormView(v);

		buttonClear.addClickListener( e -> {
			this.v = new Vehicle();
			this.vc = null;
			
			formAddVehicle.update(v);
		});
		
		buttonPersist.addClickListener( e -> {
			this.vc = new VehicleContainer(v);
			this.vc.persist();
			this.vsbean.update();
			Notification.show("Veicolo salvato correttamente!");
		});
		
		setWidth(ADDVEHICLEFORMVIEW_WIDTH, Unit.PIXELS);
		buttonPersist.getStyle().set("width", "140px").set("align", "left");
		buttonClear.getStyle().set("width", "140px").set("align", "right");
		
		add(formAddVehicle);
		add(buttonPersist);
		add(buttonClear);
	}
	
	private void init() {
		
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		
		v = new Vehicle();
	}
}
