package com.github.cheergoivan.fenkins.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.service.task.ProjectExecutor;

@Configuration
@Order(100)
public class EnvironementInitialization {
	@Autowired
	private Settings settings;
	
	@Bean
	public ProjectExecutor initializeExecutors(){
		ProjectExecutor executors = new ProjectExecutor();
		settings.getProjects().forEach(project->{
			executors.register(project.getId());
		});
		return executors;
	}
}
