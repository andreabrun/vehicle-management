package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.server.VaadinSession;

public class DialogDeleteVehicle extends Dialog {
	
	private static final long serialVersionUID = 1L;
	public final String title = "Delete";
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	
	public DialogDeleteVehicle(VehicleContainer vc) {
		
		this.vc = vc;
		init();
		
		if(vc != null)
			setHeaderTitle(title + " " + vc.getVehicle().getName());
		else 
			setHeaderTitle("Errore! Selezionare un veicolo");
		
		Button buttonConfirm = new Button("Delete");
		Button buttonCancel = new Button("Cancel", e -> this.close());
		
		buttonConfirm.addClickListener(this::delete);
		
		getFooter().add(buttonConfirm);
		getFooter().add(buttonCancel);
	}
	
	private void delete(ClickEvent<?> e) {
		if(vc != null) {
			vc.delete();
			vsbean.update();
		}
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		if(vc == null) 
			vc = vsbean.getSelected();
	}
	
	public void setVehicleContainer(VehicleContainer vc) {
		this.vc = vc;
	}
	
}