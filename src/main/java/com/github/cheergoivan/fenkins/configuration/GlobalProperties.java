package com.github.cheergoivan.fenkins.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("fenkins")
public class GlobalProperties {
	
	private String settingsFile = "../conf/settings.yml";

	public String getSettingsFile() {
		return settingsFile;
	}

	public void setSettingsFile(String settingsFile) {
		this.settingsFile = settingsFile;
	}
}
