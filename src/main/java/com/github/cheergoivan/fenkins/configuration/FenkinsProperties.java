package com.github.cheergoivan.fenkins.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("fenkins")
public class FenkinsProperties {
	
	private String settingsFile = "../conf/settings.yml";
	
	private String workspace = "/.fenkins/";
	
	private String storage = workspace + ".projects";
	
	private String log = workspace + "log/";

	public String getSettingsFile() {
		return settingsFile;
	}

	public void setSettingsFile(String settingsFile) {
		this.settingsFile = settingsFile;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
}
