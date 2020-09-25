package io.ys.application;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import io.ys.config.ApplicationConfiguration;

@SpringBootApplication
@ComponentScan("io.ys")
public class Application{
	public static void main(String[] args) {
		Logger logger = null;
		try {
			ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
			ApplicationConfiguration config = (ApplicationConfiguration) context.getBean("loadbalancer.config");
			logger = (Logger) context.getBean("logger");
			if (config.getMissingResources().isEmpty()) {
				logger.info("Application Started Successfully.");
			}
			else {
				logger.info(String.format("%s config/s are missing", config.getMissingResources().toString()));
				System.exit(0);
			}

		} catch (Exception e) {
			logger.error("Error encountered while starting spring boot application");
			logger.debug(" Now closing application......");
			System.exit(0);
		}
	}
}
