package com.rayleigh.nina.controller;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.util.XmlUtil;

public class T {

	public static void main(String[] args) {
		// new
		// FileTool().copy("E:\\Learning\\helpOthers\\sd\\冲压模具设计及实例精选\\拆分后\\附录\\01 冲模零件材料及热处理标准.docx",
		// "E:\\Learning\\helpOthers\\sd\\冲压模具设计及实例精选\\拆分后\\附录\\wf\\tem.pdf");
		
//		DocBean docBean = new DocBean();
//
//		XmlUtil xmlUtil = new XmlUtil(docBean);
//		xmlUtil.getIndexOfBean(0);
//		String[] temp = new FileTool()
//				.getFolder("E:\\Learning\\helpOthers\\sd\\冲压模具设计及实例精选\\拆分后\\附录");
//		for (int i = 0; i < temp.length; i++) {
//			System.out.println(temp[i]);
//		}
		
		FileTool.createFileTree("E:\\Learning\\helpOthers\\sd\\冲压模具设计及实例精选\\拆分后\\附录");
	}

}