package maven.com.linkedin.learning.context;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("webapi")
public class ApplicationContext extends ResourceConfig{
	
	public ApplicationContext() {
		packages("maven.com.linkedin.learning");
		packages("maven.com.linkedin.learning.exception");
		//set property for jax-rs runtime
		// helps to send a correct error message
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}

}
