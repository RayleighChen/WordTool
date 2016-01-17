package com.rayleigh.nina.bean;

import java.util.HashMap;

public abstract class XmlBean {
	protected String	filePath;
	protected String	rootNode;

	public String getFilePath() {
		return filePath;
	}

	public String getRootNode() {
		return rootNode;
	}

	public abstract HashMap<String, String> getTagAndValue();

	public abstract void setTagAndValue(HashMap<String, String> resultSet);

	public abstract String getSelectString();
}
