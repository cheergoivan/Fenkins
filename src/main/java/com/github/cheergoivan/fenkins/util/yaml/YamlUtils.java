package com.github.cheergoivan.fenkins.util.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {
	private YamlUtils() {
	}

	public static <T> T loadFromFile(File file, Class<T> type) {
		Yaml yaml = new Yaml();
		try {
			return yaml.loadAs(new FileInputStream(file), type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
