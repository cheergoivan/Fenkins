package com.github.cheergoivan.fenkins.util.yaml;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {
	private YamlUtils() {
	}

	public static <T> T load(InputStream in, Class<T> type) {
		Yaml yaml = new Yaml();
		return yaml.loadAs(in, type);
	}
}
