package com.andreabrun.vehiclemanagement.entities;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

@XmlRootElement
public class VehicleDocument implements Persistable {

	public static final String UNCATEGORIZED_DOCUMENT_KEY = "Generic";
	
	// The filename will be PERSISTENCE_KEY + id
	public static final String PERSISTENCE_KEY = "VehicleDocument";
	public static final String PERSISTENCE_FOLDER_KEY = "VDocs";
	
	Long id;
	
	@UserFillable(label = "Categoria", order = 0, type = UserFillable.COMBOBOX, valueSource = "getVehicleDocumentTypeKeys")
	String key;
	
	@UserFillable(label = "Descrizione", order = 1)
	String description;
	
	@XmlTransient
	@UserFillable(label = "Data", order = 2, type = UserFillable.DATE)
	LocalDate date;
	
	@UserFillable(label = "km", order = 3, type = UserFillable.INTEGER)
	Integer mileage;
	
	String vdPath;
	String documentsPath;
	
	@UserFillable(label = "Costo", order = 4, type = UserFillable.DOUBLE)
	Float charge;
	
	@UserFillable(label = "Informazioni", order = 5, type = UserFillable.TEXTLIST)
	List<String> items;
	
	public VehicleDocument() {
		super();
	}
	
	public VehicleDocument(VehicleContainer vc) {
		this(vc, UNCATEGORIZED_DOCUMENT_KEY, LocalDate.now(), vc.getCurrentMileage(), null, null);
	}
	
	public VehicleDocument(VehicleContainer vc, String key) {
		this(vc, key, LocalDate.now());
	}
	
	public VehicleDocument(VehicleContainer vc, String key, LocalDate date) {
		this(vc, key, date, null);
	}
	
	public VehicleDocument(VehicleContainer vc, String key, LocalDate date, Integer mileage) {
		this(vc, key, date, mileage, null, (float) 0);
	}
	
	public VehicleDocument(VehicleContainer vc, String key, LocalDate date, Integer mileage, String description, Float charge) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.date = date;
		this.mileage = mileage;
		this.description = description;
		this.charge = charge;
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
	public Integer getMileage() {
		return mileage != null ? mileage : 0;
	}
	
	@XmlElement
	public String getStringDate() {
		return date.toString();
	}
	
	@XmlElement
	public String getDocumentsPath() {
		return documentsPath;
	}
	
	@XmlElement
	public Float getCharge() {
		return charge;
	}
	
	@XmlElement
	public List<String> getItems() {
		return items;
	}
	
	@XmlTransient
	public LocalDate getDate() {
		return date;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setStringDate(String date) {
		this.date = LocalDate.parse(date);
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public void setDocumentsPath(String documentsPath) {
		this.documentsPath = documentsPath;
	}
	
	public void setvdPath(String vdPath) {
		this.vdPath = vdPath;
	}

	public void setCharge(Float charge) {
		this.charge = charge;
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
			VehicleXMLService.marshalToXML(this, VehicleDocument.class, this.getFileName());
			createFolder();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
