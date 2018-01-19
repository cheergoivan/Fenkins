package com.github.cheergoivan.fenkins.util.file;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	private FileUtils() {
	}

	public static void createFile(File file) throws IOException {
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			System.out.println(file.createNewFile());
		}
	}
}