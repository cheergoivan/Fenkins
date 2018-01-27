package com.github.cheergoivan.fenkins.service.project;

import java.util.List;
import java.util.Optional;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public interface ProjectService {
	
	Optional<Project> findById(String id);
	
	List<Project> listProjects();
}
