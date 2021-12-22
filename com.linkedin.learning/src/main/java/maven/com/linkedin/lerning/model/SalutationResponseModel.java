package maven.com.linkedin.lerning.model;

import java.io.Serializable;

public class SalutationResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salutationResponse;

	public String getSalutationResponse() {
		return salutationResponse;
	}

	public void setSalutationResponse(String salutationResponse) {
		this.salutationResponse = salutationResponse;
	}

}
