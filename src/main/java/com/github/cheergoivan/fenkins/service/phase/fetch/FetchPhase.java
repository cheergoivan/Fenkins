package com.github.cheergoivan.fenkins.service.phase.fetch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.phase.AbstractPhase;
import com.github.cheergoivan.fenkins.service.phase.Context;
import com.github.cheergoivan.fenkins.util.file.FileUtils;
import com.github.cheergoivan.fenkins.util.git.Credential;
import com.github.cheergoivan.fenkins.util.git.GitUtils;

public class FetchPhase extends AbstractPhase {

	public FetchPhase(Context context) {
		super(context);
	}

	@Override
	public void execute() {
		ByteArrayOutputStream fetchLog = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fetchLog));
		Project project = context.getProject();
		File workspace = context.getWorkspace().toFile();
		try {
			if (!workspace.exists() || FileUtils.isDirectoryEmpty(workspace)) {
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
			throwPhaseExecutionFailureException("Failed executing fetch phase!", e);
		}
	}

}
