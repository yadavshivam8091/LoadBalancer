package io.ys.scheduling;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class randomRoundRobin implements LoadBalance{

	private JSONArray servers;
	private Random random = new Random();
	private int lowerBound = 1;
	
	public randomRoundRobin(JSONArray servers) {
		this.servers=servers;
	}
	
	@Override
	public JSONObject getServer() {
		int position = random.nextInt(this.servers.length() - lowerBound) + lowerBound;
		return getServer(position);
	}

	public JSONObject getServer(int position) {
		JSONObject server = (JSONObject) this.servers.get(position);
		return server;
	}
}
