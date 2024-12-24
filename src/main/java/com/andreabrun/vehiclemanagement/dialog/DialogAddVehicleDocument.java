package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.VehicleDocumentFormView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class DialogAddVehicleDocument extends Dialog {
	
	private static final long serialVersionUID = 1L;
	public final String title = "Add Document";
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	private VehicleDocument vd = null;
	
	private VehicleDocumentFormView form;
	
	public DialogAddVehicleDocument(VehicleContainer vc) {
		
		this.vc = vc;
		init();
		
		if(vc != null) {
			setHeaderTitle(title);
			form = new VehicleDocumentFormView(vd, vc);
			add(form);
			
			Button buttonSave = new Button("Save");
			Button buttonCancel = new Button("Cancel", e -> {
				this.vd = null;
				this.close();
			});
			
			buttonSave.addClickListener(this::save);
			
			getFooter().add(buttonCancel);
			getFooter().add(buttonSave);
		} else {
			setHeaderTitle("Errore! Selezionare un veicolo!");
			Button buttonCancel = new Button("Cancel", e -> this.close());
			getFooter().add(buttonCancel);
		}
		
	}
	
	private void save(ClickEvent<?> e) {
		
		if(form.validate()) {
			vd = form.getVehicleDocument();
			this.vd.persist();
			
			Notification.show("Documento salvato correttamente!");
			this.vd = null;
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
			vd = new VehicleDocument(vc);
	}

}
