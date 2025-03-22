package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.UploadFormView;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class DialogAddVehicleAsset extends Dialog {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	
	private UploadFormView form;
	
	public DialogAddVehicleAsset(VehicleContainer vc) {
		
		this.vc = vc;
		init();
		
		if(vc != null) {
			setHeaderTitle(MessagesUtils.VEHICLE_ADD_ASSETS);
			form = new UploadFormView(UploadFormView.TYPE_IMAGE);
			add(form);
			
			Button buttonUpload = new Button(MessagesUtils.UPLOAD);
			buttonUpload.addClickListener(this::uploadImage);
			
			Button buttonCancel = new Button(MessagesUtils.CANCEL, e -> {
				this.close();
			});
			
			getFooter().add(buttonCancel);
			getFooter().add(buttonUpload);
		} else {
			setHeaderTitle(MessagesUtils.ERROR_VEHICLE_NOT_SELECTED_TITLE);
			Button buttonCancel = new Button(MessagesUtils.CANCEL, e -> this.close());
			getFooter().add(buttonCancel);
		}
		
	}
	
	private void uploadImage(ClickEvent<?> e) {
		
		if(form.getFilename() != null && vc != null) {
			String uploadedImage = PersistenceUtils.saveUploadedImage(form.getBuffer(), vc);
			Notification.show(MessagesUtils.FILE_UPLOADED + uploadedImage);
			this.close();
		}
		
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		if(vc == null) 
			vc = vsbean.getSelected();
	}

}
