package com.andreabrun.vehiclemanagement;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.form.UploadFormView;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/gallery", layout = MainView.class)
public class GalleryView extends VerticalLayout implements VehicleManagementPage {
	
	private static final long serialVersionUID = 1L;

	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc;
	
	H1 h1Title;
	FlexLayout vehiclesGallery;
	UploadFormView uploadImageForm;
	
	String title = "Gallery";
	
	public GalleryView() {
		
		init();
		
		initComponents();

	}
	
	public void updateComponents() {
		clearComponents();
		initComponents();
	}
	
	private void clearComponents() {
		this.removeAll();
	}
	
	private void initComponents() {
		
		String titleContent = (vc != null ? title + " " + vc.getVehicle().getName() : title);
		H1 h1Title = new H1(titleContent);
		add(h1Title);
		
		if(vc != null) {
			H4 uploadImageH4 = new H4("Upload image");
			uploadImageForm = new UploadFormView(1, UploadFormView.TYPE_IMAGE);
			Button buttonUpload = new Button("Upload");
			buttonUpload.addClickListener(this::uploadImage);
			add(uploadImageH4, uploadImageForm, buttonUpload);
		}
		
		initVehiclesConfiguration();
		add(vehiclesGallery);
	}
	
	private void initVehiclesConfiguration() {
		
		vehiclesGallery = new FlexLayout();

		if(vc != null) {
			String assetsPath = vc.getAssetsPath();
			List<String> filesInFolder = PersistenceUtils.getFilesInFolder(assetsPath, true);
			
			for(String imgPath : filesInFolder) {
				Image img = ComponentsUtils.getImageFromPath(imgPath);
				img.setHeight(300, Unit.PIXELS);
				vehiclesGallery.add(img);
			}
		}
		
		vehiclesGallery.setWidthFull();
		vehiclesGallery.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		// LAYOUT DEBUG
		//vehiclesConfiguration.getStyle().set("background-color", "yellow").set("border-style", "solid");
		
	}
	
	public void setVehicleContainer(VehicleContainer vc) {
		this.vc = vc;
	}
	
	public void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		this.vc = vsbean.getSelected();
		
		SelectedVehicleContainerListener listener = new SelectedVehicleContainerListener(this);
		vsbean.addPropertyChangeListener(listener);
	}
	
	private void uploadImage(ClickEvent<?> e) {
		
		if(uploadImageForm.getFilename() != null && vc != null) {
			String uploadedImage = PersistenceUtils.saveUploadedImage(uploadImageForm.getBuffer(), vc);
			Notification.show("Immagine caricata correttamente! " + uploadedImage);
		}
		
	}
}
