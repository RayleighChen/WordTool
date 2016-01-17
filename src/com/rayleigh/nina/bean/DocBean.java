package com.rayleigh.nina.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class DocBean extends XmlBean {
	private final String filePath = "res/doc.xml";
	private final String rootNode = "doc";

	private String root;

	@Override
	public HashMap<String, String> getTagAndValue() {
		// TODO Auto-generated method stub
		HashMap<String, String> tag_value = new HashMap<String, String>();
		tag_value.put("root", getRoot());
		return tag_value;
	}

	@Override
	public void setTagAndValue(HashMap<String, String> resultSet) {
		// TODO Auto-generated method stub
		Iterator<Entry<String, String>> itor = resultSet.entrySet().iterator();
		while (itor.hasNext()) {
			Entry<String, String> tv = itor.next();

			if (tv.getKey().toString().equals("root")) {
				setRoot(tv.getValue());
			}
		}
	}

	@Override
	public String getFilePath() {
		return filePath;
	}

	@Override
	public String getRootNode() {
		return rootNode;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	@Override
	public String getSelectString() {
		// TODO Auto-generated method stub
		return "/document/doc";
	}
}
