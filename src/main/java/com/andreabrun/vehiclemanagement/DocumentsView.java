package com.andreabrun.vehiclemanagement;

import java.util.List;

import com.andreabrun.vehiclemanagement.dialog.DialogAddVehicleDocument;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.utils.SelectedVehicleContainerListener;
import com.andreabrun.vehiclemanagement.view.VehicleDocumentSummaryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/documents", layout = MainView.class)
public class DocumentsView extends VerticalLayout implements VehicleManagementVehicleContainerPage {
	
	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	
	private VehicleContainer vc;
	
	H1 h1Title;
	
	String title = "Documents";
	
	Button buttonAddVehicleDocument;
	DialogAddVehicleDocument dialogAddNewVehicleDocument;
	
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
		
		String titleContent = (vc != null ? title + " " + vc.getVehicle().getName() : title);
		H1 h1Title = new H1(titleContent);
		add(h1Title);
		
		initDialogs();
		add(dialogAddNewVehicleDocument);

		// ADD NEW VEHICLE DOCUMENT
		buttonAddVehicleDocument = new Button("Aggiungi documento");
		buttonAddVehicleDocument.addClickListener( e -> {
			dialogAddNewVehicleDocument.open();
		});
		buttonAddVehicleDocument.getStyle().set("margin", "5px");
		add(buttonAddVehicleDocument);
		
		initVehicleDocuments();
		add(vehicleDocuments);
	}
	
	private void initVehicleDocuments() {
		
		vehicleDocuments = new FlexLayout();

		if(vc != null) {
			List<VehicleDocument> docs = vc.getAllDocumentsOrderedByDate();
			for (VehicleDocument vd : docs) {
				VehicleDocumentSummaryView vdsv = new VehicleDocumentSummaryView(vd, 300);
				vehicleDocuments.add(vdsv);
			}
		}
		
		vehicleDocuments.setWidthFull();
		vehicleDocuments.setFlexWrap(FlexLayout.FlexWrap.WRAP);
		
		// LAYOUT DEBUG
		//vehicleDocuments.getStyle().set("border-style", "solid");
		
	}
	
	private void initDialogs() {
		dialogAddNewVehicleDocument = new DialogAddVehicleDocument(vc);
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

}
