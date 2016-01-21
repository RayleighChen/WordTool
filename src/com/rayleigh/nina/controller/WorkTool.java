package com.rayleigh.nina.controller;

import java.util.HashMap;
import java.util.Map;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.util.FileUtil;
import com.rayleigh.nina.util.WordUtil;
import com.rayleigh.nina.util.XmlUtil;

public class WorkTool {
	
	private String root;
	
	private HashMap<String, String> picFit = new HashMap<String, String>();
	private HashMap<String, String> tableFit = new HashMap<String, String>();
	
	public void work(String root) {
		// TODO Auto-generated method stub
		WordUtil wdUtil = new WordUtil(true);
		DocBean docBean = new DocBean();
		XmlUtil xmlUtil = new XmlUtil(docBean);
		xmlUtil.getIndexOfBean(0);
//		wdUtil.openDocument(docBean.getRoot().replace("/", "\\"));
		wdUtil.openDocument("D:\\test.doc");
		wdUtil.fitFirstDoc();
		int numOfParagraphs = wdUtil.getNumOfParagraphs();
		int i = 0;
		wdUtil.openDocument("D:\\文件模板.doc");
		wdUtil.replaceText("单个文件模板", "模具工作零件的计算及初绘总装配图");
		wdUtil.moveDown(1);
		int picIndex = 0;
		int tableIndex = 0;
		boolean tmp = true;
		boolean tableFlag = false;
		while (i != numOfParagraphs) {
			
			
			String docContent = wdUtil.getParagraphFromAnotherDoc("D:\\test.doc", i + 1);
			
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
			if (tableFlag && tmp) {
				
				wdUtil.copyTableFromAnotherDoc("D:\\test.doc", 1);
				wdUtil.openDocument("D:\\test.doc");
				numOfParagraphs = wdUtil.getNumOfParagraphs();
				wdUtil.openDocument("D:\\文件模板.doc");
				wdUtil.moveEnd();
				tableFlag = false;
				i--;
			} else {
				wdUtil.copyParagraphFromAnotherDoc("D:\\test.doc", i + 1);
				tmp = true;
			}
			i++;
		}
		FileUtil.copy("D:\\test.docx", "D:\\test.doc");
		
	    System.out.println("Done");
	    
		wdUtil.fitContent();
		wdUtil.fitTitle();
		wdUtil.fitPic(picFit);
		wdUtil.fitTable(tableFit);
		wdUtil.fitNewTimes();
		wdUtil.setTableStyle();
		
//		wdUtil.closeDocument();
//		wdUtil.close();
	}

	public static void main(String[] args) {
		new WorkTool().work("");
	}
}
