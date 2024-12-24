package com.andreabrun.vehiclemanagement.entities.services;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.VehicleDocumentType;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class VehicleXMLService {
	
	public static final  List<Class<?>> serializableClasses = Arrays.asList(Vehicle.class, VehicleContainer.class, VehicleDocument.class, VehicleDocumentType.class, 
																				PersistenceHelper.class);

    public static void marshalToXML(Object serializableObj, Class<?> clazz, String filename) throws JAXBException {
    	if(!serializableClasses.contains(clazz))
    		throw new NotImplementedException();
    	
    	if(clazz.isInstance(serializableObj)) {
	        JAXBContext context = JAXBContext.newInstance(clazz);
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(serializableObj, new File(filename));
    	}
    }

    public static Object unmarshalFromXML(String filename, Class<?> clazz) throws JAXBException {
    	if(!serializableClasses.contains(clazz))
    		throw new NotImplementedException();
    	
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(new File(filename));
    }
}