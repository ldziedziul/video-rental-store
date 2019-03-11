package pl.dziedziul.videorentalstore.films.impl


import spock.lang.Specification

import static pl.dziedziul.videorentalstore.films.FilmType.REGULAR
import static pl.dziedziul.videorentalstore.test.TestData.SOME_INSTANT
import static pl.dziedziul.videorentalstore.test.TestData.SOME_NAME

class FilmEntityTest extends Specification {

    def "should reject null values in constructor"() {
        when:
        new FilmEntity(name, type, date)
        then:
        thrown NullPointerException
        where:
        name      | type    | date
        null      | REGULAR | SOME_INSTANT
        SOME_NAME | null    | SOME_INSTANT
        SOME_NAME | REGULAR | null
    }

    def "should have non null id after creation"() {
        when:
        def entity = new FilmEntity(SOME_NAME, REGULAR, SOME_INSTANT)
        then:
        entity.id
    }
}
