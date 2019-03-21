package pl.dziedziul.videorentalstore.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.dziedziul.videorentalstore.bonus.BonusPointsDto;
import pl.dziedziul.videorentalstore.film.FilmDto;
import pl.dziedziul.videorentalstore.film.FilmService;
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.RentalDto;
import pl.dziedziul.videorentalstore.rental.command.ReturnFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.ReturnResultDto;

public class RentalStepDef extends AbstractStepDef {
    private static final UUID CUSTOMER_ID = UUID.fromString("12345678-1987-0000-0000-000000000000");
    private static final Logger log = LoggerFactory.getLogger(RentalStepDef.class);

    @Autowired
    private FilmService filmService;
    private RentalDto lastRental;
    private ReturnResultDto lastReturnResult;
    private BonusPointsDto lastBonusPointsDto;

    @When("user want to rent {string} for {int} days")
    public void userWantToRentAFilm(String name, int days) {
        lastBonusPointsDto = getBonusPoints();
        rentFilm(name, days);
    }

    private void rentFilm(final String name, final int days) {
        Set<RentFilmsCommand.FilmToRent> filmsToRent = Set.of(filmToRent(name, days));
        ResponseEntity<RentalDto> response = restTemplate
            .postForEntity("/rentals", rentFilms(filmsToRent), RentalDto.class);
        this.lastRental = response.getBody();
    }

    private RentFilmsCommand rentFilms(final Set<RentFilmsCommand.FilmToRent> filmsToRent) {
        return new RentFilmsCommand(CUSTOMER_ID, filmsToRent);
    }

    private RentFilmsCommand.FilmToRent filmToRent(final String filmName, final int days) {
        FilmDto film = filmService.getFilmByName(filmName);
        return new RentFilmsCommand.FilmToRent(film.getId(), days);
    }

    @Then("film should be rented and price calculated")
    public void filmShouldBeRentedAndPriceCalculated() {
        assertNotNull("Rental should be created", lastRental);
        assertTrue("Rental price is positive", lastRental.getPrice() > 0);
    }

    @Then("film should be rented for {int} SEK")
    public void filmsShouldBeRentedForPriceSEK(int price) {
        assertThat(lastRental.getPrice()).isEqualTo(price);
    }

    @When("user want to rent all films")
    public void userWantToRentAllFilms() {
        Set<RentFilmsCommand.FilmToRent> filmsToRent = Set.of(
            filmToRent("Matrix 11", 1),
            filmToRent("Spider Man", 5),
            filmToRent("Spider Man 2", 2),
            filmToRent("Ouf of Africa", 7)
        );
        ResponseEntity<RentalDto> response = restTemplate
            .postForEntity("/rentals", rentFilms(filmsToRent), RentalDto.class);
        this.lastRental = response.getBody();
    }

    @Then("films should be rented for {int} SEK")
    public void filmsShouldBeRentedForTotalSEK(int price) {
        filmsShouldBeRentedForPriceSEK(price);
    }

    @Given("user rented {string} for {int} days")
    public void userRentedFilmForDays(final String filmName, final int days) {
        rentFilm(filmName, days);
    }

    @When("user returns {string} after {int} days")
    public void userReturnsAfterReturnDaysDays(final String filmName, final int returnDays) {
        clock.windForwardDays(returnDays);
        ResponseEntity<ReturnResultDto> response = restTemplate
            .postForEntity("/rentals/returns", new ReturnFilmsCommand(lastRental.getId(), Set.of(filmService.getFilmByName(filmName).getId())), ReturnResultDto.class);
        this.lastReturnResult = response.getBody();
    }

    @Then("user should pay {int} for late return")
    public void userShouldPaySurchargeForLateReturn(final int surcharge) {
        assertThat(lastReturnResult.getSurcharge()).isEqualTo(surcharge);
    }

    @And("user should get {int} bonus points")
    public void userShouldGetBonusPointsBonusPoints(final int expectedBonusPoints) {
        sleepMillis(100);
        BonusPointsDto bonusPoints = getBonusPoints();
        int addedBonusPoints = bonusPoints.getPoints() - lastBonusPointsDto.getPoints();
        assertThat(addedBonusPoints).isEqualTo(expectedBonusPoints);

    }

    private BonusPointsDto getBonusPoints() {
        return restTemplate.getForObject("/bonuses/" + CUSTOMER_ID, BonusPointsDto.class);
    }

    private void sleepMillis(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.warn("Sleep interrupted", e);
        }
    }
}
