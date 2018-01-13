package com.github.cheergoivan.fenkins.entity.settings.project;

public class Git {
	private String url;
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

