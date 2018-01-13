package com.github.cheergoivan.fenkins.util.git;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitUtils {

	/**
	 * Clone the specific repository into a given directory.
	 * 
	 * @param directory
	 * @param remoteRepository
	 * @param credential
	 * @param out
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public static void clone(String directory, String remoteRepository, Credential credential, Writer out)
			throws GitAPIException, IOException {
		CloneCommand cloneCommand = Git.cloneRepository().setURI(remoteRepository);
		out.write("Clone from " + remoteRepository + System.getProperty("line.separator"));
		cloneCommand.setCredentialsProvider(
				new UsernamePasswordCredentialsProvider(credential.getUsername(), credential.getPassword()));
		cloneCommand.setProgressMonitor(new TextProgressMonitor(out));
		File localRepository = new File(directory);
		if (!localRepository.exists() || !localRepository.isDirectory())
			throw new FileNotFoundException("Direcory " + directory + " doesn't exist!");
		cloneCommand.setDirectory(localRepository).call();
	}

	/**
	 * Run 'git pull' command.
	 * 
	 * @param localRepository
	 * @param credential
	 * @param out
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public static void pull(File localRepository, Credential credential, Writer out)
			throws IOException, GitAPIException {
		Git git = Git.open(localRepository);
		out.write("Pull from " + getOriginRemoteURL(git) + System.getProperty("line.separator"));
		PullCommand pullCommand = git.pull();
		pullCommand.setCredentialsProvider(
				new UsernamePasswordCredentialsProvider(credential.getUsername(), credential.getPassword()));
		pullCommand.setProgressMonitor(new TextProgressMonitor(out));
		pullCommand.call();
	}

	private static String getOriginRemoteURL(Git git) {
		return git.getRepository().getConfig().getString("remote", "origin", "url");
	}
}
