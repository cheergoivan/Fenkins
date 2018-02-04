package com.github.cheergoivan.fenkins.service.phase.deploy;

import com.github.cheergoivan.fenkins.service.phase.CmdExecPhase;
import com.github.cheergoivan.fenkins.service.phase.Context;

public class DeployPhase extends CmdExecPhase {

	public DeployPhase(Context context) {
		super(context);
	}

	@Override
	public void execute() {
		super.execute();
	}

	@Override
	public String getCommand() {
		return context.getProject().getDeploy().getCommand();
	}

	@Override
	public String getErrorMsg() {
		return "DEPLOY FAILURE! For more information, please check this log: "
				+ context.getLog().toFile().getName();
	}

}
