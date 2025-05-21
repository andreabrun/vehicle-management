package com.andreabrun.vehiclemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicleDocument;
import com.andreabrun.vehiclemanagement.dialog.DialogDeleteVehicleDocument;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.events.PageChangedEventPublisher;
import com.andreabrun.vehiclemanagement.listeners.PageChangedListener;
import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.andreabrun.vehiclemanagement.view.VehicleDocumentSummaryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/documents", layout = MainView.class)
public class DocumentsView extends VerticalLayout implements VehicleManagementVehicleContainerPage {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	private final PageChangedEventPublisher eventPublisher = new PageChangedEventPublisher();
	
	private VehicleContainer vc;
	
	Button buttonAddVehicleDocument;
	DialogAddVehicleDocument dialogAddNewVehicleDocument;
	
	Button buttonDeleteVehicleDocument;
	List<Button> buttonsDeleteVehicleDocument;
	boolean enabledDelete = false;
	Map<Button, DialogDeleteVehicleDocument> dialogsDeleteVehicleDocumentMap;
	
	FlexLayout vehicleDocuments;
	
	public DocumentsView() {
		
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
		
		buttonsDeleteVehicleDocument = new ArrayList<Button>();
		dialogsDeleteVehicleDocumentMap = new HashMap<Button, DialogDeleteVehicleDocument>();
		
		initDialogs();
		
		add(dialogAddNewVehicleDocument);
		
		FlexLayout configurationLayout = new FlexLayout();

		// ADD NEW VEHICLE DOCUMENT
		buttonAddVehicleDocument = new Button(MessagesUtils.VEHICLE_ADD_DOCUMENTS);
		buttonAddVehicleDocument.addClickListener( e -> {
			dialogAddNewVehicleDocument.open();
		});
		buttonAddVehicleDocument.getStyle().set("margin", "5px");
		configurationLayout.add(buttonAddVehicleDocument);
		
		// ADD DELETE VEHICLE DOCUMENT
		enabledDelete = false;
		
		buttonDeleteVehicleDocument = new Button(MessagesUtils.VEHICLE_DELETE_DOCUMENTS);
		buttonDeleteVehicleDocument.addClickListener( e -> {
			this.executeEnableDelete();
		});
		buttonDeleteVehicleDocument.getStyle().set("margin", "5px");
		configurationLayout.add(buttonDeleteVehicleDocument);
		
		configurationLayout.setWidthFull();
		configurationLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

		add(configurationLayout);
		
		initVehicleDocuments();
		add(vehicleDocuments);
	}
	
	private void initVehicleDocuments() {
		
		vehicleDocuments = new FlexLayout();

		if(vc != null) {
			List<VehicleDocument> docs = vc.getAllDocumentsOrderedByDate();
			for (VehicleDocument vd : docs) {
				Div div = new Div();
				
				VehicleDocumentSummaryView vdsv = new VehicleDocumentSummaryView(vd);
				
				div.add(vdsv);
				div.getStyle().set("position", "relative");
				
				Button buttonDeleteVehicleDocument = new Button(MessagesUtils.DELETE);
				StyleUtils.applyStyle(buttonDeleteVehicleDocument, StyleUtils.DELETE_ASSET_DOCUMENT_BUTTON_STYLE);
				buttonDeleteVehicleDocument.getStyle().set("display", "none");
				
				DialogDeleteVehicleDocument dialogDeleteVehicleDocument = new DialogDeleteVehicleDocument(vc, vd, this, eventPublisher);
				dialogsDeleteVehicleDocumentMap.put(buttonDeleteVehicleDocument, dialogDeleteVehicleDocument);
				
				buttonDeleteVehicleDocument.addClickListener( e -> {
					DialogDeleteVehicleDocument dialog = dialogsDeleteVehicleDocumentMap.get(e.getSource());
					dialog.open();
				});
				buttonsDeleteVehicleDocument.add(buttonDeleteVehicleDocument);
				div.add(buttonDeleteVehicleDocument);
				
				vehicleDocuments.add(div);
			}
		}
		
		vehicleDocuments.setWidthFull();
		vehicleDocuments.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		// LAYOUT DEBUG
		//vehicleDocuments.getStyle().set("border-style", "solid");
		
	}
	
	private void initDialogs() {
		dialogAddNewVehicleDocument = new DialogAddVehicleDocument(vc, this, eventPublisher);
	}
	
	public void setVehicleContainer(VehicleContainer vc) {
		this.vc = vc;
	}
	
	public void executeEnableDelete() {
		for (Button button : buttonsDeleteVehicleDocument) {
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
