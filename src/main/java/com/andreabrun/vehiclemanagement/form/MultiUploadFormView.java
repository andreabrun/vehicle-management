package com.andreabrun.vehiclemanagement.form;
import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

public class MultiUploadFormView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_DOCUMENT = "document";
    
    public static final int WIDTH = 300;

    private final int num;
    private final String type;
    private final int maxFileSize = 41943040; // 40 MB
    
    Upload upload;
    MultiFileMemoryBuffer buffer;
    Set<String> filenames;
    
	public MultiUploadFormView(int num, String type) {
		
		this.num = num;
		this.type = type;
		buffer = new MultiFileMemoryBuffer();
		this.filenames = null;
		
        upload = new Upload(buffer);
        upload.setMaxFiles(this.num); // Limit to one file at a time
        upload.setDropAllowed(true); 
        setAcceptedFileTypes();
        upload.setMaxFileSize(this.maxFileSize);
        
        upload.addSucceededListener(e -> {

			filenames = buffer.getFiles();

			StringBuffer sb = new StringBuffer("File uploaded: ");
			for(String filename : filenames) sb.append(filename + "\n");
            Notification.show(sb.toString());
            
        });

        Button clearButton = new Button("Clear", event -> {
        	buffer.getFiles().clear();
        	upload.clearFileList();
        	filenames = null;
        });

        add(upload, clearButton);
        setWidth(WIDTH + "px");
    }
	
	private void setAcceptedFileTypes() {
		if(this.type.equals(TYPE_IMAGE)) {
        	upload.setAcceptedFileTypes("image/*");
        } 
        else if(this.type.equals(TYPE_DOCUMENT)) {
        	upload.setAcceptedFileTypes(".pdf");
        }
	}
	
	public MultiFileMemoryBuffer getBuffer() {
		return buffer;
	}
	
	public Set<String> getFilenames() {
		return filenames;
	}
}
