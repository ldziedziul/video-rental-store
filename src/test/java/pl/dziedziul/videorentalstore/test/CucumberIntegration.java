package pl.dziedziul.videorentalstore.test;

import cucumber.api.java.Before;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dziedziul.videorentalstore.Application;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {Application.class})
public class CucumberIntegration {

    @Before
    public void setupCucumber() {
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
    }
}


