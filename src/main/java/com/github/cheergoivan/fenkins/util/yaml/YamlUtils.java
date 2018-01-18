package com.github.cheergoivan.fenkins.util.yaml;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {
	private YamlUtils() {
	}

	public static <T> T load(InputStream in, Class<T> type) throws YamlException{
		Yaml yaml = new Yaml();
		try {
			return yaml.loadAs(in, type);
		}catch(Exception e) {
			throw new YamlException("yaml load error!", e);
		}
		
	}
}
