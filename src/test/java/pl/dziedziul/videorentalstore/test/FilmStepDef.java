package pl.dziedziul.videorentalstore.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.dziedziul.videorentalstore.film.FilmDto;

public class FilmStepDef extends AbstractStepDef {

    private List<FilmDto> lastReadFilms = new ArrayList<>();

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
