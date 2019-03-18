package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.films.FilmType
import spock.lang.Specification

class NewFilmPricePolicyTest extends Specification {
    def tested = new NewFilmPricePolicy()

    def "should support only new films"() {
        given:
        expect:
        tested.supports(filmType) == supported
        where:
        filmType         | supported
        FilmType.NEW     | true
        FilmType.OLD     | false
        FilmType.REGULAR | false
    }

    def "should calculate price"() {
        expect:
        tested.calculatePrice(basePrice, days) == expectedPrice
        where:
        basePrice | days | expectedPrice
        40        | 1    | 40
        40        | 2    | 80
        10        | 3    | 30
    }
}
