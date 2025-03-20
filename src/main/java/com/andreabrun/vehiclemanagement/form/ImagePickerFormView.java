package com.andreabrun.vehiclemanagement.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImagePickerFormView extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
   
    private VehicleContainer vc;
    
    private GalleryFormView vehicleGallery;
    
    public ImagePickerFormView() {
		this(null);
    }

	public ImagePickerFormView(VehicleContainer vc) {
		
		this.vc = vc;
		
		add(new H4("Select cover image"));
		
		this.vehicleGallery = new GalleryFormView();
		
		init();	
		StyleUtils.applyStyle(vehicleGallery, StyleUtils.IMAGE_PICKER_FORM_VIEW_GALLERY_STYLE);
		add(vehicleGallery);
		
		StyleUtils.applyStyle(this, StyleUtils.IMAGE_PICKER_FORM_VIEW_STYLE);
        
	}
	
	public void init() {
		
		if(this.vc != null) {
			
			List<String> assets = vc.getAllAssets();
			
			List<Image> images = new ArrayList<Image>();
			Map<Image, ComponentEventListener<ClickEvent<Image>>> eventListeners = new HashMap<Image, ComponentEventListener<ClickEvent<Image>>>();
			
			for(String ass : assets) {
				
				Image img = ComponentsUtils.getImageFromPath(ass);
				
				ComponentEventListener<ClickEvent<Image>> eventListener = event -> {
					String filename = PersistenceUtils.getFilename(ass);
		            vc.setCoverImageName(filename);
		            vc.persist();
		            Notification.show(MessagesUtils.INFO_VEHICLE_COVER_SAVED_SUCCESS);
		        };
				
				images.add(img);
				eventListeners.put(img, eventListener);
			}
			
			vehicleGallery.setImages(images);
			vehicleGallery.setEventHandlers(eventListeners);
		}
		
	}
	
	public boolean validate() {
		return true;
	}

}
