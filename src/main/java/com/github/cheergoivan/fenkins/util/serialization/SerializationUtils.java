package com.github.cheergoivan.fenkins.util.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtils {

	private SerializationUtils() {
	}

	public static <T> void write(T object, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file, true);
		ObjectOutputStream oos = null;
		if (!file.exists() || file.length() == 0)
			oos = new ObjectOutputStream(fos);
		else
			oos = new HeaderLessObjectOutputStream(fos);
		oos.writeObject(object);
	}

	@SuppressWarnings("unchecked")
	public static <T> T read(File file) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (T) ois.readObject();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> readAll(File file) throws IOException, ClassNotFoundException {
		List<T> objects = new ArrayList<>();
		try (FileInputStream in = new FileInputStream(file); 
				ObjectInputStream ois = new ObjectInputStream(in)) {
			while (in.available() > 0) {
				objects.add((T) ois.readObject());
			}
		}
		return objects;
	}
}
