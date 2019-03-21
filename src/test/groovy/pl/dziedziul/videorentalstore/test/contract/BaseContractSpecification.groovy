package pl.dziedziul.videorentalstore.test.contract

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.dziedziul.videorentalstore.films.FilmDto
import pl.dziedziul.videorentalstore.films.FilmService
import pl.dziedziul.videorentalstore.rental.RentalDto
import pl.dziedziul.videorentalstore.rental.RentalService
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

import static pl.dziedziul.videorentalstore.films.FilmType.NEW
import static pl.dziedziul.videorentalstore.films.FilmType.REGULAR

@WebMvcTest
abstract class BaseContractSpecification extends Specification {
    @Autowired
    MockMvc mvc

    @SpringBean
    @SuppressWarnings("unused")
    FilmService filmService = Stub {
        getFilms() >> [
                new FilmDto(TestData.SOME_FILM_ID, "Matrix 11", NEW),
                new FilmDto(TestData.SOME_OTHER_FILM_ID, "Spider Man", REGULAR)
        ]
    }

    @SpringBean
    @SuppressWarnings("unused")
    RentalService rentalService = Stub {
        rentFilms(_) >> new RentalDto(TestData.SOME_RENTAL_ID, TestData.SOME_PRICE)
    }

    def setup() {
        RestAssuredMockMvc.mockMvc(mvc)
    }
}
