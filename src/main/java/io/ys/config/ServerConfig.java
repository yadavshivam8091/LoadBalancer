package io.ys.config;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("loadbalancer.serverconfig")
public class ServerConfig {
	
	private static int port = 9091;
	private static String host = "loaclhost";
	private static boolean ssl = false;
	public ServerConfig() {
		
	}
	
	public static void initialize(JSONObject serverConfig) {
		if(serverConfig.has("port")) {
			port = serverConfig.getInt("port");
		}
		if(serverConfig.has("host")) {
			host  = serverConfig.getString("host");
		}
		if(serverConfig.has("ssl")) {
			ssl  = serverConfig.getBoolean("ssl");
		}
	}
	
	public static String getHost() {
		return host;
	}
	
	public static int getPort() {
		return port;
	} 
	
	public static  boolean getssl() {
		return ssl;
	} 
}
