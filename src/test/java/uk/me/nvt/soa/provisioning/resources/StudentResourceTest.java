/**
 * 
 */
package uk.me.nvt.soa.provisioning.resources;

import static org.junit.Assert.*;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.ContextLoaderListener;

import uk.me.nvt.soa.provisioning.resources.ModuleResource;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Integration test api for jersey using a grizzly web server
 *
 * See: http://jersey.java.net/nonav/documentation/latest/user-guide.html#test-framework
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:web-application-config.xml" })
public class StudentResourceTest extends JerseyTest {

    private static final String CONTEXT_PATH = "provisioning";
	ApiTestUtil groupTestUtil;
    ModuleAvailabilityTestUtil groupSettingsTestUtil;

    public static final String STUDENT_ID = "some.saw@example.com";
    private static String STUDENT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<student>" +
            "<id>" + STUDENT_ID + "</id>" +
            "<loginUsername>student@institution.example.com</loginUsername>" +
            "<hostInstitutionId>institution.example.com</hostInstitutionId>"+
            "</student>";

    private static final int HTTP_STATUS_200 = 200;
    private static final int HTTP_STATUS_201 = 201;

    @Before
    public void setUp() {
        groupTestUtil = new ApiTestUtil( resource() );
        groupSettingsTestUtil = new ModuleAvailabilityTestUtil( resource() );
    }
	
    public StudentResourceTest() throws Exception {
		super( new WebAppDescriptor.Builder( ModuleResource.class.getPackage().getName() )
                                   .contextPath(CONTEXT_PATH)
                                   .contextParam("contextConfigLocation", "classpath:web-application-config.xml")
                                   .servletClass(SpringServlet.class)
                                   .contextListenerClass(ContextLoaderListener.class)
                                   .build() );
	}


    @Test
    public void addStudent_whenAddedSuccessfully_statusCode201Returned() {
    	System.out.println(STUDENT_XML);
        ClientResponse response = groupTestUtil.addStudent(STUDENT_XML );

        assertEqualsStatusCode(response, HTTP_STATUS_201);

    }
    
    @Test
    public void getStudent_whenSuccessful_statusCode200Returned() {
    	
    	ClientResponse response = groupTestUtil.getStudent(STUDENT_ID );
    	
    	assertEqualsStatusCode(response, HTTP_STATUS_200);
    	
    }
    
    
    @Test
    public void listStudents_statusCode200Returned() {
    	
    	ClientResponse response = groupTestUtil.listStudents();
    	assertEqualsStatusCode(response, HTTP_STATUS_200);
    	
    }
    
    

    @Test
    public void removeStudent_and_CheckFor_statusCode200Returned() {

        ClientResponse response =  groupTestUtil.removeStudent(STUDENT_ID );

        assertEqualsStatusCode(response, HTTP_STATUS_200);

    }

   
    
	protected static void assertEqualsStatusCode(ClientResponse response, int statusCode) {
		assertTrue(String.format("Status %s, Detail %s", response.getStatus(),
				response.getEntity(String.class)), response.getStatus() == statusCode);
	}
    @Override
	protected int getPort(int arg0) {
		// hard coded for now
		return 9281;
	}
}
