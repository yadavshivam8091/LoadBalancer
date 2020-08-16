package io.ys.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.ys.Resources.Resources;



@Service("loadbalancer.config")
public class Appconfig {
	private static JSONObject config;
	private static List<String> missingConfigs = new ArrayList<String>();
	
	public static void initialzeConfig() throws Exception {
		
		try {
			config = Resources.getFileObject("config.json");
			if(config.has("server")) {
				ServerConfig.initialize(config.getJSONObject("server"));
			}
			else {
				missingConfigs.add("server");
			}
			if(config.has("host")) {
				TargetConfig.initalize(config.getJSONObject("host"));
			}
			else {
				missingConfigs.add("host");
			}
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List<String> getMissingResources() {
		return missingConfigs;
	}
}
