package pl.dziedziul.videorentalstore.films.impl

import pl.dziedziul.videorentalstore.films.AddFilmCommand
import pl.dziedziul.videorentalstore.films.FilmNotFoundException
import pl.dziedziul.videorentalstore.test.TestClock
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

import static pl.dziedziul.videorentalstore.films.impl.FilmTestData.*

class DefaultFilmServiceTest extends Specification {
    def "should get films"() {
        given:
        def filmRepository = Mock(FilmRepository)
        1 * filmRepository.findAllByOrderByDateCreated() >> [MATRIX, SPIDERMAN]
        def tested = new DefaultFilmService(filmRepository, TestClock.defaultMalta())
        when:
        def result = tested.films
        then:
        result.size() == 2
        result*.name == [MATRIX, SPIDERMAN]*.name
    }

    def "should get a film"() {
        given:
        def filmRepository = Mock(FilmRepository)
        1 * filmRepository.findById(_) >> Optional.of(MATRIX)
        def tested = new DefaultFilmService(filmRepository, TestClock.defaultMalta())
        when:
        def result = tested.getFilm(TestData.SOME_FILM_ID)
        then:
        result.name == MATRIX.name
    }

    def "should fail when no film with given id"() {
        given:
        def filmRepository = Mock(FilmRepository)
        1 * filmRepository.findById(_) >> Optional.empty()
        def tested = new DefaultFilmService(filmRepository, TestClock.defaultMalta())
        when:
        tested.getFilm(TestData.SOME_FILM_ID)
        then:
        thrown FilmNotFoundException
    }

    def "should get a film by name"() {
        given:
        def filmRepository = Mock(FilmRepository)
        1 * filmRepository.findByName(MATRIX.name) >> Optional.of(MATRIX)
        def tested = new DefaultFilmService(filmRepository, TestClock.defaultMalta())
        when:
        def result = tested.getFilmByName(MATRIX.name)
        then:
        result.name == MATRIX.name
        result.id == MATRIX.id
    }

    def "should fail when no film with given name"() {
        given:
        def filmRepository = Mock(FilmRepository)
        1 * filmRepository.findByName(_) >> Optional.empty()
        def tested = new DefaultFilmService(filmRepository, TestClock.defaultMalta())
        when:
        tested.getFilmByName(TestData.SOME_NAME)
        then:
        thrown FilmNotFoundException
    }

    def "should add film"() {
        given:
        def filmRepository = Mock(FilmRepository)
        1 * filmRepository.save(_) >> { args -> args[0] }
        def tested = new DefaultFilmService(filmRepository, TestClock.defaultMalta())
        when:
        def result = tested.addFilm(new AddFilmCommand(OUT_OF_AFRICA.name, OUT_OF_AFRICA.type))
        then:
        result.name == OUT_OF_AFRICA.name
        result.type == OUT_OF_AFRICA.type
        result.id
        result.id != OUT_OF_AFRICA.id
    }
}
