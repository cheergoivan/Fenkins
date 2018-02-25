package com.github.cheergoivan.fenkins.service.phase.build;

import com.github.cheergoivan.fenkins.service.phase.CmdExecutionPhase;
import com.github.cheergoivan.fenkins.service.phase.PhaseExecutionContext;

public class BuildPhase extends CmdExecutionPhase {

	public BuildPhase(PhaseExecutionContext context) {
		super(context);
	}

	@Override
	public String getCommand() {
		return context.getProject().getBuild().getCommand();
	}

	@Override
	public String getErrorMsg() {
		return "BUILD FAILURE! For more information, please check this log: " + context.getLog().toFile().getName();
	}

	@Override
	public boolean preExecute() {
		return context.getProject().getBuild() != null;
	}

}
