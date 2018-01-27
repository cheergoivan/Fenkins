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
public class FenkinsProperties {
	public static final File DIR_BIN = new File(System.getProperty("user.dir"));
	
	public static final File FENKINS_HOME = DIR_BIN.getParentFile();
	
	public static final File DIR_CONF = new File(FENKINS_HOME, "conf");
	
	public static final File SETTINGS_FILE = new File(DIR_CONF, "settings.yml");
	
	public static final File DIR_PROJECTS = new File(FENKINS_HOME, "projects");
	
	public static final File DIR_LOGS = new File(FENKINS_HOME, "logs");
	
	public static final File PROJECT_ID_STOREAGE = new File(FENKINS_HOME+"/.fenkins");

}