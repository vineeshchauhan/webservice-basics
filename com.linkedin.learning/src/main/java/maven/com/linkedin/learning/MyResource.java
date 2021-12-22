package maven.com.linkedin.learning;

import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import maven.com.linkedin.lerning.model.SalutationRequestModel;
import maven.com.linkedin.lerning.model.SalutationResponseModel;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 * 
	 * URL : http://localhost:8080/com.linkedin.learning/webapi/myresource
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	/**
	 * Method handling HTTP GET requests with query parameter. The returned object will be sent to the
	 * client as "text/plain" media type.
	 * 
	 * URL : http://localhost:8080/com.linkedin.learning/webapi/myresource/guest/Vineesh/salute
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@Path("/guest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getItWithParam(@NotBlank @QueryParam("guestName") String guestName) {
		return "Got it! Mr." + guestName;
	}

	/**
	 * Method handling HTTP GET requests with query parameter. The returned object will be sent to the
	 * client as "text/plain" media type. Sleep was added to test async rest client.
	 * 
	 * URL : http://localhost:8080/com.linkedin.learning/webapi/myresource/guest/Vineesh/salute
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Path("/guest/{guest}/salutation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salutation(SalutationRequestModel model, @PathParam("guest") String guestName) {
		try {
			Thread.sleep(8000);
		}catch(Exception exp) {
			
		}
		SalutationResponseModel m = new SalutationResponseModel();
		m.setSalutationResponse("Welcome, " + model.getSalutationRequest() + guestName);
		Response responseWrapper = Response.ok(m).build();
		return responseWrapper;

	}
	
	/**
	 * Method handling HTTP POST requests. 
	 * Accepts JSON and one query parameter.
	 * The returned object will be sent to the client as "application/json" media type.
	 * URL : http://localhost:8080/com.linkedin.learning/webapi/myresource/guest/Vineesh/salute
	 * JSON : { "salutationRequest" : "Coder. " }
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Path("/guest/{guest}/salute")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salute(SalutationRequestModel model, @PathParam("guest") String guestName) {
		SalutationResponseModel m = new SalutationResponseModel();
		m.setSalutationResponse("Welcome, " + model.getSalutationRequest() + guestName);
		Response responseWrapper = Response.ok(m).build();
		return responseWrapper;

	}
	


	/**
	 * Method handling HTTP POST requests. 
	 * Accepts argumenet from HTML form.
	 * The returned object will be sent to the
	 * client as "application/json" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Path("/guest/salute")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saluteWithForm(@FormParam("salutation") String salutation, @FormParam("guest") String guest) {
		SalutationResponseModel m = new SalutationResponseModel();
		m.setSalutationResponse(salutation + " " + guest);
		Response responseWrapper = Response.ok(m).build();
		return responseWrapper;

	}
}
