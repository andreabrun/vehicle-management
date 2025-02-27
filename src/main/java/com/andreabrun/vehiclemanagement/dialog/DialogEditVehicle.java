package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.VehicleFormView;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class DialogEditVehicle extends Dialog {
	
	private static final long serialVersionUID = 1L;
	public final String title = "Edit";
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	
	private VehicleFormView form;
	
	public DialogEditVehicle(VehicleContainer vc) {
		
		this.vc = vc;
		init();
		
		if(vc != null) {
			
			Vehicle v = vc.getVehicle();
			
			setHeaderTitle(title + " " + v.getName());
			
			form = new VehicleFormView(v, vc);
			add(form);
			
			Button buttonSave = new Button("Save");
			
			buttonSave.addClickListener(this::save);
			
			getFooter().add(buttonSave);
			
		} else {
			setHeaderTitle(MessagesUtils.ERROR_VEHICLE_NOT_SELECTED_TITLE);
		}
		
		Button buttonCancel = new Button("Cancel", e -> this.close());
		getFooter().add(buttonCancel);
		
	}
	
	private void save(ClickEvent<?> e) {
		
		if(form.validate()) {
			this.vc.persist();
			Notification.show("Veicolo salvato correttamente!");
			this.close();
		} else {
			Notification.show("Correggere gli errori nel Form!");
		}
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		if(vc == null)
			vc = vsbean.getSelected();
	}
}
