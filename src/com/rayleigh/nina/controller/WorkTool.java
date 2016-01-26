package com.rayleigh.nina.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.bean.OutlineBean;
import com.rayleigh.nina.util.FileUtil;
import com.rayleigh.nina.util.OutLineUtil;
import com.rayleigh.nina.util.WordUtil;
import com.rayleigh.nina.util.XmlUtil;

public class WorkTool {
	
	private String root;
	ArrayList<OutlineBean> outlins;
	private HashMap<String, OutlineBean> docFile = new HashMap<String, OutlineBean>();
	private HashMap<String, String> picFit = null;
	private HashMap<String, String> tableFit = null;
	
	
	public WorkTool(){
		// TODO Auto-generated method stub
		DocBean docBean = new DocBean();
		XmlUtil xmlUtil = new XmlUtil(docBean);
		xmlUtil.getIndexOfBean(0);
		
		root = docBean.getRoot();
		String filename = root.substring(0,
				root.lastIndexOf('/')) + "/outline.txt";

		outlins = new OutLineUtil()
				.readFileByLines(filename);
		for (int i = 0; i < outlins.size(); i++) {
			if (outlins.get(i).getName().indexOf(' ') > 0) {
				docFile.put(outlins.get(i).getName().substring(outlins.get(i).getName().indexOf(' '), outlins.get(i).getName().length()), outlins.get(i));
			}
			else {
				docFile.put(outlins.get(i).getName(), outlins.get(i));
			}
		}
		
		for (Iterator doc = docFile.entrySet().iterator(); doc.hasNext();) {
			Entry<String, OutlineBean> entry = (Entry<String, OutlineBean>) doc.next();
//			System.err.println(entry.getKey());
		}
	}
	
	public void work() {
		// TODO Auto-generated method stub
//		wdUtil.openDocument(root.replace("/", "\\"));
		WordUtil wdUtil = new WordUtil(true);
		wdUtil.openDocument(root);
		wdUtil.fitFirstDoc();
		int numOfParagraphs = wdUtil.getNumOfParagraphs();
		int i = 0;
;
		int picIndex = 0;
		int tableIndex = 0;
		boolean tmp = true;
		boolean tableFlag = false;
		boolean isfirst = true;
		OutlineBean outline = null;
		String simpleFile = null;
		
		while (i != numOfParagraphs) {
			
			
			String docContent = wdUtil.getParagraphFromAnotherDoc(root, i + 1);
			if (docContent.trim().indexOf(' ') >= 0) {				
				outline = docFile.get(docContent.trim().substring(docContent.trim().indexOf(' '), docContent.trim().length()));
				if (outline != null) {
					if (outline.isFile()) {
						
						if (!isfirst) {
							wdUtil.fitContent();
							wdUtil.fitTitle();
							wdUtil.fitPic(picFit);
							wdUtil.fitTable(tableFit);
							wdUtil.fitNewTimes();
							wdUtil.setTableStyle();
							System.out.println("文件处理完毕。。。");
							System.out.println();
						}
						
						simpleFile = root.substring(0,
								root.lastIndexOf('.')) + "/拆分后/" + outline.getParent() + "/word文件/" + outline.getName() + ".doc";
						System.err.println(simpleFile.replace("/", "\\"));
						wdUtil.openDocument(simpleFile.replace("/", "\\"));

						System.out.println("开始处理。。。" + outline.getParent() + "/word文件/" + outline.getName() + ".doc");
						wdUtil.replaceText("单个文件模板", docContent.trim().substring(docContent.trim().indexOf(' '), docContent.trim().length()));
						wdUtil.moveDown(1); 
						picIndex = 0;
						 tableIndex = 0;
						 isfirst = false;
						 picFit = new HashMap<String, String>();
						 tableFit = new HashMap<String, String>();
						 
					}
					i++;
					continue;
				}
			}
			
			if (docContent.substring(0, 1).equals("图")) {
				picIndex++;
				picFit.put(docContent, "图"+picIndex+" "+docContent.substring(docContent.indexOf(' '), docContent.length()));
			}
			if (docContent.substring(0, 1).equals("表")) {
				tableIndex++;
				tableFit.put(docContent, "表"+tableIndex+" "+docContent.substring(docContent.indexOf(' '), docContent.length()));
				tableFlag = true;
				tmp = false;
			}
			if (docContent.trim().length() >= 2) {
				if (docContent.trim().substring(0, 2).equals("附表")) {
					tableIndex++;
					tableFit.put(docContent, "附表"+tableIndex+" "+docContent.substring(docContent.indexOf(' '), docContent.length()));
					tableFlag = true;
					tmp = false;
				}
			}
			if (docContent.trim().length() == 2) {
				if (docContent.trim().substring(0, 2).equals("续表")) {
					tableFlag = true;
					tmp = false;
				}
			}
			if (tableFlag && tmp) {
				
				wdUtil.copyTableFromAnotherDoc(root, 1);
				wdUtil.openDocument(root);
				numOfParagraphs = wdUtil.getNumOfParagraphs();
				wdUtil.openDocument(simpleFile.replace("/", "\\"));
				wdUtil.moveEnd();
				tableFlag = false;
				i--;
			} else {
				wdUtil.copyParagraphFromAnotherDoc(root, i + 1);
				tmp = true;
			}
			i++;
		}
		FileUtil.copy(
				root.substring(0,
						root.lastIndexOf('/'))
						+ "/原稿/"
						+ root.substring(
								root.lastIndexOf('/'),
								root.length()), root);
		
		
	    System.out.println("Done");
	    

		
//		wdUtil.closeDocument();
//		wdUtil.close();

	}

	public static void main(String[] args) {
		new WorkTool().work();
	}
}
