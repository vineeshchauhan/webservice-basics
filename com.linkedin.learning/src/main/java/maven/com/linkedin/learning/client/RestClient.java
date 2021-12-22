package maven.com.linkedin.learning.client;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import maven.com.linkedin.lerning.model.SalutationRequestModel;

/**
 * JAX-RS HTTP Rest Client 
 * @author Vineesh.Chauhan
 *
 */
public class RestClient {

	public static void main(String[] args) throws Exception {
		restClient();
		restAsyncClient();
	}

	public static void restClient() {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/com.linkedin.learning/webapi/myresource")
							.request(MediaType.TEXT_PLAIN)
							.get();
		Logger.getAnonymousLogger().info(String.valueOf(response.getStatus()));
		Logger.getAnonymousLogger().info(response.readEntity(String.class));

		client = ClientBuilder.newClient();
		SalutationRequestModel salutationRequestModel = new SalutationRequestModel();
		salutationRequestModel.setSalutationRequest("Coder. ");
		Entity<SalutationRequestModel> ent = Entity.entity(salutationRequestModel, MediaType.APPLICATION_JSON);
		response = client.target("http://localhost:8080/com.linkedin.learning/webapi/myresource/guest/{guest}/salute")
		.resolveTemplate("guest", "Vineesh")
		.request(MediaType.APPLICATION_JSON)
		.post(ent);
		Logger.getAnonymousLogger().info("Get");
		Logger.getAnonymousLogger().info(String.valueOf(response.getStatus()));
		Logger.getAnonymousLogger().info(response.readEntity(String.class));
		
	}
	
	public static void restAsyncClient() throws Exception {
		Client client = ClientBuilder.newClient();
		client = ClientBuilder.newClient();
		SalutationRequestModel salutationRequestModel = new SalutationRequestModel();
		salutationRequestModel.setSalutationRequest("Coder. ");
		Entity<SalutationRequestModel> ent = Entity.entity(salutationRequestModel, MediaType.APPLICATION_JSON);
		Future<Response> futureResponse = client.
				target("http://localhost:8080/com.linkedin.learning/webapi/myresource/guest/{guest}/salutation")
		.resolveTemplate("guest", "Vineesh")
		.request(MediaType.APPLICATION_JSON)
		.async()
		.post(ent);
		Logger.getAnonymousLogger().info("Before Get");
		
		Response response = futureResponse.get();
		
		Logger.getAnonymousLogger().info("After Get");
		
		Logger.getAnonymousLogger().info(String.valueOf(response.getStatus()));
		Logger.getAnonymousLogger().info(response.readEntity(String.class));
		
	}
}
