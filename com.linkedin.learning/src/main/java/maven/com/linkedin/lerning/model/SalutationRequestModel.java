package maven.com.linkedin.lerning.model;

import java.io.Serializable;

public class SalutationRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salutationRequest;

	public String getSalutationRequest() {
		return salutationRequest;
	}

	public void setSalutationRequest(String salutationRequest) {
		this.salutationRequest = salutationRequest;
	}

}
