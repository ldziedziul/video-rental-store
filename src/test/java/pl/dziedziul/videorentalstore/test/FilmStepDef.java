package pl.dziedziul.videorentalstore.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.dziedziul.videorentalstore.films.FilmDto;

public class FilmStepDef {

    @Autowired
    private TestRestTemplate restTemplate;

    private List<FilmDto> lastReadFilms = new ArrayList<>();

    @Before
    public void setUp(final Scenario scenario) {
        RestTemplate actualRestTemplate = this.restTemplate.getRestTemplate();
        allowMultipleResponseReads(actualRestTemplate);
        actualRestTemplate.setInterceptors(List.of(new CucumberLoggingRequestInterceptor(scenario)));
    }

    private void allowMultipleResponseReads(final RestTemplate actualRestTemplate) {
        actualRestTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    @When("user reads films")
    public void userReadsFilms() {
        lastReadFilms = getFilms();
    }

    @Then("user should get films containing film {string} of type {string}")
    public void userShouldGetFilmsContainingFilmNameOfTypeType(String name, String type) {
        lastReadFilms.stream()
            .filter(f -> Objects.equals(name, f.getName()) && Objects.equals(type, f.getType().name()))
            .findAny().orElseThrow(() -> new AssertionError("Film not found"));
    }

    private List<FilmDto> getFilms() {
        ResponseEntity<List<FilmDto>> response = restTemplate.exchange(
            "/films",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<FilmDto>>() {
            });
        return response.getBody();
    }

}
