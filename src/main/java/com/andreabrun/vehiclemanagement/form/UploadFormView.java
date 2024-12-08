package com.andreabrun.vehiclemanagement.form;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

public class UploadFormView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    public static final String TYPE_IMAGE = "image";

    private final int num;
    private final String type;
    private final int maxFileSize = 2097152; // 20 MB
    
	public UploadFormView(int num, String type) {
		
		this.num = num;
		this.type = type;
		
        MemoryBuffer buffer = new MemoryBuffer();

        Upload upload = new Upload(buffer);
        upload.setMaxFiles(this.num); // Limit to one file at a time
        upload.setDropAllowed(true); 
        if(this.type.equals(TYPE_IMAGE)) {
        	upload.setAcceptedFileTypes(".png");
        }
        upload.setMaxFileSize(this.maxFileSize);
        
        upload.addSucceededListener(e -> {
        	
            String fileName = e.getFileName();
            long fileSize = 0;
			try {
				fileSize = buffer.getInputStream().available();
			} catch (IOException exp) {
				exp.printStackTrace();
			}

            Notification.show("File uploaded: " + fileName + " (" + fileSize + " bytes)");
            
        });
        
        Button saveButton = new Button("Save");
        saveButton.addClickListener( e -> {

        	// TODO: Aggiungere controlli ed eventualmente spostare in PersistenceUtils
        	String inputName = buffer.getFileName();
        	int indexOfPoint = inputName.lastIndexOf('.');
        	String extension = inputName.substring(indexOfPoint);
        	String fileName = PersistenceUtils.ASSETS_KEY + PersistenceHelper.getNextID() + extension;
        	
        	// Create the target file
            File targetFile = new File(PersistenceUtils.ASSETS_PATH, fileName);

            // Write the content of the MemoryBuffer to the file
            try (
            	InputStream inputStream = buffer.getInputStream();
            	FileOutputStream outputStream = new FileOutputStream(targetFile)) {

                byte[] bufferBytes = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(bufferBytes)) != -1) {
                    outputStream.write(bufferBytes, 0, bytesRead);
                }
                
                Notification.show("File saved: " + fileName);
                
            } catch (IOException e1) {
				e1.printStackTrace();
			}

        });

        Button clearButton = new Button("Clear", event -> upload.getElement().setPropertyJson("files", null));

        add(upload, saveButton, clearButton);
    }
}
