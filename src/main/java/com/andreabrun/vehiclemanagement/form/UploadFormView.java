package com.andreabrun.vehiclemanagement.form;
import java.io.IOException;

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
    
    MemoryBuffer buffer;
    String filename;
    
	public UploadFormView(int num, String type) {
		
		this.num = num;
		this.type = type;
		buffer = new MemoryBuffer();
		this.filename = null;
		
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(this.num); // Limit to one file at a time
        upload.setDropAllowed(true); 
        if(this.type.equals(TYPE_IMAGE)) {
        	upload.setAcceptedFileTypes("image/*");
        }
        upload.setMaxFileSize(this.maxFileSize);
        
        upload.addSucceededListener(e -> {
        	
            this.filename = e.getFileName();
            long fileSize = 0;
			try {
				fileSize = buffer.getInputStream().available();
			} catch (IOException exp) {
				exp.printStackTrace();
			}

            Notification.show("File uploaded: " + filename + " (" + fileSize + " bytes)");
            
        });

        Button clearButton = new Button("Clear", event -> {
        	upload.clearFileList();
        	filename = null;
        });

        add(upload, clearButton);
    }
	
	public MemoryBuffer getBuffer() {
		return buffer;
	}
	
	public String getFilename() {
		return filename;
	}
}
