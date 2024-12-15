package com.andreabrun.vehiclemanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

import io.micrometer.common.util.StringUtils;

@XmlRootElement
public class VehicleContainer implements Persistable {
	
	// The filename will be PERSISTENCE_KEY + id
	public static final String PERSISTENCE_KEY = "VehicleContainer";
	
	Long id;
	
	Vehicle master;

	String coverImageName;
	
	List<String> configuredDuties;
	
	List<VehicleDuty> duties;
	
	String currentMileage;
	
	public VehicleContainer() {
		this(null);
	}
	
	public VehicleContainer(Vehicle v) {
		id = PersistenceHelper.getNextID();
		master = v;
		configuredDuties = new ArrayList<String>();
		duties = new ArrayList<VehicleDuty>();
	}
	
	// XML GETTERS AND SETTERS
	@XmlElement
    public Long getId() {
        return id;
    }
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement(name = "vehicle")
    public Vehicle getVehicle() {
        return master;
    }
	
	public void setVehicle(Vehicle vehicle) {
        master = vehicle;
    }
	
	@XmlElement
    public String getCoverImageName() {
        return coverImageName;
    }
	
	public void setCoverImageName(String coverImageName) {
        this.coverImageName = coverImageName;
    }
	
	@XmlElement
    public String getCurrentMileage() {
        return currentMileage;
    }
	
	public void setCurrentMileage(String currentMileage) {
        this.currentMileage = currentMileage;
    }
	
	@XmlElement
    public List<String> getConfiguredDuties() {
        return configuredDuties;
    }

    public void setConfiguredDuties(List<String> configuredDuties) {
        this.configuredDuties = configuredDuties;
    }
	
	@XmlElement
    public List<VehicleDuty> getDuties() {
        return duties;
    }

    public void setDuties(List<VehicleDuty> duties) {
        this.duties = duties;
    }
    // END XML GETTERS AND SETTERS
    
    public boolean isCoverImagePresent() {
    	return !StringUtils.isEmpty(getCoverImageName());
    }
    
    public String getCoverImagePath() {
        return getAssetsPath() + "/" + getCoverImageName();
    }
	
	public VehicleDuty getDuty(String key) {
		
		for(VehicleDuty vd : duties) {
			if(key.equals(vd.key))
				return vd;
		}
		return null;
	}
	
	@Override
	public String getFileName() {
		return PersistenceUtils.VEHICLE_PATH + PERSISTENCE_KEY + id.toString() + PersistenceUtils.EXT;
	}
	
	public String getDocumentsPath() {
		return PersistenceUtils.DOCUMENTS_PATH + PERSISTENCE_KEY + id.toString();
	}
	
	public String getAssetsPath() {
		return PersistenceUtils.ASSETS_PATH + PERSISTENCE_KEY + id.toString();
	}
	
	public void createAssetsFolder() {
		PersistenceUtils.createFolderIfNotPresent(getAssetsPath());
	}
	
	public void deleteAssetsFolder() {
		PersistenceUtils.delete(getAssetsPath());
	}
	
	public void createDocumentsFolder() {
		PersistenceUtils.createFolderIfNotPresent(getDocumentsPath());
	}
	
	public void deleteDocumentsFolder() {
		PersistenceUtils.delete(getDocumentsPath());
	}
	
	
	public void delete() {
		deleteAssetsFolder();
		deleteDocumentsFolder();
		PersistenceUtils.delete(getFileName());
	}
	
	public void persist() {
		try {
			
			VehicleXMLService.marshalToXML(this, VehicleContainer.class, this.getFileName());
			
			createAssetsFolder();
			createDocumentsFolder();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static List<VehicleContainer> findAll() {
		List<VehicleContainer> res = new ArrayList<VehicleContainer>();
		
		List<String> vcfiles = PersistenceUtils.getFilesInFolderWithKey(PersistenceUtils.VEHICLE_PATH, PERSISTENCE_KEY);
		for(String vcf : vcfiles) {
			VehicleContainer vc = null;
			try {
				vc = (VehicleContainer) VehicleXMLService.unmarshalFromXML(PersistenceUtils.VEHICLE_PATH + vcf, VehicleContainer.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(vc != null) {
				res.add(vc);
			}
		}
		
		return res;
	}
}
