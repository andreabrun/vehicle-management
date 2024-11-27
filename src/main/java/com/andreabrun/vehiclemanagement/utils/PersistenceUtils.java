package com.andreabrun.vehiclemanagement.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PersistenceUtils {
	
	public static final String VEHICLE_PATH = "persistence/vehicles/";
	public static final String ASSETS_PATH = "persistence/assets/";
	public static final String DOCUMENTS_PATH = "persistence/documents/";
	
	public static boolean createFolder(String path) {
		File f = new File(path);
		return f.mkdir();
	}
	
	public static List<String> getFilesInFolder(String path) {
		File f = new File(path);
		return Arrays.asList(f.list());
	}
	
	public static boolean renameFile(String oldPath, String newPath) {
		File oldf = new File(oldPath);
		File newf = new File(newPath);
		return oldf.renameTo(newf);
	}

}
