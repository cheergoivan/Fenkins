package com.github.cheergoivan.fenkins.service.task;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public interface ProjectExecutorService {
	void dispatch(Project project);
}
