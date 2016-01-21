package com.rayleigh.nina.bean;

public class OutlineBean {
	private String name;
	private String parent;
	private String level;
	
	private boolean isFile;
	private boolean isFold;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	public boolean isFold() {
		return isFold;
	}
	public void setFold(boolean isFold) {
		this.isFold = isFold;
	}

}
