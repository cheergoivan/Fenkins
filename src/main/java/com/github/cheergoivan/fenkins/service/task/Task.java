package com.github.cheergoivan.fenkins.service.task;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cheergoivan.fenkins.configuration.FenkinsProperties;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.phase.Context;
import com.github.cheergoivan.fenkins.service.phase.Phase;
import com.github.cheergoivan.fenkins.service.phase.build.BuildPhase;
import com.github.cheergoivan.fenkins.service.phase.deploy.DeployPhase;
import com.github.cheergoivan.fenkins.service.phase.fetch.FetchPhase;
import com.github.cheergoivan.fenkins.util.file.FileUtils;

public class Task implements Runnable {
	private Project project;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
	
	public Task(Project project) {
		this.project = project;
	}

	@Override
	public void run() {
		Context context = new Context(prepareLog(), project);
		Phase phase = new FetchPhase(context).next(new BuildPhase(context)).next(new DeployPhase(context));
		phase.execute();
	}

	private Path prepareLog() {
		Path logDir = Paths.get(FenkinsProperties.DIR_LOGS.getAbsolutePath() + "/" + project.getName());
		Path log = logDir
				.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH.mm.ss")) + ".log");
		try {
			FileUtils.createFileIfNotExists(log.toFile());
		} catch (IOException e) {
			LOGGER.error("Fail to create log file: " + log.toString(), e);
			throw new RuntimeException("Fail to create log file: " + log.toString());
		}
		return log;
	}

}
