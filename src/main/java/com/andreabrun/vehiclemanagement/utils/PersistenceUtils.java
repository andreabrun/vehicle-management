package com.andreabrun.vehiclemanagement.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistenceUtils {
	
	public static final String VEHICLE_PATH = "persistence/vehicles/";
	public static final String ASSETS_PATH = "persistence/assets/";
	public static final String DOCUMENTS_PATH = "persistence/documents/";
	public static final String PERSISTENCEHELPER_PATH = "persistence/persistence/";
	
	public static final String ASSETS_KEY = "asset";
	
	public static final String EXT = ".xml";
	
	public static boolean createFolder(String path) {
		File f = new File(path);
		return f.mkdir();
	}
	
	public static boolean createFolderIfNotPresent(String folderPath) {
		if(isFolderPresent(folderPath)) 
			return true;
		return createFolder(folderPath);
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
	
	public static List<String> getFilesInFolder(String path) {
		File f = new File(path);
		String[] list = f.list();
		if(list != null)
			return Arrays.asList(f.list());
		return null;
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

}
