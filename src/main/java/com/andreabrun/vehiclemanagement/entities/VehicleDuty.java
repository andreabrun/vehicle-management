package com.andreabrun.vehiclemanagement.entities;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VehicleDuty {
	
	public static final String UNCATEGORIZED_DOCUMENT_KEY = "null";
	
	Long id;
	
	String key;
	
	List<VehicleDocument> documents;
	
	public VehicleDuty() {
		this(UNCATEGORIZED_DOCUMENT_KEY);
	}
	
	public VehicleDuty(String key) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.documents = new ArrayList<VehicleDocument>();
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
	public List<VehicleDocument> getDocuments() {
		return documents;
	}
	
	public void addDocument(VehicleDocument v) {
		this.documents.add(v);
	}
}
