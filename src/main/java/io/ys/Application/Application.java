package io.ys.Application;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import io.ys.Resources.Resources;
import io.ys.config.Appconfig;
import io.ys.config.ServerConfig;

@SpringBootApplication
@ComponentScan("io.ys")
public class Application implements ApplicationContextAware, WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
	public static void main(String[] args) {
		try {

			Application.initializeConfigs();
			SpringApplication.run(Application.class, args);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void initializeConfigs() throws Exception {
		try {
			Appconfig.initialzeConfig();
			if (!Appconfig.getMissingResources().isEmpty()) {

				System.out
						.println(String.format("%s config/s are missing", Appconfig.getMissingResources().toString()));
				System.exit(0);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Bean
	ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
	
	public void customize(ConfigurableWebServerFactory factory) {
		factory.setPort(ServerConfig.getPort());
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		Resources.setApplicationContext(context);
	}
}
