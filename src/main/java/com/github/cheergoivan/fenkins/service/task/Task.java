package com.github.cheergoivan.fenkins.service.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cheergoivan.fenkins.configuration.FenkinsStructure;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.phase.Context;
import com.github.cheergoivan.fenkins.service.phase.Phase;
import com.github.cheergoivan.fenkins.service.phase.build.BuildPhase;
import com.github.cheergoivan.fenkins.service.phase.deploy.DeployPhase;
import com.github.cheergoivan.fenkins.service.phase.fetch.FetchPhase;
import com.github.cheergoivan.fenkins.util.file.FileUtils;
import com.github.cheergoivan.fenkins.util.spring.SpringContextUtils;

public class Task implements Runnable {
	private Project project;
	private FenkinsStructure fenkinsStructure;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
	
	public Task(Project project) {
		this.project = project;
		this.fenkinsStructure = SpringContextUtils.getBean(FenkinsStructure.class);
	}

	@Override
	public void run() {
		Context context = new Context(prepareWorkspace(), project, prepareLog());
		Phase phase = new FetchPhase(context).next(new BuildPhase(context)).next(new DeployPhase(context));
		phase.execute();
	}

	private Path prepareWorkspace(){
		File workspace = new File(fenkinsStructure.getDirProjects(), project.getName());
		return Paths.get(workspace.getAbsolutePath());
	}
	
	private Path prepareLog() {
		Path logDir = Paths.get(fenkinsStructure.getDirLogs().getAbsolutePath() + "/" + project.getName());
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
