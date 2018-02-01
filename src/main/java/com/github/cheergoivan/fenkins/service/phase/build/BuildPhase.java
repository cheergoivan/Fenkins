package com.github.cheergoivan.fenkins.service.phase.build;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.cheergoivan.fenkins.service.phase.AbstractPhase;
import com.github.cheergoivan.fenkins.service.phase.Context;
import com.github.cheergoivan.fenkins.util.cmd.CmdExecUtils;
import com.github.cheergoivan.fenkins.util.cmd.InputStreamToLinesConsumer;

public class BuildPhase extends AbstractPhase {

	public BuildPhase(Context context) {
		super(context);
	}

	@Override
	public void execute() {
		List<String> buildResult = new LinkedList<>();
//		buildResult.add(System.getProperty("line.separator"));
		Map<String, String> environment = new HashMap<>();
		String workspace = context.getWorkspace().toString();
		environment.put("WORKSPACE", context.getWorkspace().toString());
		try {
			int exit = CmdExecUtils.executeCommand(new File(workspace), environment,
					context.getProject().getBuild().getCommand(), new InputStreamToLinesConsumer(buildResult::add));
			log(buildResult);
			if (exit != 0)
				throwPhaseExecutionFailureException("BUILD FAILURE! For more information, please check this log: "
						+ context.getLog().toFile().getName());
		} catch (IOException | InterruptedException e) {
			throwPhaseExecutionFailureException("BUILD FAILURE! For more information, please check this log: "
					+ context.getLog().toFile().getName(), e);
		}
	}

}
