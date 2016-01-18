package com.rayleigh.nina.util;

import com.rayleigh.nina.bean.DocBean;




public class MyTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

//
//		XmlUtil chen = new XmlUtil(bean1);
//		chen.getIndexOfBean(2);
//		// chen.parser();
//		
//		for(int i = 0; i < chen.getNumOfNode(); i++) {
//			chen.getIndexOfBean(i);
//			System.out.println(bean1.getSysName() + "*" + bean1.getSourcePath()
//					+ "*" + bean1.getTargetPath());
//		}
		
		//file:///E:/Learning/helpOthers/sd/Test
		
		DocBean docBean = new DocBean();
		
		docBean.setRoot("E:/Learning/helpOthers/sd/Test");
		XmlUtil xmlUtil = new XmlUtil(docBean);
		xmlUtil.modifyMsg();
//		xmlUtil.getIndexOfBean(0);
//		System.out.println(docBean.getRoot());
		// chen.addMsg();
	}


}
