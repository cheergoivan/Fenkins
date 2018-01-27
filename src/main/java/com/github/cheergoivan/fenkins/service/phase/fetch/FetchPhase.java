package com.github.cheergoivan.fenkins.service.phase.fetch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cheergoivan.fenkins.configuration.FenkinsProperties;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.phase.AbstractPhase;
import com.github.cheergoivan.fenkins.service.phase.Context;
import com.github.cheergoivan.fenkins.service.phase.PhaseExecutionFailureException;
import com.github.cheergoivan.fenkins.util.file.FileUtils;
import com.github.cheergoivan.fenkins.util.git.Credential;
import com.github.cheergoivan.fenkins.util.git.GitUtils;

public class FetchPhase extends AbstractPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FetchPhase.class);

	public FetchPhase(Context context) {
		super(context);
	}

	@Override
	public void execute() {
		ByteArrayOutputStream fetchLog = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fetchLog));
		Project project = context.getProject();
		File workspace = new File(FenkinsProperties.DIR_PROJECTS, project.getName());
		try {
			if (!workspace.exists()) {
				FileUtils.createDirectoryIfNotExists(workspace);
				GitUtils.clone(workspace, project.getGit().getUrl(),
						new Credential(project.getGit().getCredential().getUsername(),
								project.getGit().getCredential().getPassword()),
						pw);
			} else {
				GitUtils.pull(workspace, new Credential(project.getGit().getCredential().getUsername(),
						project.getGit().getCredential().getPassword()), pw);
			}
			Files.write(context.getLog(), Arrays.asList(new String(fetchLog.toByteArray())), StandardOpenOption.APPEND);
		} catch (Exception e) {
			LOGGER.error("Failed executing fetch phase!", e);
			throw new PhaseExecutionFailureException("Failed executing fetch phase!", e);
		}
	}

}
