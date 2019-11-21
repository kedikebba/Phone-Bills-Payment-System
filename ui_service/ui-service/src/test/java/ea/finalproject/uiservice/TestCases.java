package ea.finalproject.uiservice;

import ea.finalproject.uiservice.model.ServiceProvider;
import ea.finalproject.uiservice.service.SimpleRestService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@WebMvcTest(controllers = ServiceProvider.class)
@ActiveProfiles("test")
public class TestCases {

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SimpleRestService simpleRestService;


    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testProvidersLink(){
        mockServer.expect(requestTo("http://34.70.154.170/provider/atnt"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"),
                containsString("resultSuccess")));
    }

    @Test
    public void testGetMessage_500() {
        mockServer.expect(requestTo("http://34.70.154.170/provider/atnt")).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }

    @Test
    public void testGetMessage_404() {
        mockServer.expect(requestTo("http://34.70.154.170/provider/atnt")).andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = simpleRestService.getMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }

}
