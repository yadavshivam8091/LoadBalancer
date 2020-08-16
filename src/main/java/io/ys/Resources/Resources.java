package io.ys.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

public class Resources {

	private static ApplicationContext context;

	public static JSONObject getFileObject(String fileName) throws IOException {
		String fileAsString = new String(Files.readAllBytes(Paths.get(Constants.BASEPATH.concat(fileName))));
		return new JSONObject(fileAsString);
	}

	public static void setApplicationContext(ApplicationContext context) {
		Resources.context = context;
	}

	public static ApplicationContext getApplicationContext() {
		return Resources.context;
	}
}
