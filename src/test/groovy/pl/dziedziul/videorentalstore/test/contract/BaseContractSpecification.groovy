package pl.dziedziul.videorentalstore.test.contract

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.dziedziul.videorentalstore.films.FilmDto
import pl.dziedziul.videorentalstore.films.FilmService
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
                new FilmDto(UUID.fromString("12345678-1983-0000-0000-000000000000"), "Matrix 11", NEW),
                new FilmDto(UUID.fromString("12345678-1984-0000-0000-000000000000"), "Spider Man", REGULAR)
        ]
    }

    def setup() {
        RestAssuredMockMvc.mockMvc(mvc)
    }
}
