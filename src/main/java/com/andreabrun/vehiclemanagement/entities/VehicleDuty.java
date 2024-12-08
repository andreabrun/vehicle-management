package com.andreabrun.vehiclemanagement.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.Comparators;

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
	
	public void setId(Long id) {
        this.id = id;
    }
	
	@XmlElement
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key= key ;
	}
	
	@XmlElement
	public List<VehicleDocument> getDocuments() {
		return documents;
	}
	
	public void setDocuments(List<VehicleDocument> documents) {
		this.documents = documents;
	}
	
	
	public void addDocument(VehicleDocument v) {
		this.documents.add(v);
	}
	
	public VehicleDocument getLatest() {
		
		if(documents.isEmpty())
			return null;
		
		Collections.sort(documents, Comparators.VEHICLE_DOCUMENT_DATE);
		return documents.get(documents.size() - 1);
	}
}
