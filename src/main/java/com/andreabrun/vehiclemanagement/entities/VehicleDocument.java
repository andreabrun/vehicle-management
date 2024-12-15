package com.andreabrun.vehiclemanagement.entities;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

@XmlRootElement
public class VehicleDocument implements Persistable {

	public static final String UNCATEGORIZED_DOCUMENT_KEY = "null";
	
	// The filename will be PERSISTENCE_KEY + id
	public static final String PERSISTENCE_KEY = "VehicleDocument";
	public static final String PERSISTENCE_FOLDER_KEY = "VDocs";
	
	Long id;
	
	String key;
	
	String description;
	
	LocalDate date;
	
	String mileage;
	
	String documentPath;
	
	String charge;
	
	public VehicleDocument() {
		super();
	}
	
	
	public VehicleDocument(VehicleContainer vc) {
		this(vc, UNCATEGORIZED_DOCUMENT_KEY, LocalDate.now(), null, null, null);
	}
	
	public VehicleDocument(VehicleContainer vc, String key) {
		this(vc, key, LocalDate.now());
	}
	
	public VehicleDocument(VehicleContainer vc, String key, LocalDate date) {
		this(vc, key, date, null);
	}
	
	public VehicleDocument(VehicleContainer vc, String key, LocalDate date, String mileage) {
		this(vc, key, date, mileage, null, null);
	}
	
	public VehicleDocument(VehicleContainer vc, String key, LocalDate date, String mileage, String description, String charge) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.date = date;
		this.mileage = mileage;
		this.description = description;
		this.charge = charge;
		this.documentPath = vc.getDocumentsPath() + "/" + PERSISTENCE_FOLDER_KEY + id.toString();
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
	public String getMileage() {
		return mileage;
	}
	
	@XmlElement
	public LocalDate getDate() {
		return date;
	}
	
	@XmlElement
	public String getDocumentPath() {
		return documentPath;
	}
	
	@XmlElement
	public String getCharge() {
		return charge;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	private void createFolder() {
		PersistenceUtils.createFolderIfNotPresent(getDocumentPath());
	}
	
	public List<String> getDocumentFiles() {
		return PersistenceUtils.getFilesInFolder(documentPath);
	}
	
	public String getFileName() {
		return documentPath + PERSISTENCE_KEY + id.toString() + PersistenceUtils.EXT;
	}

	@Override
	public void persist() {
		try {
			VehicleXMLService.marshalToXML(this, VehicleDocument.class, this.getFileName());
			createFolder();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
