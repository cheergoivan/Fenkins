package com.github.cheergoivan.fenkins.configuration;

import java.io.File;

/**
 * fenkins
 * --bin
 *   --fenkins.jar
 * --conf 
 *   --settings.yml
 * --projects
 *   --app1
 *   --app2
 * --logs
 *   --app1
 *   --app2
 *   --fenkins.log
 * startup.sh
 * shutdown.sh
 *   
 */
public class FenkinsStructure {
	private File fenkinsHome;
	private File dirBin;
	private File dirConf;
	private File dirProjects;
	private File dirLogs;
	private File fileSettings;
	private File fileProjectIdStorage;
	
	public FenkinsStructure(File fenkinsHome) {
		super();
		this.fenkinsHome = fenkinsHome;
		this.dirBin = new File(fenkinsHome, "bin");
		this.dirConf = new File(fenkinsHome, "conf");
		this.dirProjects = new File(fenkinsHome, "projects");
		this.dirLogs = new File(fenkinsHome, "logs");
		this.fileSettings = new File(dirConf, "settings.yml");
		this.fileProjectIdStorage = new File(dirProjects, ".fenkins");
	}

	public File getFenkinsHome() {
		return fenkinsHome;
	}

	public File getDirBin() {
		return dirBin;
	}

	public File getDirConf() {
		return dirConf;
	}

	public File getDirProjects() {
		return dirProjects;
	}

	public File getDirLogs() {
		return dirLogs;
	}

	public File getFileSettings() {
		return fileSettings;
	}

	public File getFileProjectIdStorage() {
		return fileProjectIdStorage;
	}
}