package com.github.cheergoivan.fenkins.util.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SerializationUtils {

	private SerializationUtils() {
	}

	public static <T> void write(T object, OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(object);
	}

	@SuppressWarnings("unchecked")
	public static <T> T read(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(in);
		return (T) ois.readObject();
	}
}
