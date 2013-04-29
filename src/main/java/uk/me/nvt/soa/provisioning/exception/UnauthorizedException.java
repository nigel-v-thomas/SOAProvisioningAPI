package uk.me.nvt.soa.provisioning.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class UnauthorizedException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message, String realm)
	{
		super(Response.status(Status.UNAUTHORIZED).entity(message).build());
	}

}
