package com.github.cheergoivan.fenkins.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.cheergoivan.fenkins.entity.project.ProjectId;
import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.id.IdGenerationService;
import com.github.cheergoivan.fenkins.util.serialization.SerializationUtils;
import com.github.cheergoivan.fenkins.util.yaml.YamlUtils;

@Configuration
public class SettingsLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsLoader.class);

	@Autowired
	private FenkinsProperties globalProperties;

	@Autowired
	private IdGenerationService idGenerationService;

	@Bean
	@Profile("develop")
	public Settings loadSettings() {
		Settings settings = YamlUtils.load(this.getClass().getResourceAsStream("/settings.yml"), Settings.class);
		return settings;
	}

	@Bean
	@Profile("product")
	public Settings loadSettingsInProduct() {
		String settingsFile = globalProperties.getSettingsFile();
		try {
			return YamlUtils.load(new FileInputStream(settingsFile), Settings.class);
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private Settings loadSettings(InputStream in, File storage) {
		try {
			List<ProjectId> ids = SerializationUtils.readAll(storage);
			Map<String, String> projectIdMap = ids.stream().collect(Collectors.toMap(ProjectId::getProject, ProjectId::getId));
			Settings settings = YamlUtils.load(in, Settings.class);
			for (Project project : settings.getProjects()) {
				if(!projectIdMap.containsKey(project.getName())) {
					String id = idGenerationService.generateId();
					projectIdMap.put(project.getName(), id);
					SerializationUtils.write(new ProjectId(id, project.getName()), storage);
				}
				project.setId(projectIdMap.get(project.getName()));
			}
			return settings;
		} catch (ClassNotFoundException | IOException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
