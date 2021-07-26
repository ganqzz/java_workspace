package com.pluralsight;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Configuration {

	public static Configuration loadConfiguration(String fileName) throws IOException {
		Path path = FileSystems.getDefault().getPath(fileName);
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		Configuration config = mapper.readValue(contents, Configuration.class);
		return config;
	}

	private String factoryType;
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}
}
