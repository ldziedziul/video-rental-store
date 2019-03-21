package pl.dziedziul.videorentalstore.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

abstract class AbstractStepDef {

    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    protected TestClock clock;

    @Before
    public void setUp(final Scenario scenario) {
        RestTemplate actualRestTemplate = this.restTemplate.getRestTemplate();
        allowMultipleResponseReads(actualRestTemplate);
        actualRestTemplate.setInterceptors(List.of(new CucumberLoggingRequestInterceptor(scenario)));
        clock.reset();
    }

    private void allowMultipleResponseReads(final RestTemplate actualRestTemplate) {
        actualRestTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }
}
