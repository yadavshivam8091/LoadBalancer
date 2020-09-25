package io.ys.config;

import org.json.JSONObject;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service("loadbalancer.serverconfig")
@Configuration
public class ServerConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{
	
	private static int port = 9091;
	private static String host = "loaclhost";
	private static boolean ssl = false;
	private ConfigurableWebServerFactory factory;
	public ServerConfiguration() {
		
	}
	
	public void initialize(JSONObject serverConfig) {
		if(serverConfig.has("port")) {
			factory.setPort(serverConfig.getInt("port"));
		}
		if(serverConfig.has("host")) {
			host  = serverConfig.getString("host");
		}
		if(serverConfig.has("ssl")) {
			ssl  = serverConfig.getBoolean("ssl");
		}
	}
	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		this.factory = factory;
	} 
}
