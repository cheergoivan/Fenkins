package com.github.cheergoivan.fenkins.service.project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private Settings settings;

	@Override
	public Optional<Project> findById(String id) {
		return settings.getProjects().stream().filter(p -> p.getId().equals(id)).findFirst();
	}

}
