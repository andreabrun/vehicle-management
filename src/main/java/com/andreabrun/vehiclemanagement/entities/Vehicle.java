package com.andreabrun.vehiclemanagement.entities;

import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehicle {
	
	Long id;
	
	String name;
	
	String make; // marca
	
	String model; // modello
	
	String type;
	
	String powerSource;
	
	Double displacement;
	
	Double power;
	
	String numberPlate; // targa
	
	String vin;
	
	public Vehicle() {
		id = PersistenceUtils.getNextID();
		name = "New Vehicle";
	}
	
	public Vehicle(String name, String make, String model) {
		id = PersistenceUtils.getNextID();
		this.name = name;
		this.make = make;
		this.model = model;
	}
	
	@XmlElement
    public Long getId() {
        return id;
    }
	
	@XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    
    @XmlElement
    public String getModel() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
