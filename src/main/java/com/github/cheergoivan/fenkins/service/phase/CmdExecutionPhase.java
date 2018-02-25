package com.github.cheergoivan.fenkins.service.phase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.cheergoivan.fenkins.util.cmd.CmdExecUtils;
import com.github.cheergoivan.fenkins.util.cmd.InputStreamToLinesConsumer;

public abstract class CmdExecutionPhase extends AbstractPhase{
	
	public CmdExecutionPhase(PhaseExecutionContext context) {
		super(context);
	}

	@Override
	public void internalExecute() {
		List<String> output = new LinkedList<>();
		Map<String, String> environment = new HashMap<>();
		String workspace = context.getWorkspace().toString();
		environment.put("WORKSPACE", context.getWorkspace().toString());
		try {
			int exit = CmdExecUtils.executeCommand(new File(workspace), environment,
					getCommand(), new InputStreamToLinesConsumer(output::add));
			log(output);
			if (exit != 0)
				throwPhaseExecutionException(getErrorMsg());
		} catch (IOException | InterruptedException e) {
			throwPhaseExecutionException(getErrorMsg(), e);
		}
	}
	
	public abstract String getCommand();
	
	public abstract String getErrorMsg();

}
