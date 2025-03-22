package com.andreabrun.vehiclemanagement.dialog;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;

public class DialogDeleteVehicleAsset extends Dialog {
	
	private static final long serialVersionUID = 1L;

	private VehicleContainer vc = null;
	private String assetPath;
	
	public DialogDeleteVehicleAsset(VehicleContainer vc, String assetPath) {
		
		this.vc = vc;
		this.assetPath = assetPath;
		
		if(vc != null) {
			setHeaderTitle(MessagesUtils.SURE_TO_DELETE + " l'immagine?");
			Image img = ComponentsUtils.getImageFromPath(assetPath);
			img.getStyle().set("height", "100%");
			Div imageDiv = new Div();
			imageDiv.add(img);
			StyleUtils.applyStyle(imageDiv, StyleUtils.VEHICLE_GALLERY_IMAGE_DIV_STYLE);
			add(imageDiv);
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
		if(vc != null) {
			try {
				vc.deleteAsset(assetPath);
				this.close();
			} catch (Exception e1) {
				Notification.show(e1.getMessage());
			}
		}
	}

}
