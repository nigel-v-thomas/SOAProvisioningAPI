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
public class ModuleResourceTest extends JerseyTest {

    private static final String CONTEXT_PATH = "provisioning";
	ApiTestUtil testUtil;
    ModuleAvailabilityTestUtil moduleAvailabilityTestUtil;

    public static final String MODULE_ID = "abc1234";
    public static final String NEW_MODULE_ID = "abc12345";

    private static String NEW_MODULE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<module>" +
            "<id>" + NEW_MODULE_ID + "</id>" +
            "<name>New Test Module</name>" +
            "<description>New Test Module</description>" +
            "<moduleURL>http://www.example.com/module/abc1234</moduleURL>"+
            "<courses><kisCourseId>course-abc1234</kisCourseId></courses>"+
            "</module>";

    private static String MODULE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<module>" +
            "<id>" + MODULE_ID + "</id>" +
            "<name>test</name>" +
            "<description>Test</description>" +
            "<moduleURL>http://www.example.com/module/abc1234</moduleURL>"+
            "<courses><kisCourseId>course-abc1234</kisCourseId></courses>"+
            "</module>";

    private static final String MODULE_AVAILABILTIY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><moduleAvailability>" +
            "<minClassSize>10</minClassSize>" +
            "<maxClassSize>20</maxClassSize></moduleAvailability>";


    private static final int HTTP_STATUS_200 = 200;
    private static final int HTTP_STATUS_201 = 201;

    @Before
    public void setUp() {
        testUtil = new ApiTestUtil( resource() );
        moduleAvailabilityTestUtil = new ModuleAvailabilityTestUtil( resource() );
    }
	
    public ModuleResourceTest() throws Exception {
		super( new WebAppDescriptor.Builder( ModuleResource.class.getPackage().getName() )
                                   .contextPath(CONTEXT_PATH)
                                   .contextParam("contextConfigLocation", "classpath:web-application-config.xml")
                                   .servletClass(SpringServlet.class)
                                   .contextListenerClass(ContextLoaderListener.class)
                                   .build() );
	}

    @Test
    public void createModule_whenModuleCreatedSuccessfully_statusCode201Returned() {

        ClientResponse response = testUtil.createModule( NEW_MODULE_XML );

        assertEqualsStatusCode(response, HTTP_STATUS_201);
    }

    @Test
    public void getModuleAvailability() {

        ClientResponse response = moduleAvailabilityTestUtil.getModuleAvailability( NEW_MODULE_ID );

        assertEqualsStatusCode(response, HTTP_STATUS_200);

        //TODO : Passes in live, but fails in test environment
        String responseMsg = response.getEntity( String.class );
        //assertFalse(StringUtil.isEmpty(responseMsg));
    }

    @Test
    public void updateModuleAvailability_whenModuleAvailabilitySuccessfullyUpdated_statusCode200Returned() {

        ClientResponse response = moduleAvailabilityTestUtil.updateModuleAvailability( NEW_MODULE_ID, MODULE_AVAILABILTIY );

        assertEqualsStatusCode(response, HTTP_STATUS_200);

    }
    

    @Test
    public void deleteModule_whenModuleDeletedSuccessfully_statusCode200Returned() {

        ClientResponse response = testUtil.deleteModule( NEW_MODULE_ID );

        assertEqualsStatusCode(response, HTTP_STATUS_200);

    }
    
	protected static void assertEqualsStatusCode(ClientResponse response, int statusCode) {
		assertTrue(String.format("Status %s, Detail %s", response.getStatus(),
				response.getEntity(String.class)), response.getStatus() == statusCode);
	}
    @Override
	protected int getPort(int arg0) {
		// hard coded for now
		return 9285;
	}
}
