package com.andreabrun.vehiclemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicleAsset;
import com.andreabrun.vehiclemanagement.dialog.DialogDeleteVehicleAsset;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.events.PageChangedEventPublisher;
import com.andreabrun.vehiclemanagement.listeners.PageChangedListener;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/gallery", layout = MainView.class)
public class GalleryView extends VerticalLayout implements VehicleManagementVehicleContainerPage {
	
	private static final long serialVersionUID = 1L;

	private VehicleSessionBean vsbean;
	private final PageChangedEventPublisher eventPublisher = new PageChangedEventPublisher();
	
	private VehicleContainer vc;

	FlexLayout vehicleGallery;
	
	Button buttonAddVehicleAsset;
	DialogAddVehicleAsset dialogAddVehicleAsset;
	
	Button buttonDeleteVehicleAssets;
	List<Button> buttonsDeleteVehicleAsset;
	boolean enabledDelete = false;
	Map<Button, DialogDeleteVehicleAsset> dialogsDeleteVehicleAssetMap;
	
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

		buttonsDeleteVehicleAsset = new ArrayList<Button>();
		dialogsDeleteVehicleAssetMap = new HashMap<Button, DialogDeleteVehicleAsset>();
		
		initDialogs();
		
		FlexLayout configurationLayout = new FlexLayout();
		
		// ADD NEW VEHICLE ASSET
		buttonAddVehicleAsset = new Button(MessagesUtils.VEHICLE_ADD_ASSETS);
		buttonAddVehicleAsset.addClickListener( e -> {
			dialogAddVehicleAsset.open();
		});
		buttonAddVehicleAsset.getStyle().set("margin", "5px");
		configurationLayout.add(buttonAddVehicleAsset);

		// DELETE VEHICLE ASSET
		enabledDelete = false;
		buttonDeleteVehicleAssets = new Button(MessagesUtils.VEHICLE_DELETE_ASSETS);
		buttonDeleteVehicleAssets.addClickListener( e -> {
			this.executeEnableDelete();
		});
		buttonDeleteVehicleAssets.getStyle().set("margin", "5px");
		configurationLayout.add(buttonDeleteVehicleAssets);
		
		configurationLayout.setWidthFull();
		configurationLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

		add(configurationLayout);
		
		initVehicleGallery();
		add(vehicleGallery);
		
	}

	private void initVehicleGallery() {
		
		vehicleGallery = new FlexLayout();

		if(vc != null) {
			String assetsPath = vc.getAssetsPath();
			List<String> filesInFolder = PersistenceUtils.getFilesInFolder(assetsPath, true);

			for(String imgPath : filesInFolder) {
				
				Image img = ComponentsUtils.getImageFromPath(imgPath);
				img.getStyle().set("height", "100%");
				Div imageDiv = new Div();
				
				Anchor imageLink = ComponentsUtils.getImageAnchorFromPath(imgPath);
				imageLink.add(img);
				imageDiv.add(imageLink);
				
				Button buttonDeleteVehicleAsset = new Button(MessagesUtils.DELETE);
				StyleUtils.applyStyle(buttonDeleteVehicleAsset, StyleUtils.DELETE_ASSET_DOCUMENT_BUTTON_STYLE);
				buttonDeleteVehicleAsset.getStyle().set("display", "none");
				
				DialogDeleteVehicleAsset dialogDeleteVehicleAsset = new DialogDeleteVehicleAsset(vc, imgPath, this, eventPublisher);
				dialogsDeleteVehicleAssetMap.put(buttonDeleteVehicleAsset, dialogDeleteVehicleAsset);
				
				buttonDeleteVehicleAsset.addClickListener( e -> {
					DialogDeleteVehicleAsset dialog = dialogsDeleteVehicleAssetMap.get(e.getSource());
					dialog.open();
				});
				buttonsDeleteVehicleAsset.add(buttonDeleteVehicleAsset);
				imageDiv.add(buttonDeleteVehicleAsset);
				
				StyleUtils.applyStyle(imageDiv, StyleUtils.VEHICLE_GALLERY_IMAGE_DIV_STYLE);
				vehicleGallery.add(imageDiv);
			}
		}
		
		vehicleGallery.setWidthFull();
		vehicleGallery.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
	}
	
	private void initDialogs() {
		dialogAddVehicleAsset = new DialogAddVehicleAsset(vc, this, eventPublisher);
	}
	
	public void setVehicleContainer(VehicleContainer vc) {
		this.vc = vc;
	}
	
	public void executeEnableDelete() {
		for (Button button : buttonsDeleteVehicleAsset) {
			button.getStyle().set("display", enabledDelete ? "none" : "block");
		}
		enabledDelete = !enabledDelete;
	}
	
	public void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
		this.vc = vsbean.getSelected();
		
		SelectedVehicleContainerListener listener = new SelectedVehicleContainerListener(this);
		vsbean.addPropertyChangeListener(listener);
		
		eventPublisher.addListener(new PageChangedListener());
	}
}
