package com.github.cheergoivan.fenkins.entity.project;

import java.io.Serializable;

public class ProjectIdentity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 736613682269179890L;
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}