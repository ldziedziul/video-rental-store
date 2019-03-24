package pl.dziedziul.videorentalstore.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    plugin = { "pretty", "json:build/reports/cucumber/cucumber-report.json" }
)
public class CucumberRunner {
}
