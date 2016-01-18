package com.rayleigh.nina.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class FileTool {
	private static Properties props;
	private static FileInputStream fis;

	private static void copy(String source, String target) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldFile = new File(source);
			if (oldFile.exists()) {
				InputStream inStream = new FileInputStream(oldFile);
				FileOutputStream fs = new FileOutputStream(target);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	private static String[] readFolder(String root) {
		File file = new File(root);
		if (file.isDirectory()) {
			String[] fileList = file.list(new DocFilter());
			return fileList;
		}
		return null;
	}

	private static String[] getFolder(String root) {
		File file = new File(root);
		String[] files = null;
		if (file.isDirectory()) {
			files = file.list();
		}
		
		ArrayList<String> folder = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			
			if (new File(root + "/" + files[i]).isDirectory()) {
				folder.add(files[i]);
			}
		}
		return (String [])folder.toArray(new String[folder.size()]);
	}
	
	public static void createFileTree(String root) {

		String[] existFilesList = getNewFiles(root);

		for (int i = 0; i < existFilesList.length; i++) {
			new File(root + "/" + existFilesList[i]).mkdir();
			new File(root + "/" + existFilesList[i] + "/原搞").mkdir();
			new File(root + "/" + existFilesList[i] + "/拆分后").mkdir();
		}
		
		String[] files = readFolder(root);
		for (int i = 0; i < files.length; i++) {
			copy(root + "/" + files[i], root + "/" + files[i].substring(0, files[i].lastIndexOf('.')) + "/原搞/" + files[i]);
			
		}
	}
	
	public static void mkdir(String root) {
		new File(root).mkdir();
	}
	
	public static String[] getNewFiles(String root){
		String[] filesList = readFolder(root);
		for (int i = 0; i < filesList.length; i++) {
			if ((filesList[i] != null) && (filesList[i].length() > 0)) {
				int dot = filesList[i].lastIndexOf('.');
				if ((dot > -1) && (dot < filesList[i].length())) {
					filesList[i] = filesList[i].substring(0, dot);
				}
			}
		}

		String[] folderList = getFolder(root);
		String[] existFilesList = removeNotExist(filesList, folderList);
		return existFilesList;
	}
	private static String[] removeNotExist(String[] target, String[] source) {
		HashMap<String, Integer> isExistMap = new HashMap<String, Integer>();
		for (int i = 0; i < target.length; i++) {
			isExistMap.put(target[i], 0);
		}

		for (int j = 0; j < source.length; j++) {
			if (isExistMap.get(source[j]) != null) {
				isExistMap.put(source[j], 1);
			}
		}

		ArrayList<String> existFiles = new ArrayList<String>();
		Iterator<String> iterator = isExistMap.keySet().iterator();
		while (iterator.hasNext()) {
			String filename = iterator.next();
			if (isExistMap.get(filename) == 1) {
				existFiles.add(filename);
			}
			if (isExistMap.get(filename) == 0) {
				existFiles.add(filename);
				System.out.println("新增文件夹: " + filename);
			}
		}

		return (String[]) existFiles.toArray(new String[existFiles.size()]);
	}

	private static class DocFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String fname) {
			// TODO Auto-generated method stub
			boolean flagDoc = fname.toLowerCase().endsWith(".doc") ? true : false;
			boolean flagDocx = fname.toLowerCase().endsWith(".docx") ? true : false;
			return flagDoc | flagDocx ;
		}

	}
}
