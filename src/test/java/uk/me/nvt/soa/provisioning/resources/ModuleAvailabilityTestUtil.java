package uk.me.nvt.soa.provisioning.resources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class ModuleAvailabilityTestUtil {

    WebResource webResource;

    public static final String GROUPS_PATH = "modules";
    public static final String SETTINGS_PATH = "availability";

    public ModuleAvailabilityTestUtil( WebResource webResource ) {
        this.webResource = webResource;
    }

    public ClientResponse getModuleAvailability( final String groupId ) {

        return webResource.path( GROUPS_PATH )
                          .path( groupId )
                          .path( SETTINGS_PATH )
                          .get( ClientResponse.class );
    }

    public ClientResponse updateModuleAvailability( final String groupId, final String groupSettings ) {

        return webResource.path( GROUPS_PATH )
                          .path( groupId )
                          .path( SETTINGS_PATH )
                          .type( MediaType.APPLICATION_XML )
                          .accept( MediaType.TEXT_PLAIN )
                          .entity( groupSettings )
                          .put( ClientResponse.class );
    }

}
