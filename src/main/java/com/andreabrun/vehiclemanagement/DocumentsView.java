package com.andreabrun.vehiclemanagement;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.andreabrun.vehiclemanagement.view.VehicleDocumentSummaryView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="/documents", layout = MainView.class)
public class DocumentsView extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	private VehicleSessionBean vsbean;
	
	public DocumentsView() {
		init();
		
		String title = "Documents";
		
		VehicleContainer vc = vsbean.getSelected();
		if(vc != null) {
			title += " " + vc.getVehicle().getName();
		}
		
		add(new H1(title));
		
		if(vc != null) {
			String documentsPath = vc.getDocumentsPath();
			List<String> docs = PersistenceUtils.getFilesInFolderWithKey(documentsPath, VehicleDocument.PERSISTENCE_KEY);
			
			for(String doc : docs) {
				try {
					VehicleDocument vd = (VehicleDocument) VehicleXMLService.unmarshalFromXML(documentsPath + "/" + doc, VehicleDocument.class);
					VehicleDocumentSummaryView vdsv = new VehicleDocumentSummaryView(vd, 300);
					add(vdsv);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void init() {
		this.vsbean = VaadinSession.getCurrent().getAttribute(VehicleSessionBean.class);
	}
}
