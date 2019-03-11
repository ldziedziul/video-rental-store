package pl.dziedziul.videorentalstore.films.impl

import pl.dziedziul.videorentalstore.films.AddFilmCommand
import pl.dziedziul.videorentalstore.test.TestClock
import spock.lang.Specification

import static pl.dziedziul.videorentalstore.films.impl.FilmTestData.MATRIX
import static pl.dziedziul.videorentalstore.films.impl.FilmTestData.OUT_OF_AFRICA
import static pl.dziedziul.videorentalstore.films.impl.FilmTestData.SPIDERMAN

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
