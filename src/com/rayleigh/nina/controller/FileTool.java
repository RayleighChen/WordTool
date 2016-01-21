package com.rayleigh.nina.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;

public class FileTool {

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
			mkdir(root + "/" + existFilesList[i].substring(0, existFilesList[i].lastIndexOf('.')));
			mkdir(root + "/" + existFilesList[i].substring(0, existFilesList[i].lastIndexOf('.')) + "/原搞");
			mkdir(root + "/" + existFilesList[i].substring(0, existFilesList[i].lastIndexOf('.')) + "/拆分后");
			copy(root + "/" + existFilesList[i], root + "/" + existFilesList[i].substring(0, existFilesList[i].lastIndexOf('.')) + "/原搞/" + existFilesList[i]);
		}
	}
	
	public static void mkdir(String root) {
		new File(root).mkdir();
	}
	
	public static String[] getNewFiles(String root){
		String[] filesList = readFolder(root);
		String[] folderList = getFolder(root);
		String[] existFilesList = removeExist(folderList, filesList);
		return existFilesList;
	}
	private static String[] removeExist(String[] target, String[] source) {
		HashMap<String, Integer> isExistMap = new HashMap<String, Integer>();
		for (int i = 0; i < target.length; i++) {
			isExistMap.put(target[i], 0);
		}
		
		ArrayList<String> notExistFiles = new ArrayList<String>();
		
		for (int j = 0; j < source.length; j++) {
			if (isExistMap.get(source[j].substring(0, source[j].lastIndexOf('.'))) == null) {
				notExistFiles.add(source[j]);
				System.out.println("新增文件夹: " + source[j].substring(0, source[j].lastIndexOf('.')));
			}
		}

		return (String[]) notExistFiles.toArray(new String[notExistFiles.size()]);
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
