package com.test.js.jsper;

public class Coll {

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	private String name;
	private String collegeName;
	
	@Override
	public String toString() {
		return "Coll [name=" + name + ", collegeName=" + collegeName + "]";
	}
	
	
	
}
