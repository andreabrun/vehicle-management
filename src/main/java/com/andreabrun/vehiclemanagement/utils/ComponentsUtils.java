package com.andreabrun.vehiclemanagement.utils;

import java.io.File;
import java.io.FileInputStream;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

public class ComponentsUtils {

	public static Image getImageFromPath(String path) {
		File f = new File(path);

        StreamResource resource = new StreamResource(
            f.getName(), 
            () -> {
                try {
                    return new FileInputStream(f);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        );
		
		return new Image(resource, "Image");
	}
}
