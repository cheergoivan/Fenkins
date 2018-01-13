package com.github.cheergoivan.fenkins.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.cheergoivan.fenkins.entity.settings.GlobalSettings;
import com.github.cheergoivan.fenkins.util.yaml.YamlUtils;

@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
public class SettingsLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsLoader.class);

	@Autowired
	private GlobalProperties globalProperties;

	@Bean
	@Profile("develop")
	public GlobalSettings loadGlobalSettings() {
		return YamlUtils.load(this.getClass().getResourceAsStream("/settings.yml"), GlobalSettings.class);
	}

	@Bean
	@Profile("product")
	public GlobalSettings loadGlobalSettingsInProduct() {
		String settingsFile = globalProperties.getSettingsFile();
		try {
			return YamlUtils.load(new FileInputStream(settingsFile), GlobalSettings.class);
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
