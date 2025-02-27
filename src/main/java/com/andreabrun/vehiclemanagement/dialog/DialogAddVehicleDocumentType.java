package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocumentType;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.VehicleDocumentTypeFormView;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class DialogAddVehicleDocumentType extends Dialog {
	
	private static final long serialVersionUID = 1L;
	public final String title = "Add Document Type";
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	private VehicleDocumentType vdt = null;
	
	private VehicleDocumentTypeFormView form;
	
	public DialogAddVehicleDocumentType(VehicleContainer vc) {
		
		this.vc = vc;
		init();
		
		if(vc != null) {
			setHeaderTitle(title);
			form = new VehicleDocumentTypeFormView(vdt, vc);
			add(form);
			
			Button buttonSave = new Button("Save");
			Button buttonCancel = new Button("Cancel", e -> {
				this.vdt = null;
				this.close();
			});
			
			buttonSave.addClickListener(this::save);
			
			getFooter().add(buttonCancel);
			getFooter().add(buttonSave);
		} else {
			setHeaderTitle(MessagesUtils.ERROR_VEHICLE_NOT_SELECTED_TITLE);
			Button buttonCancel = new Button("Cancel", e -> this.close());
			getFooter().add(buttonCancel);
		}
		
	}
	
	private void save(ClickEvent<?> e) {
		
		if(form.validate()) {
			vdt = form.getVehicleDocumentType();
			this.vc.addDocumentType(vdt);
			this.vc.persist();
			
			Notification.show("Tipo documento salvato correttamente!");
			this.vdt = null;
			this.close();
		} else {
			Notification.show("Correggere gli errori nel Form!");
		}
		
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		if(vc == null) 
			vc = vsbean.getSelected();
		
		if(vc != null)
			vdt = new VehicleDocumentType();
	}

}
