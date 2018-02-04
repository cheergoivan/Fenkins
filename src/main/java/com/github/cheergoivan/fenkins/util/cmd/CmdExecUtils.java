package com.github.cheergoivan.fenkins.util.cmd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.github.cheergoivan.fenkins.util.system.SystemUtils;

public class CmdExecUtils {

	private CmdExecUtils() {
	}

	/**
	 * Returns the exit value of the subprocess represented by this Process
	 * object. If the specified waiting time elapses and the subprocess doesn't
	 * end then return -1.
	 * 
	 * @param directory
	 * @param environment
	 * @param command
	 * @param timeout
	 * @param inputStreamConsumer
	 * @return 0 indicates normal termination and -1 indicates timeout
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static int executeCommand(File directory, Map<String, String> environment, String command, Duration timeout,
			Consumer<InputStream> inputStreamConsumer) throws IOException, InterruptedException {
		Objects.requireNonNull(timeout);
		return executeCommandWithTimeout(directory, environment, command, timeout, inputStreamConsumer);
	}

	/**
	 * Returns the exit value of the subprocess represented by this command
	 * execution. By convention, the value 0 indicates normal termination.
	 * 
	 * @param directory
	 * @param environment
	 * @param command
	 * @param inputStreamConsumer
	 * @return 0 indicates normal termination
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static int executeCommand(File directory, Map<String, String> environment, String command,
			Consumer<InputStream> inputStreamConsumer) throws IOException, InterruptedException {
		return executeCommandWithTimeout(directory, environment, command, null, inputStreamConsumer);
	}

	private static int executeCommandWithTimeout(File directory, Map<String, String> environment, String command,
			Duration timeout, Consumer<InputStream> inputStreamConsumer) throws IOException, InterruptedException {
		ProcessBuilder pb = null;
		switch (SystemUtils.detectOS()) {
		case WINDOWS:
			pb = new ProcessBuilder("cmd.exe", "/c", command);
		case LINUX:
			pb = new ProcessBuilder("/bin/sh", "-c", command);
		}
		pb.directory(directory);
		Map<String, String> pbEnv = pb.environment();
		Optional.ofNullable(environment).ifPresent(en -> {
			en.forEach((k, v) -> pbEnv.put(k, v));
		});
		pb.redirectErrorStream(true);
		Process p = pb.start();
		inputStreamConsumer.accept(p.getInputStream());
		if (timeout == null) {
			p.waitFor();
		} else {
			boolean terminated = p.waitFor(timeout.getDuration(), timeout.getTimeUnit());
			if (!terminated)
				return -1;
		}
		return p.exitValue();
	}
}
