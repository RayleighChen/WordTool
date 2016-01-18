/**
 * 
 */
package com.rayleigh.nina.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * @Description ����ָ���ļ����������ļ� ����ŵ�nameList������
 * @author wangxf
 * 
 */
public final class FileOperateUtil {

	private static ArrayList<String>	fileList	= new ArrayList<String>();

	/**
	 * @param fileList
	 *            the fileList to set
	 */
	public static void setFileList(ArrayList<String> fileList) {
		FileOperateUtil.fileList = fileList;
	}

	/**
	 * @return the fileList
	 */
	public static ArrayList<String> getFileList() {
		return fileList;
	}

	/**
	 * �ж��ļ��Ƿ����
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean fileExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	/**
	 * ��������ļ�������
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs();
			File oldDir = new File(oldPath);
			if (!oldDir.exists())
				return;
			String[] files = oldDir.list();
			File temp = null;
			if (files == null || files.length == 0)
				return;
			for (int i = 0; i < files.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + files[i]);
				} else {
					temp = new File(oldPath + File.separator + files[i]);
				}
				if (temp.isFile()) {
					fileChannelCopy(temp.getAbsolutePath(), newPath + "/" + (temp.getName().toString()));
				}
				if (temp.isDirectory()) {
					copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��������ļ������� ��������delFileSuf��β���ļ�
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath, String delFileSuf) {
		try {
			(new File(newPath)).mkdirs();
			File oldDir = new File(oldPath);
			String[] files = oldDir.list();
			File temp = null;
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(delFileSuf))
					continue;
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + files[i]);
				} else {
					temp = new File(oldPath + File.separator + files[i]);
				}
				if (temp.isFile()) {
					fileChannelCopy(temp.getAbsolutePath(), newPath + "/" + (temp.getName().toString()));
				}
				if (temp.isDirectory()) {
					copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i], delFileSuf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ʹ���ļ�ͨ���ķ�ʽ�����ļ�
	 * 
	 * @param sourceFile
	 *            Դ�ļ�
	 * @param targetFile
	 *            ���Ƶ������ļ�
	 */
	public static boolean fileChannelCopy(String sourceFile, String targetFile) {

		FileInputStream fileInput = null;
		FileOutputStream fileOutput = null;
		FileChannel in = null;
		FileChannel out = null;
		boolean flag;
		try {
			File srcFile = new File(sourceFile);
			File desFile = new File(targetFile);
			// System.out.println("desFile:" + desFile.getParent());
			if (!desFile.exists()) {
				// Ŀ���ļ�������
				if (!desFile.getParentFile().exists()) {
					// Ŀ���ļ��ϲ㲻���ڣ�������ӦĿ¼
					desFile.getParentFile().mkdirs();
				}
			}
			if (desFile.isDirectory()) {
				System.out.println(targetFile + " is a directory");
			}

			fileInput = new FileInputStream(srcFile);
			fileOutput = new FileOutputStream(desFile);
			in = fileInput.getChannel();// �õ���Ӧ���ļ�ͨ��
			out = fileOutput.getChannel();// �õ���Ӧ���ļ�ͨ��
			in.transferTo(0, in.size(), out);// ��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��
			flag = true;
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				if (!(fileInput == null))
					fileInput.close();
				if (!(in == null))
					in.close();
				if (!(fileOutput == null))
					fileOutput.close();
				if (!(out == null))
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// ���ǰ׺�ͺ�׺�Լ�Buffer�������ļ��к��ļ�������buffer����д���ļ���
	public static boolean fillFile(String filePath, StringBuffer buffer) throws IOException {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			// �ϲ�Ŀ¼�����ڣ��򴴽��ϲ�Ŀ¼
			file.getParentFile().mkdirs();
			file = new File(filePath);
			if (!file.exists()) {
				System.out.println(filePath + ":can not create");
				file = new File(filePath);
			}
		}
		if (file.isDirectory()) {
			System.err.println(filePath + " is a directory,please set a file.");
			return false;
		}
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(filePath, false), "GBK");
		fileWriter.write(buffer.toString());
		fileWriter.flush();
		fileWriter.close();
		return true;
	}

	/**
	 * ����ļ�
	 * 
	 * @param fileName
	 * @param bufferString
	 * @return
	 */
	public static boolean fillFile(String fileName, String bufferString) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(bufferString);
			bw.flush();
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * ����Ŀ¼���ҵ���Ŀ¼�µ������ļ�
	 */
	public static void refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (File file : files) {
			if (file.isDirectory()) {
				// �ļ���Ŀ¼������б���
				refreshFileList(file.getAbsolutePath());
			} else {
				// �ļ����ļ�����ֱ�ӽ����ŵ��б���
				// System.out.println("---" + file.getAbsolutePath());
				fileList.add(file.getAbsolutePath());
			}
		}
	}

	/**
	 * �ж�filePath�Ƿ�����startString��ͷ���ļ�
	 * 
	 * @param filePath
	 * @param startString
	 * @return
	 */
	public static boolean findPrefixFile(String filePath, String prefixString) {
		File file = new File(filePath);
		return file.getName().startsWith(prefixString) ? true : false;
	}

	/**
	 * �ж�filePath�Ƿ�����suffixString��β���ļ�
	 * 
	 * @param filePath
	 * @param startString
	 * @return
	 */
	public static boolean findSuffixFile(String filePath, String suffixString) {
		File file = new File(filePath);
		return file.getName().endsWith(suffixString) ? true : false;
	}

	// �ж��ļ��Ƿ�����ͨ�����ļ���׺
	public static boolean findWildcardSuffixFile(String filePath, String wildcard, String suffixString) {
		File file = new File(filePath);
		boolean ret = file.exists();
		if (ret == false) {
			System.out.println(filePath + " is not a valid path");
			return ret;
		}
		ret = file.getName().endsWith(suffixString);
		if (ret == false) {
			// System.out.println(filePath + " is not a " + suffixString
			// + " file.");
			return ret;
		}
		if (file.getName().indexOf(wildcard) > 0) {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}

	public static boolean dirHasChildren(String filePath, String wildcard, String suffixString) {
		File file = new File(filePath);
		boolean ret = file.exists();
		if (ret == false) {
			System.out.println(filePath + " is not a valid path");
			return ret;
		}
		ret = file.isFile();
		if (file.isFile()) {
			// System.out.println(filePath + " is not a valid directory");
			return false;
		}
		refreshFileList(filePath);
		return ret;
	}

	/**
	 * ���Ŀ¼�Ƿ�Ϸ�
	 * 
	 * @param perlDir
	 * @return
	 */
	public static String checkDir(String dir) {
		try {
			File file = new File(dir);
			if (!file.isDirectory())
				return "[" + dir + "]Ŀ¼���Ϸ�!";
		} catch (Exception e) {
			return "[" + dir + "]Ŀ¼������!";
		}
		System.out.println("[" + dir + "]Ŀ¼����!");
		return null;
	}

	/**
	 * ��ȡһ���ļ������ļ��ĸ���
	 * 
	 * @param path
	 * @return
	 */
	public static int getFileNum(String path) {
		int num = 0;
		try {
			File dir = new File(path);
			if (!dir.exists()) {
				System.out.println(path + "�ļ��в�����");
				return 0;
				// throw new Exception("�ļ��в�����!");
			} else if (!dir.isDirectory()) {
				System.out.println(path + "�����ļ���");
				return 0;
				// throw new Exception("�ļ�������!");
			}
			String[] files = dir.list();
			if (files == null || files.length == 0)
				return num;
			File temp = null;
			for (int i = 0; i < files.length; i++) {
				temp = new File(path + files[i]);
				if (temp.isFile()) {
					num++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return num;
	}

	/**
	 * ��ȡһ���ļ�������suffix��β���ļ�����
	 * 
	 * @param path
	 * @param suffix
	 * @return
	 */
	public static int getFileNum(String path, String suffix) {
		int num = 0;
		try {
			File dir = new File(path);
			if (!dir.exists()) {
				return 0;
				// throw new Exception("�ļ��в�����!");
			} else if (!dir.isDirectory()) {
				return 0;
				// throw new Exception("�ļ�������!");
			}
			String[] files = dir.list();
			if (files == null || files.length == 0)
				return num;
			File temp = null;
			for (int i = 0; i < files.length; i++) {
				if (!files[i].endsWith(suffix))
					continue;
				temp = new File(path + files[i]);
				if (temp.isFile()) {
					num++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return num;
	}

	/**
	 * ��ȡһ���ļ������ļ���suffix����µ�Ԫ�ؽ�β���ļ�����
	 * 
	 * @param path
	 * @param suffix
	 * @return
	 */
	public static int getFileNum(String path, String suffix[]) {
		int num = 0;
		try {
			File dir = new File(path);
			if (!dir.exists()) {
				// throw new Exception("�ļ��в�����!");
				return 0;
			} else if (!dir.isDirectory()) {
				return 0;
				// throw new Exception("�ļ�������!");
			}
			String[] files = dir.list();
			if (files == null || files.length == 0)
				return num;
			File temp = null;
			boolean flag;
			for (int i = 0; i < files.length; i++) {
				flag = false;
				for (int j = 0; j < suffix.length; j++) {
					if (files[i].endsWith(suffix[j])) {
						flag = true;
						break;
					}
				}
				if (!flag)
					continue;
				temp = new File(path + files[i]);
				if (temp.isFile()) {
					num++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return num;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("fileExist():" + fileExist("E:\\Office\\ETL�淶ָ��V1 0.doc"));
		ArrayList<String> startFileList = new ArrayList<String>();
		refreshFileList("D:\\git");
		for (String filename : fileList) {
			// System.out.println(filename);
			fileChannelCopy(filename, filename.replace("D:\\git", "E:\\���\\Program files"));
			if (findSuffixFile(filename, "java")) {
				startFileList.add(filename);
			}
		}

		for (String st : startFileList) {
			System.out.println(st);
		}
	}
}
