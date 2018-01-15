package com.github.cheergoivan.fenkins.service.task;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public class Task{
	private Project targetProject;

	public Project getTargetProject() {
		return targetProject;
	}

	public void setTargetProject(Project targetProject) {
		this.targetProject = targetProject;
	}
}
