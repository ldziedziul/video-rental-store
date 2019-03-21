package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.film.FilmType
import spock.lang.Specification

class PriceLevelMapperTest extends Specification {

    def "should map film type to price level"() {
        given:
        def tested = new PriceLevelMapper(new RentalProperties([:],
                [
                        (FilmType.REGULAR): PriceLevel.BASIC,
                        (FilmType.NEW)    : PriceLevel.PREMIUM
                ])
        )
        when:
        def result = tested.mapToPriceType(FilmType.NEW)
        then:
        result == PriceLevel.PREMIUM
    }

    def "should fail when no rental properties"() {
        when:
        new PriceLevelMapper(null)
        then:
        thrown NullPointerException
    }

    def "should fail when no price level mappings"() {
        given:
        def tested = new PriceLevelMapper(new RentalProperties([:], [:]))
        when:
        tested.mapToPriceType(FilmType.NEW)
        then:
        thrown PriceLevelNotFoundException
    }

    def "should fail when no price level for given film type"() {
        given:
        def tested = new PriceLevelMapper(new RentalProperties([:], [(FilmType.REGULAR): PriceLevel.BASIC]))
        when:
        tested.mapToPriceType(FilmType.NEW)
        then:
        thrown PriceLevelNotFoundException
    }

    def "should validate"() {
        given:
        def tested = new PriceLevelMapper(new RentalProperties([:], [
                (FilmType.REGULAR): PriceLevel.BASIC,
                (FilmType.NEW)    : PriceLevel.BASIC,
                (FilmType.OLD)    : PriceLevel.BASIC,
        ])
        )
        expect:
        tested.validateConfig()
    }

    def "should fail when missing mappings"() {
        given:
        def tested = new PriceLevelMapper(new RentalProperties([:], [
                (FilmType.REGULAR): PriceLevel.BASIC,
                (FilmType.NEW)    : PriceLevel.BASIC,
        ])
        )
        when:
        tested.validateConfig()
        then:
        thrown PriceLevelNotFoundException
    }
}
