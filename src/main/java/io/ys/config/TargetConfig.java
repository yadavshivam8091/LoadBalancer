package io.ys.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.INITIALIZE;
import org.springframework.stereotype.Service;

@Service("loadbalancer.hostconfig")
public class TargetConfig {

	public static void initalize(JSONObject config) {
		
	}

	public JSONArray getHost() {
		// TODO Auto-generated method stub
		return new JSONArray("[{'IP':'localhost','PORT':3000},{'IP':'localhost','PORT':3100}]");
	}

	public String getLoadBalanceingAlgoritm() {
		// TODO Auto-generated method stub
		return null;
	}
}
