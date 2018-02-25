package com.github.cheergoivan.fenkins.service.phase.fetch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.phase.AbstractPhase;
import com.github.cheergoivan.fenkins.service.phase.PhaseExecutionContext;
import com.github.cheergoivan.fenkins.util.file.FileUtils;
import com.github.cheergoivan.fenkins.util.git.Credential;
import com.github.cheergoivan.fenkins.util.git.GitUtils;

public class FetchPhase extends AbstractPhase {

	public FetchPhase(PhaseExecutionContext context) {
		super(context);
	}

	@Override
	public boolean preExecute() {
		return context.getProject().getGit() != null;
	}

	@Override
	public void internalExecute() {
		ByteArrayOutputStream fetchLog = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fetchLog));
		Project project = context.getProject();
		File workspace = context.getWorkspace().toFile();
		try {
			if (FileUtils.createDirectoryIfNotExists(workspace) 
					|| FileUtils.isDirectoryEmpty(workspace)) {
				GitUtils.clone(workspace, project.getGit().getUrl(),
						new Credential(project.getGit().getCredential().getUsername(),
								project.getGit().getCredential().getPassword()), pw);
			} else {
				GitUtils.pull(workspace, new Credential(project.getGit().getCredential().getUsername(),
						project.getGit().getCredential().getPassword()), pw);
			}
			log(Arrays.asList(new String(fetchLog.toByteArray())));
		} catch (Exception e) {
			throwPhaseExecutionException("Failed executing fetch phase!", e);
		}
	}

}
