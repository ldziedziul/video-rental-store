package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.film.FilmDto
import pl.dziedziul.videorentalstore.film.FilmService
import pl.dziedziul.videorentalstore.film.FilmType
import pl.dziedziul.videorentalstore.test.TestClock
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class SurchargeCalculatorTest extends Specification {
    def "should calculate surcharge"() {
        given:
        def clock = TestClock.defaultMalta()
        RentalEntity rental = new RentalEntity(TestData.SOME_CUSTOMER_ID, clock.instant())
        def rentedFilmEntity = new RentedFilmEntity(TestData.SOME_FILM_ID, TestData.SOME_PRICE, TestData.SOME_NUMBER_OF_DAYS)
        rental.withRentedFilm(rentedFilmEntity)
        clock.windForwardDays(TestData.SOME_NUMBER_OF_DAYS + extraDays)
        def filmService = Stub(FilmService) {
            getFilm(_) >> new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        }
        def filmPriceCalculator = Stub(FilmPriceCalculator) {
            calculatePrice(_, _) >> TestData.SOME_BIGGER_PRICE
        }
        def tested = new SurchargeCalculator(filmService, filmPriceCalculator, clock)
        when:
        int surcharge = tested.calculateSurcharge(rentedFilmEntity)
        then:
        surcharge == expectedSurcharge
        where:
        extraDays | expectedSurcharge
        -1        | 0
        0         | 0
        1         | TestData.SOME_BIGGER_PRICE - TestData.SOME_PRICE
    }
}
