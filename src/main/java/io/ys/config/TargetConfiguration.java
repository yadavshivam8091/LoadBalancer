package io.ys.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service("loadbalancer.hostconfig")
@Configuration
public class TargetConfiguration {

	private static JSONArray hostArray = null;
	private static String algo_type = "Round-robin";

	public void initalize(JSONObject config) {
		if (config.has("host")) {
			hostArray = config.getJSONArray("host");
		}
		if (config.has("algorithm-type")) {
			algo_type = config.getString("algorithm-type");
		}
	}

	public JSONArray getHost() {
		return hostArray;
//		return new JSONArray("[{'IP':'localhost','PORT':3000},{'IP':'localhost','PORT':3100}]");
	}

	public String getLoadBalanceingAlgoritm() {
		return algo_type;
	}
}
