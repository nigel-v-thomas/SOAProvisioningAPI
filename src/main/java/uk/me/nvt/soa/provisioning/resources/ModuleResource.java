package uk.me.nvt.soa.provisioning.resources;

import com.sun.jersey.api.core.ResourceContext;
import org.apache.commons.lang3.Validate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import uk.me.nvt.soa.provisioning.exception.ProvisioningAPIException;
import uk.me.nvt.soa.provisioning.helper.ProvisioningApiHelper;
import uk.me.nvt.soa.provisioning.model.Module;
import uk.me.nvt.soa.provisioning.model.Modules;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

@Component
@Scope(value = "singleton")
@Path("/modules")
public class ModuleResource {

	public ModuleResource() {
		super();
	}

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ModuleResource.class);
	
	@Autowired
	private ProvisioningApiHelper apiHelper;

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
    @Context
    private ResourceContext resourceContext;
	
	private ModuleResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
	}
	
	
	/**
	 * Get module by moduleId
	 * available in application/json or application/xml
	 * @param moduleId expecting a KIS compliant module id..
	 * @return Returns a serialization of Module
	 */
	@GET
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	@Path("{module}")
	public Module getModule( @PathParam("module") String moduleId ) {
		
		Module module = null;

		try{
			Validate.notNull( this.apiHelper );
			module = this.apiHelper.getModule( moduleId );
		}
        catch ( IllegalArgumentException e ) {
			throw new ProvisioningAPIException( e.getMessage() );
		}
		return module;
	}
	
	/**
	 * Update a particular module
	 * consume application/xml
	 * @param moduleElement , see code sample for create
	 * @return Expect a 200 ok
	 */
	@PUT
	@Consumes( MediaType.APPLICATION_XML )
	public Response updateModule( JAXBElement<Module> moduleElement ) {

        Module module = moduleElement.getValue();

		try {
			apiHelper.updateModule( module );
		}
        catch ( IllegalArgumentException e ) {
			throw new ProvisioningAPIException( e.getMessage() );
		}
		return Response.ok().build();
	}

	/**
	 * Delete a module by moduleId, returns 200 on success
	 * @param moduleId expecting a KIS compliant module id..
	 * @return Expect a 200 ok
	 */
	@DELETE
	@Path("{module}")
	public Response deleteModule( @PathParam("module") String moduleId ) {

		try {
			apiHelper.deleteModule( moduleId );
		}
        catch ( IllegalArgumentException e ) {
			throw new ProvisioningAPIException( e.getMessage() );
		}
		return Response.ok().build();
	}

    /**
     * List all modules
	 * available in application/json or application/xml
     * @return Returns a serialization of Module
     */
    @GET
    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
    public Modules listModules() {

    	Modules modules = new Modules();

        try {
            modules.setModuleList( apiHelper.listAllModules() );
        }
        catch ( IllegalArgumentException e ) {
            throw new ProvisioningAPIException( e.getMessage() );
        }
        return modules;
    }

    /**
     * Create a new module, 201 on success 
     *  <pre>
     * {@code
     *	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     *     <module>
     *       <id>abc1234</id>
     *       <name>New Test Module</name>
     *       <description>New Test Module</description>
     *       <moduleURL>http://www.example.com/module/abc1234</moduleURL>
     *     </module>
     *       
     *       
     * }
     * </pre>
     * @param moduleElement sample above or XSD definition.
     * @return Expect a 200 ok
     */
    @POST
    @Consumes( MediaType.APPLICATION_XML )
    public Response createModule( JAXBElement<Module> moduleElement ) {

        Module module = moduleElement.getValue();
        String moduleId = null;
        
        try{
        	moduleId =  module.getId();
        }catch(IllegalArgumentException e){
        	String message = e.getMessage();
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(message).build());
        }
        module.setId( moduleId );

        try {
        	apiHelper.createModule( module );
        }
        catch ( IllegalArgumentException e ) {
            throw new ProvisioningAPIException( e.getMessage() );
        }

        return Response.created( uriInfo.getAbsolutePath() ).build();
    }

    /**
     * Get module availability data
     * @param moduleId expecting a KIS compliant module id..
     * @return Returns a serialization of ModuleAvailability
     */
    @Path("/{module}/availability")
	public ModuleAvailabilityResource getModuleAvailability( @PathParam("module") String moduleId ) {
		ModuleAvailabilityResource moduleAvailability = resourceContext.getResource(ModuleAvailabilityResource.class);
		moduleAvailability.setModuleId( moduleId );
		return moduleAvailability;
	}

}
