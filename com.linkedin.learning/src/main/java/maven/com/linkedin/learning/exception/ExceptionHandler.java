package maven.com.linkedin.learning.exception;

import java.util.Set;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {

		Set<ConstraintViolation<?>> voilations = exception.getConstraintViolations();

		StringBuilder str = new StringBuilder();
		voilations.forEach(s -> str.append("Wrong Value : "+s.getInvalidValue()+" Reason : "+s.getMessage()));
		
		Response response = Response.status(400,str.toString()).type(MediaType.APPLICATION_JSON).build();
		return response;
	}
}
