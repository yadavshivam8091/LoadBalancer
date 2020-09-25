package io.ys.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import io.ys.application.Application;
import io.ys.resources.Resources;

@Service("loadbalancer.config")
@Configuration
public class ApplicationConfiguration{
	private static JSONObject config;
	private List<String> missingConfigs = new ArrayList<String>();

	static{
		try {
			config = Resources.getFileObject("config.json");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Autowired
	public ApplicationConfiguration(ServerConfiguration serverConfig , TargetConfiguration targetConfig) {
		if (config.has("server")) {
			serverConfig.initialize(config.getJSONObject("server"));
		} else {
			missingConfigs.add("server");
		}
		if (config.has("host")) {
			targetConfig.initalize(config.getJSONObject("host"));
		} else {
			missingConfigs.add("host");
		}
	}
	public List<String> getMissingResources() {
		return missingConfigs;
	}

	@Bean
	ServletWebServerFactory servletWebServerFactory() {
		return new JettyServletWebServerFactory();
	}
	
	@Bean("logger")
	Logger logger() {
		org.slf4j.Logger loadBalancerLogger = LoggerFactory.getLogger(Application.class);
		return loadBalancerLogger;
	}

}
