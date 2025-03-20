package com.andreabrun.vehiclemanagement.entities;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

@XmlRootElement
public class VehicleInformation implements Persistable {

	public static final String UNCATEGORIZED_DOCUMENT_KEY = "Generic";
	
	// The filename will be PERSISTENCE_KEY + id
	public static final String PERSISTENCE_KEY = "VehicleInformation";
	public static final String PERSISTENCE_FOLDER_KEY = "VDocs";
	
	Long id;
	
	@UserFillable(label = "Categoria", order = 0, type = UserFillable.COMBOBOX, valueSource = "getVehicleInformationTypeKeys")
	String key;
	
	@UserFillable(label = "Descrizione", order = 1)
	String description;
	
	String vdPath;
	String documentsPath;
	
	@UserFillable(label = "Informazioni", order = 2, type = UserFillable.TEXTLIST)
	List<String> items;
	
	public VehicleInformation() {
		super();
	}
	
	public VehicleInformation(VehicleContainer vc) {
		this(vc, UNCATEGORIZED_DOCUMENT_KEY, LocalDate.now(), vc.getCurrentMileage(), null, null);
	}
	
	public VehicleInformation(VehicleContainer vc, String key) {
		this(vc, key, LocalDate.now());
	}
	
	public VehicleInformation(VehicleContainer vc, String key, LocalDate date) {
		this(vc, key, date, null);
	}
	
	public VehicleInformation(VehicleContainer vc, String key, LocalDate date, Integer mileage) {
		this(vc, key, date, mileage, null, (float) 0);
	}
	
	public VehicleInformation(VehicleContainer vc, String key, LocalDate date, Integer mileage, String description, Float charge) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.description = description;
		this.vdPath = vc.getDocumentsPath();
		this.documentsPath = vdPath + "/" + PERSISTENCE_FOLDER_KEY + id.toString();
	}
	
	@XmlElement
    public Long getId() {
        return id;
    }
	
	@XmlElement
	public String getKey() {
		return key;
	}
	
	@XmlElement
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public String getDocumentsPath() {
		return documentsPath;
	}
	
	@XmlElement
	public List<String> getItems() {
		return items;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDocumentsPath(String documentsPath) {
		this.documentsPath = documentsPath;
	}
	
	public void setvdPath(String vdPath) {
		this.vdPath = vdPath;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
	private void createFolder() {
		PersistenceUtils.createFolderIfNotPresent(getDocumentsPath());
	}
	
	public List<String> getDocumentFiles() {
		return PersistenceUtils.getFilesInFolder(documentsPath);
	}
	
	public String getFileName() {
		return vdPath + "/" + PERSISTENCE_KEY + id.toString() + PersistenceUtils.EXT;
	}

	@Override
	public void persist() {
		try {
			VehicleXMLService.marshalToXML(this, VehicleInformation.class, this.getFileName());
			createFolder();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
