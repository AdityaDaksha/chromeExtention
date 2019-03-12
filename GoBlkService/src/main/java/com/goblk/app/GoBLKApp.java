/**
 * 
 */
package com.goblk.app;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author adityapratap
 *
 */
@SpringBootApplication
public class GoBLKApp {
	
	private static final Logger LOOGER = Logger.getLogger(GoBLKApp.class);
	
	private static ConfigurableApplicationContext context;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOOGER.info("Starting goblk service...");
		try {
			context = new SpringApplicationBuilder(GoBLKApp.class).headless(false).run(args);
		} catch (Exception e) {
			LOOGER.error(e.getMessage(), e);
			if (context != null) {
				LOOGER.info("Closing context...");
				context.close();
			}
			LOOGER.info("Shutting down system...");
			System.exit(1);
		}
	}

}
