package com.rayleigh.nina.controller;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.util.WordUtil;
import com.rayleigh.nina.util.XmlUtil;

public class T {

	public static void main(String[] args) {
		WordUtil wdUtil = new WordUtil(true);
		wdUtil.openDocument("D:\\¾«Ñ¡2.doc");
		wdUtil.pic();
		System.out.println("Test");
	}

}