package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.films.FilmDto
import pl.dziedziul.videorentalstore.films.FilmNotFoundException
import pl.dziedziul.videorentalstore.films.FilmService
import pl.dziedziul.videorentalstore.films.FilmType
import pl.dziedziul.videorentalstore.rental.RentFilmsCommand
import pl.dziedziul.videorentalstore.test.TestClock
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class RentalEntityCreatorTest extends Specification {

    def "should create rental"() {
        given:
        def filmService = Stub(FilmService) {
            getFilm(_) >> new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        }
        def filmPriceCalculator = Mock(FilmPriceCalculator)
        2 * filmPriceCalculator.calculatePrice(_, _) >> TestData.SOME_PRICE
        def tested = new RentalEntityCreator(filmService, filmPriceCalculator, TestClock.defaultMalta())
        when:
        RentalEntity rentalEntity = tested.create(rentFilmsCommand())
        then:
        rentalEntity.id
        rentalEntity.rentedFilms.size() == 2
        rentalEntity.totalPrice == 2 * TestData.SOME_PRICE
        rentalEntity.customerId == TestData.SOME_CUSTOMER_ID
    }

    def "should fail when film not found"() {
        given:
        def filmService = Stub(FilmService) {
            getFilm(_) >> { args -> throw new FilmNotFoundException("id", args[0]) }
        }
        def tested = new RentalEntityCreator(filmService, Mock(FilmPriceCalculator), TestClock.defaultMalta())
        when:
        tested.create(rentFilmsCommand())
        then:
        thrown(FilmNotFoundException)
    }

    private static RentFilmsCommand rentFilmsCommand() {
        def filmsToRent = Set.of(
                new RentFilmsCommand.FilmToRent(TestData.SOME_FILM_ID, 2),
                new RentFilmsCommand.FilmToRent(TestData.SOME_OTHER_FILM_ID, 3)
        )
        def rentFilmsCommand = new RentFilmsCommand(TestData.SOME_CUSTOMER_ID, filmsToRent)
        rentFilmsCommand
    }
}
