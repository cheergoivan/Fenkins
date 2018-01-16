package com.github.cheergoivan.fenkins.entity.project;

import java.io.Serializable;

public class ProjectId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 736613682269179890L;
	
	private String id;
	private String project;
	
	public ProjectId(String id, String project) {
		super();
		this.id = id;
		this.project = project;
	}
	
	public ProjectId() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
}