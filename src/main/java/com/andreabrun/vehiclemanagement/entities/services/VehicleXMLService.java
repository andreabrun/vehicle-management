package com.andreabrun.vehiclemanagement.entities.services;

import com.andreabrun.vehiclemanagement.entities.Vehicle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;

public class VehicleXMLService {

    public static void marshalToXML(Vehicle vehicle, String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Vehicle.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(vehicle, new File(filename));
    }

    public static Vehicle unmarshalFromXML(String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Vehicle.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Vehicle) unmarshaller.unmarshal(new File(filename));
    }
}