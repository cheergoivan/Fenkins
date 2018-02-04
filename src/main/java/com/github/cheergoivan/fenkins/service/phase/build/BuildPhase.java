package com.github.cheergoivan.fenkins.service.phase.build;

import com.github.cheergoivan.fenkins.service.phase.CmdExecPhase;
import com.github.cheergoivan.fenkins.service.phase.Context;

public class BuildPhase extends CmdExecPhase {

	public BuildPhase(Context context) {
		super(context);
	}

	@Override
	public void execute() {
		super.execute();
	}

	@Override
	public String getCommand() {
		return context.getProject().getBuild().getCommand();
	}

	@Override
	public String getErrorMsg() {
		return "BUILD FAILURE! For more information, please check this log: "
				+ context.getLog().toFile().getName();
	}
}
