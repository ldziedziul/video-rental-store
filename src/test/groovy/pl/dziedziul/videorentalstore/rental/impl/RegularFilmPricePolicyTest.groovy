package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.film.FilmType
import spock.lang.Specification

class RegularFilmPricePolicyTest extends Specification {
    def tested = new RegularFilmPricePolicy()

    def "should support only regular films"() {
        given:
        expect:
        tested.supports(filmType) == supported
        where:
        filmType         | supported
        FilmType.NEW     | false
        FilmType.OLD     | false
        FilmType.REGULAR | true
    }

    def "should calculate price"() {
        expect:
        tested.calculatePrice(basePrice, days) == expectedPrice
        where:
        basePrice | days | expectedPrice
        30        | 5    | 90
        30        | 2    | 30
        10        | 3    | 10
        30        | 1    | 30
    }
}
