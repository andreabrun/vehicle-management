package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.UploadFormView;
import com.andreabrun.vehiclemanagement.form.VehicleFormView;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class DialogAddVehicle extends Dialog {
	
	private static final long serialVersionUID = 1L;
	public final String title = "New Vehicle";
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	private Vehicle v = null;
	
	private VehicleFormView form;
	private UploadFormView coverImageForm;
	
	public DialogAddVehicle() {
		
		init();
		
		setHeaderTitle(title);
		form = new VehicleFormView(v, vc);
		add(form);
		
		NativeLabel coverImageTitle = new NativeLabel("Upload cover image");
		coverImageForm = new UploadFormView(UploadFormView.TYPE_IMAGE);
		add(coverImageTitle, coverImageForm);
		
		Button buttonSave = new Button("Save");
		Button buttonCancel = new Button("Cancel", e -> this.close());
		
		buttonSave.addClickListener(this::save);
		
		getFooter().add(buttonCancel);
		getFooter().add(buttonSave);
	}
	
	private void save(ClickEvent<?> e) {
		
		if(form.validate()) {
			v = form.getVehicle();
			this.vc = new VehicleContainer(v);
			
			vc.createAssetsFolder();
			if(coverImageForm.getFilename() != null) {
				String uploadedImage = PersistenceUtils.saveUploadedImage(coverImageForm.getBuffer(), vc);
				if(uploadedImage != null) {
					vc.setCoverImageName(uploadedImage);
				}
			}
			
			this.vc.persist();
			this.vsbean.update();
			
			Notification.show("Veicolo salvato correttamente!");
			this.close();
		} else {
			Notification.show("Correggere gli errori nel Form!");
		}
		
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		
		v = new Vehicle();
	}
	
}
