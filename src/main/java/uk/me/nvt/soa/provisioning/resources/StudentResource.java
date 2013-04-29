package uk.me.nvt.soa.provisioning.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import uk.me.nvt.soa.provisioning.exception.ProvisioningAPIException;
import uk.me.nvt.soa.provisioning.helper.ProvisioningApiHelper;
import uk.me.nvt.soa.provisioning.model.Module;
import uk.me.nvt.soa.provisioning.model.Student;
import uk.me.nvt.soa.provisioning.model.Students;

@Component
@Scope(value = "singleton")
@Path("/students")
public class StudentResource {

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(StudentResource.class);

	@Autowired
	private ProvisioningApiHelper apiHelper;

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String studentId;

	public StudentResource() {
		super();
	}

	private StudentResource(UriInfo uriInfo, Request request, String studentId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.studentId = studentId;
	}

	/**
	 * Get back a list of student id, if none present returns an empty list.
	 * 
	 * @return Returns a serialization of Students
	 */
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Students getAllStudents() {
		Students student = new Students();

		try {
			student = apiHelper.listAllStudents();
		} catch (Exception e) {
			throw new ProvisioningAPIException(e.getMessage());
		}

		return student;
	}

	/**
	 * Get back a representation of student with an id
	 * 
	 * @param studentId
	 * @return Returns a serialization of Student
	 */
	@GET
	@Path("/{student}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Student getStudent(@PathParam("student") String studentId) {
		Student student = new Student();

		try {
			student = apiHelper.getStudent(studentId);
		} catch (Exception e) {
			throw new ProvisioningAPIException(e.getMessage());
		}

		return student;
	}

	/**
	 * Creates a new student entry
	 * On success returns HTTP 201 Created and an HTTP Location header containing the URI of the resulting order
	 * 
	 * @param headers
	 * @param studentId
	 * @return  201 Created on success
	 */
	@POST
	@Path("/{student}")
	public Response addStudent(@Context HttpHeaders headers,
			@PathParam("student") String studentId) {

		try {
			apiHelper.addStudent(studentId);
		} catch (IllegalArgumentException e) {
			throw new ProvisioningAPIException(e.getMessage());
		}
		return Response.created(URI.create("students/" + studentId)).build();
	}
	
    /**
     * Add a student
     * <pre>
     * {@code
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     * 	<student>
     * 	    <id>some.saw@example.com</id>
     * 	    <loginUsername>student@institution.example.com</loginUsername>
     * 	    <hostInstitutionId>institution.example.com</hostInstitutionId>
     * 	</student>
     * 
     * }
     * </pre>
     * @param studentElement see sample xml encoding or XSD 
     * @return  201 Created on success
     */
    @POST
    @Consumes( MediaType.APPLICATION_XML )
    public Response addStudent( JAXBElement<Student> studentElement ) {

    	Student student = studentElement.getValue();
        String studentId = null;

        try{
        	studentId =  student.getId();
        }catch(IllegalArgumentException e){
        	String message = e.getMessage();
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(message).build());
        }
        student.setId( studentId );

        try {
        	apiHelper.creatStudent( student );
        }
        catch ( IllegalArgumentException e ) {
            throw new ProvisioningAPIException( e.getMessage() );
        }

        return Response.created( uriInfo.getAbsolutePath() ).build();
    }

	/**
	 * Remove a student
	 * 
	 * @param student student id
	 * @return  200 OK on success
	 */
	@DELETE
	@Path("/{student}")
	public Response removeStudent(@PathParam("student") String student) {
		try {
			apiHelper.removeStudent(student);
		} catch (IllegalArgumentException e) {
			throw new ProvisioningAPIException(e.getMessage());
		}

		return Response.ok().build();
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
