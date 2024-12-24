package com.andreabrun.vehiclemanagement.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.Comparators;
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
	
	Map<String, VehicleDocumentType> documentTypes;
	
	public VehicleContainer() {
		this(null);
	}
	
	public VehicleContainer(Vehicle v) {
		id = PersistenceHelper.getNextID();
		master = v;
		configuredDuties = new ArrayList<String>();
		documentTypes = new HashMap<String, VehicleDocumentType>();
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
    public List<String> getConfiguredDuties() {
        return configuredDuties;
    }

    public void setConfiguredDuties(List<String> configuredDuties) {
        this.configuredDuties = configuredDuties;
    }
	
	@XmlElement
    public Map<String, VehicleDocumentType> getDocumentTypes() {
		return documentTypes;
    }

    public void setDocumentTypes(Map<String, VehicleDocumentType> docTypes) {
    	this.documentTypes = docTypes;
    }
    // END XML GETTERS AND SETTERS
    
    public boolean isCoverImagePresent() {
    	return !StringUtils.isEmpty(getCoverImageName());
    }
    
    public String getCoverImagePath() {
        return getAssetsPath() + "/" + getCoverImageName();
    }
    
    public void addDocumentType(VehicleDocumentType vdt) {
    	if(vdt != null)
    		this.documentTypes.put(vdt.getKey(), vdt);
    }
	
	public VehicleDocumentType getDocumentType(String key) {
		return documentTypes.get(key);
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
	
	public List<VehicleDocument> getAllDocuments() {
		
		List<VehicleDocument> documents = new ArrayList<VehicleDocument>();
		
		List<String> docFiles = PersistenceUtils.getFilesInFolderWithKey(getDocumentsPath(), VehicleDocument.PERSISTENCE_KEY);
		for(String doc : docFiles) {
			VehicleDocument d = null;
			try {
				d = (VehicleDocument) VehicleXMLService.unmarshalFromXML(getDocumentsPath() + "/" + doc, VehicleDocument.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(d != null) {
				documents.add(d);
			}
		}
		
		return documents;
	}
	
	public List<VehicleDocument> getAllDocumentsOrderedByDate() {
		List<VehicleDocument> documents = getAllDocuments();
		Collections.sort(documents, Comparators.VEHICLE_DOCUMENT_DATE.reversed());
		return documents;
	}
	
	public VehicleDocument getLatestVehicleDocument() {
		List<VehicleDocument> documents = getAllDocumentsOrderedByDate();
		if(documents.isEmpty())
			return null;
		return documents.get(0);
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
	
	// Functions for COMBOBOX List Values
	public List<String> getVehicleDocumentTypeKeys() {
		List<String> res = new ArrayList<String>();
		for(String s : getDocumentTypes().keySet()) {
			res.add(s);
		}
		return res;
	}
}
