package com.github.cheergoivan.fenkins.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;

@Service
public class ProjectExecutorServiceImpl implements ProjectExecutorService{

	@Autowired
	private ProjectExecutor executor;
	
	@Override
	public void dispatch(Project project) {
		executor.execute(project.getId(), new Task(project));
	}
}
