package pl.dziedziul.videorentalstore.films

import spock.lang.Specification

class AddFilmCommandTest extends Specification {
    def "should create command with valid values"() {
        expect:
        new AddFilmCommand("film name", FilmType.REGULAR)

    }

    def "should reject null film name"() {
        when:
        new AddFilmCommand(null, FilmType.NEW)
        then:
        thrown NullPointerException
    }

    def "should reject null film type"() {
        when:
        new AddFilmCommand("film name", null)
        then:
        thrown NullPointerException
    }
}
