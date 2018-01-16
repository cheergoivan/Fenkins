package com.github.cheergoivan.fenkins.util.serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class HeaderLessObjectOutputStream extends ObjectOutputStream {

	protected HeaderLessObjectOutputStream() throws SecurityException, IOException {
		super();
	}
	
	public HeaderLessObjectOutputStream(OutputStream out) throws IOException, SecurityException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		return;
	}

}
