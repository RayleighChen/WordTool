package com.rayleigh.nina.controller;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.util.XmlUtil;

public class T {

	public static void main(String[] args) {
		// new
		// FileTool().copy("E:\\Learning\\helpOthers\\sd\\��ѹģ����Ƽ�ʵ����ѡ\\��ֺ�\\��¼\\01 ��ģ������ϼ��ȴ����׼.docx",
		// "E:\\Learning\\helpOthers\\sd\\��ѹģ����Ƽ�ʵ����ѡ\\��ֺ�\\��¼\\wf\\tem.pdf");
		
//		DocBean docBean = new DocBean();
//
//		XmlUtil xmlUtil = new XmlUtil(docBean);
//		xmlUtil.getIndexOfBean(0);
//		String[] temp = new FileTool()
//				.getFolder("E:\\Learning\\helpOthers\\sd\\��ѹģ����Ƽ�ʵ����ѡ\\��ֺ�\\��¼");
//		for (int i = 0; i < temp.length; i++) {
//			System.out.println(temp[i]);
//		}
		
		FileTool.createFileTree("E:\\Learning\\helpOthers\\sd\\��ѹģ����Ƽ�ʵ����ѡ\\��ֺ�\\��¼");
	}

}