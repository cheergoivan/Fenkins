package com.github.cheergoivan.fenkins.service.phase.deploy;

import com.github.cheergoivan.fenkins.service.phase.CmdExecutionPhase;
import com.github.cheergoivan.fenkins.service.phase.PhaseExecutionContext;

public class DeployPhase extends CmdExecutionPhase {

	public DeployPhase(PhaseExecutionContext context) {
		super(context);
	}

	@Override
	public String getCommand() {
		return context.getProject().getDeploy().getCommand();
	}

	@Override
	public String getErrorMsg() {
		return "DEPLOY FAILURE! For more information, please check this log: " + context.getLog().toFile().getName();
	}

	@Override
	public boolean preExecute() {
		return context.getProject().getDeploy() != null;
	}

}
