package pl.dziedziul.videorentalstore.bonus.impl

import pl.dziedziul.videorentalstore.film.FilmDto
import pl.dziedziul.videorentalstore.film.FilmService
import pl.dziedziul.videorentalstore.film.FilmType
import pl.dziedziul.videorentalstore.rental.FilmRentedEvent
import pl.dziedziul.videorentalstore.test.TestClock
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class BonusCreatorTest extends Specification {
    def "should add points"() {
        given:
        def bonusRepository = Mock(BonusRepository)
        def filmService = Stub(FilmService) {
            getFilm(_) >> new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        }
        def clock = TestClock.defaultMalta()
        def tested = new BonusCreator(filmService, bonusRepository, clock)
        when:
        tested.addBonusPoints(new FilmRentedEvent(this, TestData.SOME_FILM_ID, TestData.SOME_CUSTOMER_ID))
        then:
        1 * bonusRepository.save({
            it.points == 2
            it.customerId == TestData.SOME_CUSTOMER_ID
            it.filmId = TestData.SOME_FILM_ID
            it.dateCreated = clock.instant()
        })
        where:
        type             | expectedPoins
        FilmType.NEW     | 2
        FilmType.REGULAR | 1
        FilmType.OLD     | 1
    }
}
