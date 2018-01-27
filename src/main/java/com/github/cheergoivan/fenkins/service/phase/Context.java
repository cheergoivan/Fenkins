package com.github.cheergoivan.fenkins.service.phase;

import java.nio.file.Path;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public class Context {
	private Path log;
	private Project project;
	
	public Context(Path log, Project project) {
		super();
		this.log = log;
		this.project = project;
	}

	public Path getLog() {
		return log;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setLog(Path log) {
		this.log = log;
	}
}
