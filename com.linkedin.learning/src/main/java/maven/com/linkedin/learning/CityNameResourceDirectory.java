package maven.com.linkedin.learning;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/city")
public class CityNameResourceDirectory {
	
	private static List<String> cityNames = new ArrayList<>();
	
	static{
		cityNames.add("Delhi");
		cityNames.add("Jaipur");
		cityNames.add("Chandigarh");
	}

	@Path("/names")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCityNames() throws InterruptedException {
		return cityNames.toString();
	}
	
	@Path("/addCityName")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String addCityNames(@QueryParam("cityName") String cityName) {
		cityNames.add(cityName);
		return cityNames.toString();
	}
	
	@Path("/removeCityName")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String removeCityNames(@QueryParam("cityName") String cityName) {
		cityNames.remove(cityName);
		return cityNames.toString();
	}
	
	@Path("/clearCityList")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String emptyList() {
		cityNames.clear();
		return "City Name is reset";
	}
	
}
