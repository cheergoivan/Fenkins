package com.github.cheergoivan.fenkins.service.phase;

import java.nio.file.Path;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public class PhaseExecutionContext {
	private Path workspace;
	private Project project;
	private Path log;
	
	public PhaseExecutionContext(Path workspace, Project project, Path log) {
		super();
		this.workspace = workspace;
		this.project = project;
		this.log = log;
	}

	public Path getWorkspace() {
		return workspace;
	}

	public Project getProject() {
		return project;
	}

	public Path getLog() {
		return log;
	}
}
