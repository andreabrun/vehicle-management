package com.andreabrun.vehiclemanagement.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

@XmlRootElement
public class VehicleInformationType {
	
	public static final String UNCATEGORIZED_DOCUMENT_KEY = "Generic";
	
	private Long id;
	
	@UserFillable(label = "Categoria", order = 0, type = UserFillable.TEXT)
	private String key;
	
	public VehicleInformationType() {
		this(UNCATEGORIZED_DOCUMENT_KEY);
	}
	
	public VehicleInformationType(String key) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
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
}
