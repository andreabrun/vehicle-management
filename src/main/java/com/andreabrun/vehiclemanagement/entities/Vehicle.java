package com.andreabrun.vehiclemanagement.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

@XmlRootElement(name = "vehicle")
public class Vehicle {
	
	Long id;
	
	@UserFillable(label = "Nome", order = 0)
	String name;
	
	@UserFillable(label = "Marca", order = 1)
	String brand; 
	
	@UserFillable(label = "Modello", order = 2)
	String model; 
	
	@UserFillable(label = "Tipo veicolo", order = 3)
	String type;
	
	@UserFillable(label = "Tipo di alimentazione", order = 4)
	String powerSource;
	
	@UserFillable(label = "Cilindrata", order = 5)
	String displacement;
	
	@UserFillable(label = "Potenza", order = 6)
	String power;
	
	@UserFillable(label = "Targa", order = 7)
	String numberPlate; 
	
	@UserFillable(label = "VIN", order = 8)
	String vin;
	
	public Vehicle() {
		this("New Vehicle", null, null);
	}
	
	public Vehicle(String name, String brand, String model) {
		id = PersistenceHelper.getNextID();
		this.name = name;
		this.brand = brand;
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
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    @XmlElement
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model= model;
    }

    @XmlElement
    public String getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}

	@XmlElement
	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	@XmlElement
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@XmlElement
	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	@XmlElement
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
