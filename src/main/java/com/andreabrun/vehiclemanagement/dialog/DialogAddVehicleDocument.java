package com.andreabrun.vehiclemanagement.dialog;

import java.util.Set;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.MultiUploadFormView;
import com.andreabrun.vehiclemanagement.form.UploadFormView;
import com.andreabrun.vehiclemanagement.form.VehicleDocumentFormView;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class DialogAddVehicleDocument extends Dialog {
	
	private static final long serialVersionUID = 1L;
	public final String title = "Add Document";
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc = null;
	private VehicleDocument vd = null;
	
	private VehicleDocumentFormView form;
	private MultiUploadFormView uploadDocumentsForm;
	
	public DialogAddVehicleDocument(VehicleContainer vc) {
		
		this.vc = vc;
		init();
		
		if(vc != null) {
			setHeaderTitle(title);
			form = new VehicleDocumentFormView(vd, vc);
			add(form);
			
			NativeLabel addDocumentsTitle = new NativeLabel("Upload documents");
			uploadDocumentsForm = new MultiUploadFormView(10, UploadFormView.TYPE_DOCUMENT);
			add(addDocumentsTitle, uploadDocumentsForm);
			
			Button buttonSave = new Button("Save");
			Button buttonCancel = new Button("Cancel", e -> {
				this.vd = null;
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
			vd = form.getVehicleDocument();
			this.vd.persist();
			
			Set<String> filenames = uploadDocumentsForm.getFilenames();
			if(filenames != null && filenames.size() > 0) {
				PersistenceUtils.saveUploadedDocuments(uploadDocumentsForm.getBuffer(), vd);
			}
			
			Notification.show(MessagesUtils.VEHICLE_DOCUMENT_SAVED_SUCCESS);
			this.vd = null;
			this.close();
		} else {
			Notification.show(MessagesUtils.ERROR);
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
