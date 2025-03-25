package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocumentType;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.ViewUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;

public class DialogDeleteVehicleDocumentType extends Dialog {
	
	private static final long serialVersionUID = 1L;

	private VehicleContainer vc = null;
	private VehicleDocumentType vdt = null;
	
	public DialogDeleteVehicleDocumentType(VehicleContainer vc, VehicleDocumentType vdt) {
		
		this.vc = vc;
		this.vdt = vdt;
		
		if(vdt != null) {
			setHeaderTitle(MessagesUtils.SURE_TO_DELETE + " il tipo di documento?");
			add(ViewUtils.createSimpleOutputComponents("Tipo", vdt.getKey()));
		}
		else 
			setHeaderTitle(MessagesUtils.ERROR_DOCUMENT_TYPE_NOT_SELECTED_TITLE);
		
		Button buttonConfirm = new Button(MessagesUtils.CONFIRM);
		Button buttonCancel = new Button(MessagesUtils.CANCEL, e -> this.close());
		
		buttonConfirm.addClickListener(this::delete);
		
		getFooter().add(buttonConfirm);
		getFooter().add(buttonCancel);
		
	}
	
	private void delete(ClickEvent<?> e) {
		if(vc != null && vdt != null) {
			if(vc.getDocumentsByType(vdt.getKey()).isEmpty()) {
				vc.removeDocumentType(vdt);
				vc.persist();
				Notification.show(MessagesUtils.VEHICLE_DOCUMENT_DELETED);
				this.close();
			} else {
				Notification.show(MessagesUtils.CANNOT_DELETE_BESAUSE_USED);
			}
		}
	}

}
