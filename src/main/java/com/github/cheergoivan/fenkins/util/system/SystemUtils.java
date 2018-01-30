package com.github.cheergoivan.fenkins.util.system;

public class SystemUtils {

	private static String os = System.getProperty("os.name").toLowerCase();

	private SystemUtils() {
	}

	public static OS detectOS() {
		return os.contains("windows") ? OS.WINDOWS : OS.LINUX;
	}

}
