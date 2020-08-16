package io.ys.scheduling;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoundRobin implements LoadBalance {

	private static Integer position = 0;
	private JSONArray servers;
	private JSONObject servertoUse = null;

	public RoundRobin(JSONArray servers) {
		this.servers = servers;
		findServer();
	}

	public JSONObject getServer() {
		return this.servertoUse;
	}

	private void findServer() {
		System.out.println("position is " + position);
		int serverSize = this.servers.length();
		synchronized (position) {
			if (position > serverSize - 1) {
				position = 0;
			}
			this.servertoUse = getServer(position);
			position++;
		}
	}

	private JSONObject getServer(int position) {
		JSONObject server = (JSONObject) this.servers.get(position);
		return server;
	}

}
