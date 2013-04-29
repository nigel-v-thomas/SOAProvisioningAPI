package uk.me.nvt.soa.provisioning.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import uk.me.nvt.soa.provisioning.exception.ProvisioningAPIException;
import uk.me.nvt.soa.provisioning.helper.ModuleAvailabilityHelper;
import uk.me.nvt.soa.provisioning.model.ModuleAvailability;

@Component
@Scope(value = "singleton")
public class ModuleAvailabilityResource {
	
	public ModuleAvailabilityResource() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(ModuleAvailabilityResource.class);
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String moduleId;
	
	@Autowired
	private ModuleAvailabilityHelper moduleAvailabilityHelper;
	
	private ModuleAvailabilityResource(UriInfo uriInfo, Request request, String moduleId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.moduleId = moduleId;
	}
	
	/**
	 * Get a module availability details for a this module
	 * @param headers
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML}) 
	public ModuleAvailability getModuleAvailability(@Context HttpHeaders headers) {
		
		ModuleAvailability moduleAvailability = null;

		try {
			moduleAvailability = moduleAvailabilityHelper.getModuleAvailability(moduleId);
		}  catch (IllegalArgumentException e) {
			throw new ProvisioningAPIException(e.getMessage());
		}		
		return moduleAvailability;
	}
	
	/**
	 * Update module availability for this module
	 * expect xml of the form:
	 * <pre>
	 * {@code
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 *    <moduleAvailability>
     *      <minClassSize>10</minClassSize>
     *      <maxClassSize>20</maxClassSize>
     *    </moduleAvailability>
     * }
     * </pre>
	 * @param moduleAvailabilityElement
	 * @return
	 */
	@PUT  
	@Consumes(MediaType.APPLICATION_XML)  
    public Response updateModuleAvailability(JAXBElement<ModuleAvailability> moduleAvailabilityElement) {
		ModuleAvailability moduleAvailability = moduleAvailabilityElement.getValue();
		
		try {
			moduleAvailabilityHelper.updateModuleAvailability(moduleId, moduleAvailability);
		}  catch (IllegalArgumentException e) {
			throw new ProvisioningAPIException(e.getMessage());
		}
		
		return Response.ok().build(); 		
    }

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
		
	}  
}
