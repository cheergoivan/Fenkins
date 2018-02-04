package com.github.cheergoivan.fenkins.entity.settings.project;

import javax.validation.constraints.NotNull;

public class Git {
	@NotNull(message = "Git url mustn't be null!")
	private String url;
	@NotNull(message = "Git credential mustn't be null!")
	private Credential credential;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
}

