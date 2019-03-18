package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.films.FilmType
import spock.lang.Specification

class OldFilmPricePolicyTest extends Specification {
    def tested = new OldFilmPricePolicy()

    def "should support only old films"() {
        given:
        expect:
        tested.supports(filmType) == supported
        where:
        filmType         | supported
        FilmType.NEW     | false
        FilmType.OLD     | true
        FilmType.REGULAR | false
    }

    def "should calculate price"() {
        expect:
        tested.calculatePrice(basePrice, days) == expectedPrice
        where:
        basePrice | days | expectedPrice
        30        | 7    | 90
        30        | 5    | 30
        30        | 4    | 30
        10        | 6    | 20
    }
}
