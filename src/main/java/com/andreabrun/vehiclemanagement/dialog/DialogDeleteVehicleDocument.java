package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.VehicleManagementVehicleContainerPage;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.events.PageChangedEvent;
import com.andreabrun.vehiclemanagement.events.PageChangedEventPublisher;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.ViewUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;

public class DialogDeleteVehicleDocument extends Dialog {
	
	private static final long serialVersionUID = 1L;

	private VehicleContainer vc = null;
	private VehicleDocument vd = null;
	
	private VehicleManagementVehicleContainerPage parent;
	private PageChangedEventPublisher eventPublisher;
	
	public DialogDeleteVehicleDocument(VehicleContainer vc, VehicleDocument vd, VehicleManagementVehicleContainerPage parent, PageChangedEventPublisher eventPublisher) {
		
		this.parent = parent;
		this.eventPublisher = eventPublisher;
		
		this.vc = vc;
		this.vd = vd;
		
		if(vd != null) {
			setHeaderTitle(MessagesUtils.SURE_TO_DELETE + " il documento?");
			add(ViewUtils.createSimpleOutputComponents("Tipo", vd.getKey()));
			add(ViewUtils.createSimpleOutputComponents("Descrizione", vd.getDescription()));
		}
		else 
			setHeaderTitle(MessagesUtils.ERROR_DOCUMENT_NOT_SELECTED_TITLE);
		
		Button buttonConfirm = new Button(MessagesUtils.CONFIRM);
		Button buttonCancel = new Button(MessagesUtils.CANCEL, e -> this.close());
		
		buttonConfirm.addClickListener(this::delete);
		
		getFooter().add(buttonConfirm);
		getFooter().add(buttonCancel);
		
	}
	
	private void delete(ClickEvent<?> e) {
		if(vc != null && vd != null) {
			vc.deleteDocument(vd);
			Notification.show(MessagesUtils.VEHICLE_DOCUMENT_DELETED);
			eventPublisher.fireEvent(new PageChangedEvent(parent));
			this.close();
		}
	}

}
