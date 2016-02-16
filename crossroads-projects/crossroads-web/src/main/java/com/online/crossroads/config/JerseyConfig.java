package com.online.crossroads.config;

import com.online.crossroads.controllers.TestController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by lenovo on 02-02-2016.
 */
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(MultiPartFeature.class);

		// Controllers
		register(TestController.class);

		// Exception Mapper
		packages("com.online.crossroads");
	}
}