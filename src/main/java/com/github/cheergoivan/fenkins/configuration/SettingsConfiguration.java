package com.github.cheergoivan.fenkins.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.cheergoivan.fenkins.configuration.exception.IllegalSettingsException;
import com.github.cheergoivan.fenkins.configuration.exception.InitializationException;
import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.id.IdGenerationService;
import com.github.cheergoivan.fenkins.util.file.FileUtils;
import com.github.cheergoivan.fenkins.util.serialization.SerializationUtils;
import com.github.cheergoivan.fenkins.util.validation.ValidationResult;
import com.github.cheergoivan.fenkins.util.validation.ValidationUtils;
import com.github.cheergoivan.fenkins.util.yaml.YamlException;
import com.github.cheergoivan.fenkins.util.yaml.YamlUtils;

@Configuration
@AutoConfigureAfter(FenkinsStructureConfiguration.class)
public class SettingsConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsConfiguration.class);

	@Autowired
	private IdGenerationService idGenerationService;

	@Autowired
	private FenkinsStructure fenkinsStructure;

	@Bean
	public Settings loadSettingsInProduct() {
		File storage = initializeStorage();
		try {
			return loadSettings(new FileInputStream(fenkinsStructure.getFileSettings()), storage);
		} catch (FileNotFoundException e) {
			throw new InitializationException("Settings file doesn't exist!", e);
		}
	}

	private File initializeStorage() {
		File storage = fenkinsStructure.getFileProjectIdStorage();
		try {
			FileUtils.createFileIfNotExists(storage);
		} catch (IOException e) {
			throw new InitializationException("Fail to create a storage file!", e);
		}
		return storage;
	}

	private Settings loadSettings(InputStream in, File storage) {
		try {
			Settings settings = loadSettings(in);
			validateSettings(settings);
			Map<String, String> storedProjectIds = SerializationUtils.read(storage);
			if (storedProjectIds == null)
				storedProjectIds = new HashMap<>();
			Map<String, String> presentProjectIds = new HashMap<>();
			for (Project project : settings.getProjects()) {
				String id = storedProjectIds.computeIfAbsent(project.getName(),
						name -> idGenerationService.generateId());
				presentProjectIds.put(project.getName(), id);
				project.setId(id);
			}
			SerializationUtils.write(presentProjectIds, storage, false);
			return settings;
		} catch (ClassNotFoundException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new IllegalSettingsException(e.getMessage());
		}
	}

	private Settings loadSettings(InputStream in) {
		try {
			return YamlUtils.load(in, Settings.class);
		} catch (YamlException e) {
			throw new IllegalSettingsException("Illegal settings!", e);
		}
	}

	private void validateSettings(Settings settings) {
		Set<String> projects = new HashSet<>();
		for (Project p : settings.getProjects()) {
			projects.add(p.getName());
			ValidationResult result = ValidationUtils.validate(p);
			if (result.isHasErrors()) {
				for (String error : result.getErrorMsg().values()) {
					throw new IllegalSettingsException(error);
				}
			}
		}
		if (settings.getProjects().size() != projects.size()) {
			throw new IllegalSettingsException("Project name must be unique!");
		}
	}

}
