package com.github.cheergoivan.fenkins.dto.project;

public class ProjectWebHookDTO {
	private String project;
	private String webhook;
	
	public ProjectWebHookDTO(){}
	
	public ProjectWebHookDTO(String project, String webhook) {
		super();
		this.project = project;
		this.webhook = webhook;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWebhook() {
		return webhook;
	}
	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}
}
