package com.andreabrun.vehiclemanagement.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

public class PersistenceUtils {
	
	public static final String BASE_PATH;
	
	// 20250618: Fix FileNotFoundException on windows
	static {
        String basePathProp = System.getProperty("base.path");
        if (basePathProp != null) {
            BASE_PATH = basePathProp + "/";
        } else {
            BASE_PATH = "";
        }
    }
	
	public static final String VEHICLE_PATH = BASE_PATH + "persistence/vehicles/";
	public static final String ASSETS_PATH = BASE_PATH + "persistence/assets/";
	public static final String DOCUMENTS_PATH = BASE_PATH + "persistence/documents/";
	public static final String PERSISTENCEHELPER_PATH = BASE_PATH + "persistence/persistence/";
	
	public static final String ASSETS_KEY = "asset";
	public static final String DOCUMENTS_KEY = "document";
	
	public static final String EXT = ".xml";
	
	public static final String SEPARATOR = "|-|";
	
	public static boolean createFolder(String path) {
		File f = new File(path);
		return f.mkdir();
	}
	
	public static boolean createFolderIfNotPresent(String folderPath) {
		if(isFolderPresent(folderPath)) 
			return true;
		return createFolder(folderPath);
	}
	
	public static void delete(String path) {
		File f = new File(path);
		_delete(f);
	}
	
	private static void _delete(File f) {
		
		if (f.isDirectory()) {
            File[] fs = f.listFiles();
            for (File file : fs) {
                if (file.isDirectory()) {
                	_delete(file);
                } else {
                    file.delete();
                }
            }
        }
		
        f.delete();
	}
	
	public static boolean isFolderPresent(String folderPath) {
		File f = new File(folderPath);
		if(f.exists())
			return true;
		return false;
	}
	
	public static boolean isFilePresent(String folderPath, String filename) {
		List<String> filesInFolder = getFilesInFolder(folderPath);
		if(filesInFolder != null)
			return filesInFolder.contains(filename);
		return false;
	}
	
	public static List<String> getFilesInFolder(String path, boolean fullPath) {
		File f = new File(path);
		String[] list = f.list();
		if(list != null) {
			if(!fullPath) 
				return Arrays.asList(f.list());
			
			List<String> res = new ArrayList<String>();
			for(String fname : list) {
				res.add(path + "/" + fname);
			}
			return res;
		}
		return null;
	}
	
	public static List<String> getFilesInFolder(String path) {
		return getFilesInFolder(path, false);
	}
	
	public static List<String> getFilesInFolderWithKey(String path, String key) {
		List<String> fip = getFilesInFolder(path);
		List<String> res = new ArrayList<String>();
		if(fip != null) {
			for(String f : fip) {
				if(f.startsWith(key))
					res.add(f);
			}
		}
		return res;
	}
	
	public static boolean renameFile(String oldPath, String newPath) {
		File oldf = new File(oldPath);
		File newf = new File(newPath);
		return oldf.renameTo(newf);
	}
	
	private static String getExtension(String filename) {
    	int indexOfPoint = filename.lastIndexOf('.');
    	String extension = "";
    	
    	if(indexOfPoint > 0)
    		extension = filename.substring(indexOfPoint);
    	return extension;
	}
	
	public static String getFilename(String filePath) {
    	int indexOfPoint = filePath.lastIndexOf('/');
    	String extension = "";
    	
    	if(indexOfPoint > 0)
    		extension = filePath.substring(indexOfPoint);
    	return extension;
	}

	public static List<String> saveUploadedDocuments(MultiFileMemoryBuffer buffer, VehicleDocument vd) {
		List<String> res = new ArrayList<String>();
		
		Set<String> filenames = buffer.getFiles();
		
		if(filenames == null) return res;
		
		for(String f : filenames) {
			String extension = getExtension(f);
			String fileName = PersistenceUtils.DOCUMENTS_KEY + PersistenceHelper.getNextID() + extension;
			// Create the target file
	        File targetFile = new File(vd.getDocumentsPath(), fileName);

	        try (
	        	InputStream inputStream = buffer.getInputStream(f);
	        	FileOutputStream outputStream = new FileOutputStream(targetFile)) {

	            byte[] bufferBytes = new byte[1024];
	            int bytesRead;

	            while ((bytesRead = inputStream.read(bufferBytes)) != -1) {
	                outputStream.write(bufferBytes, 0, bytesRead);
	            }
	            
	            res.add(targetFile.getName());
	        } catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	/**
	 * @param MemoryBuffer buffer
	 * @param VehicleContainer vc
	 * Saves the image in the correct folder of the VehicleContainer given as parameter.
	 * @return the file name or null if the saving does not end successfully
	 */
	public static String saveUploadedImage(MemoryBuffer buffer, VehicleContainer vc) {
		
		String res = null;
		
		String inputName = buffer.getFileName();
    	String extension = getExtension(inputName);
    	
    	String fileName = PersistenceUtils.ASSETS_KEY + PersistenceHelper.getNextID() + extension;
    	
    	// Create the target file
        File targetFile = new File(vc.getAssetsPath(), fileName);

        // Write the content of the MemoryBuffer to the file
        try (
        	InputStream inputStream = buffer.getInputStream();
        	FileOutputStream outputStream = new FileOutputStream(targetFile)) {

            byte[] bufferBytes = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(bufferBytes)) != -1) {
                outputStream.write(bufferBytes, 0, bytesRead);
            }
            
            res = targetFile.getName();
        } catch (Exception e) {
			e.printStackTrace();
		}
        
        return res;
	}

}
