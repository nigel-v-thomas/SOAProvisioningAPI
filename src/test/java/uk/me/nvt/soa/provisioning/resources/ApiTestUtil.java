package uk.me.nvt.soa.provisioning.resources;


import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class ApiTestUtil {

    WebResource webResource;

    public static final String MODULE_PATH = "modules";
    public static final String STUDENT_PATH = "students";

    public ApiTestUtil( WebResource webResource ) {
        this.webResource = webResource;
    }

    public String getModule( final String groupId ) {

        return webResource.path( MODULE_PATH )
                          .path( groupId )
                          .get( String.class );
    }

    public ClientResponse createModule( final String group ) {

        return webResource.path( MODULE_PATH )
                          .type( MediaType.APPLICATION_XML )
                          .entity( group )
                          .post( ClientResponse.class );
    }

    public ClientResponse deleteModule( final String groupId ) {

        return webResource.path( MODULE_PATH )
                          .path( groupId )
                          .delete( ClientResponse.class );
    }

    public ClientResponse updateModule( final String groupId, final String group ) {

        return webResource.path( MODULE_PATH )
                          .path( groupId )
                          .type( MediaType.APPLICATION_XML )
                          .entity( group )
                          .put( ClientResponse.class );
    }

    public ClientResponse addStudent(final String student ) {

		return webResource.path(STUDENT_PATH)
				.type(MediaType.APPLICATION_XML)
				.entity(student)
				.post(ClientResponse.class);
    }

    public ClientResponse removeStudent( final String memberId ) {

        return webResource.path( STUDENT_PATH )
                          .path( memberId )
                          .delete( ClientResponse.class );
    }
    
    public ClientResponse getStudent(String studentId) {
    	return webResource.path( STUDENT_PATH )
                .path( studentId )
    			.get( ClientResponse.class );
    }

	public ClientResponse listStudents() {
	    return webResource.path( STUDENT_PATH )
                .get( ClientResponse.class );

	}
	

}
