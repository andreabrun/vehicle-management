package com.andreabrun.vehiclemanagement.entities;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VehicleDocument {
	
	public static final String UNCATEGORIZED_DOCUMENT_KEY = "null";
	
	Long id;
	
	String key;
	
	LocalDate date;
	
	String documentPath;
	
	Double charge;
	
	public VehicleDocument() {
		this(UNCATEGORIZED_DOCUMENT_KEY, LocalDate.now(), null, null);
	}
	
	public VehicleDocument(String key) {
		this(key, LocalDate.now(), null, null);
	}
	
	public VehicleDocument(String key, LocalDate date) {
		this(key, date, null, null);
	}
	
	public VehicleDocument(String key, LocalDate date, String documentPath, Double charge) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.date = date;
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
	public LocalDate getDate() {
		return date;
	}
	
	@XmlElement
	public String getDocumentPath() {
		return documentPath;
	}
	
	@XmlElement
	public Double getCharge() {
		return charge;
	}
}
