package com.andreabrun.vehiclemanagement.entities;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

@XmlRootElement
public class VehicleDocument {
	
	public static final String UNCATEGORIZED_DOCUMENT_KEY = "null";
	
	long id;
	
	String key;
	
	LocalDate date;
	
	String mileage;
	
	String documentPath;
	
	String charge;
	
	public VehicleDocument() {
		this(UNCATEGORIZED_DOCUMENT_KEY, LocalDate.now(), null, null, null);
	}
	
	public VehicleDocument(String key) {
		this(key, LocalDate.now());
	}
	
	public VehicleDocument(String key, LocalDate date) {
		this(key, date, null);
	}
	
	public VehicleDocument(String key, LocalDate date, String mileage) {
		this(key, date, mileage, null, null);
	}
	
	public VehicleDocument(String key, LocalDate date, String mileage, String documentPath, String charge) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.date = date;
		this.mileage = mileage;
		this.documentPath = documentPath;
		this.charge = charge;
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
}
