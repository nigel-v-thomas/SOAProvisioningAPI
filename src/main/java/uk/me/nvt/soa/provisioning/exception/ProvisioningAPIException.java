package uk.me.nvt.soa.provisioning.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Intended to represent exceptions caught while talking with google API
 */
public class ProvisioningAPIException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	
	public ProvisioningAPIException(String message) {
		this(null, message);
	}
	
	public ProvisioningAPIException(Throwable e, String message) {
		super(e, Response.status(Status.BAD_REQUEST).entity(message).build());
	}

}
