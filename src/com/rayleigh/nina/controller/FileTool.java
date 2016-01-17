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

	static {
		props = new Properties();
		try {
			fis = new FileInputStream("res/SDM.properties");
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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


	public String[] readFolder() {
		File file = new File(props.getProperty("rootpath"));
		if (file.isDirectory()) {
			String[] fileList = file.list();
			return fileList;
		}
		return null;
	}

	public void createFileTree(String root, ArrayList<String> filesList)
			throws Exception {

		File rootFolder = new File(props.getProperty("rootpath") + root);
		if (!rootFolder.exists()) {
			if (!rootFolder.mkdir()) {
				throw new Exception("Ŀ¼�����ڣ�����ʧ�ܣ�");
			}
			copy(props.getProperty("modelfilepath").toString()
					+ props.getProperty("modelfile1name").toString(), props
					.getProperty("rootpath")
					+ root + "/" + props.getProperty("modelfile1name"));
		}
		new File(props.getProperty("rootpath") + root + "/PDM").mkdir();
		new File(props.getProperty("rootpath") + root + "/PDM/SDM").mkdir();

		File SDMFolder = new File(props.getProperty("sdmfilsall"));
		String[] existFilesList = removeNotExist((String[]) filesList
				.toArray(new String[filesList.size()]), SDMFolder
				.list(new DocFilter()));

		for (int i = 0; i < existFilesList.length; i++) {
			copy(
					props.getProperty("sdmfilsall").toString()
							+ existFilesList[i], props.getProperty("rootpath")
							+ root + "/PDM/SDM/" + existFilesList[i]);
		}
		new File(props.getProperty("rootpath") + root + "/PDM/SDM/log").mkdir();
		new File(props.getProperty("rootpath") + root + "/PDM/初始�?log").mkdir();
		new File(props.getProperty("rootpath") + root + "/SA").mkdir();
		new File(props.getProperty("rootpath") + root + "/打包").mkdir();
		new File(props.getProperty("rootpath") + root + "/应用").mkdir();
		copy(props.getProperty("modelfilepath").toString()
				+ props.getProperty("modelfile2name").toString(), props
				.getProperty("rootpath")
				+ root + "/应用/" + props.getProperty("modelfile2name"));
		new File(props.getProperty("rootpath") + root + "/应用/初始�?log").mkdir();
		new File(props.getProperty("rootpath") + root + "/应用/周期脚本").mkdir();
		new File(props.getProperty("rootpath") + root + "/应用/周期脚本/log").mkdir();

		System.out.println("OK");
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
				System.err.println("ERROR: " + filename + "文件没找到！");
			}
		}

		return (String[]) existFiles.toArray(new String[existFiles.size()]);
	}

	private class DocFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String fname) {
			// TODO Auto-generated method stub
			return fname.toLowerCase().endsWith(".doc") ? true : false;
		}

	}
}
