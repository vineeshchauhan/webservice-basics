package maven.com.linkedin.learning.client;

import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * JAX-RS HTTP Reactive Rest Client 
 * @author Vineesh.Chauhan
 *
 */
public class ReactiveRestClient {

	public static void main(String[] args) {
		rxClient();
	}

	private static void rxClient() {

		Logger logger = Logger.getLogger("RX JAX-RS Client API");
		logger.log(Level.SEVERE, "Reactive Client Chaining");

		Client clientfetchName = ClientBuilder.newClient();
		Client clientaddCityName = ClientBuilder.newClient();
		Client clientremoveCityName = ClientBuilder.newClient();

		String fetchName = "http://localhost:8080/com.linkedin.learning/webapi/city/names";
		String addCityName = "http://localhost:8080/com.linkedin.learning/webapi/city/addCityName";
		String removeCityName = "http://localhost:8080/com.linkedin.learning/webapi/city/removeCityName";

		CompletionStage<String> futureList = getListOfCities(clientfetchName, fetchName);

		futureList.thenAcceptAsync((listOfCities) -> {
			CompletionStage<Response> addedFutureList = clientaddCityName.target(addCityName)
					.queryParam("cityName", "Calcutta").request(MediaType.TEXT_PLAIN).rx().post(null)
					.exceptionally((exception) -> {
						logger.log(Level.SEVERE,"Exception : " + exception);
						return null;
					});
			/*
			 * addedFutureList.whenComplete((list, e) -> {
			 * System.out.println(" clientaddCityName : " + list.toString()); });
			 */
			logger.log(Level.SEVERE, "Add Calcutta");
			getListOfCities(clientfetchName, fetchName);
		});
		futureList.thenAcceptAsync((listOfCities) -> {
			CompletionStage<Response> addedFutureList = clientaddCityName.target(addCityName)
					.queryParam("cityName", "Cuttak").request(MediaType.TEXT_PLAIN).rx().post(null)
					.exceptionally((exception) -> {
						logger.log(Level.SEVERE,"Exception : " + exception);
						return null;
					});
			/*
			 * addedFutureList.whenComplete((list, e) -> {
			 * System.out.println("clientremoveCityName : " + list.toString()); });
			 */
			logger.log(Level.SEVERE, "Add Cuttak");
			
		});
		getListOfCities(clientfetchName, fetchName);
		
	}

	private static CompletionStage<String> getListOfCities(Client fetchName, String clientaddCityName) {
		CompletionStage<String> a = fetchName.target(clientaddCityName).request(MediaType.TEXT_PLAIN).rx()
				.get(new GenericType<String>() {
				}).exceptionally((exception) -> {
					// logger.warning("Exception"+exception);
					return null;
				});

		a.whenComplete((list, e) -> {
			System.out.println("fetchName : "+list.toString());
		});

		return a;

	}

}
