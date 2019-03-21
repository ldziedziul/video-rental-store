package pl.dziedziul.videorentalstore.test;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.time.Clock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import cucumber.api.java.Before;
import pl.dziedziul.videorentalstore.Application;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = { Application.class, CucumberIntegration.Config.class })
public class CucumberIntegration {

    @Before
    public void setupCucumber() {
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
    }

    @Configuration
    static class Config {

        @Bean
        @Primary
        Clock testClock() {
            return TestClock.defaultMalta();
        }

    }
}


