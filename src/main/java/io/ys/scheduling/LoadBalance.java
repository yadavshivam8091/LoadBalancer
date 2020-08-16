package io.ys.scheduling;

import org.json.JSONObject;

public interface LoadBalance {
	//get random server 
	 JSONObject getServer();
	 
}
