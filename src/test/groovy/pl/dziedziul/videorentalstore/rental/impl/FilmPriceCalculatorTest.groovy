package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.film.FilmDto
import pl.dziedziul.videorentalstore.film.FilmType
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class FilmPriceCalculatorTest extends Specification {

    def "should calculate price for given film"() {
        given:
        def rentalProperties = new RentalProperties([(PriceLevel.PREMIUM): TestData.SOME_PRICE], [(FilmType.NEW): PriceLevel.PREMIUM])
        def tested = createCalculator(rentalProperties)
        def filmDto = new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        when:
        def result = tested.calculatePrice(filmDto, TestData.SOME_NUMBER_OF_DAYS)
        then:
        result == TestData.SOME_PRICE * TestData.SOME_NUMBER_OF_DAYS
    }

    def "should fail to return negative price - paranoid check"() {
        given:
        def rentalProperties = new RentalProperties([(PriceLevel.PREMIUM): -1], [(FilmType.NEW): PriceLevel.PREMIUM])
        def tested = createCalculator(rentalProperties)
        def filmDto = new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        when:
        tested.calculatePrice(filmDto, TestData.SOME_NUMBER_OF_DAYS)
        then:
        thrown IllegalArgumentException
    }

    private static FilmPriceCalculator createCalculator(RentalProperties rentalProperties) {
        new FilmPriceCalculator(rentalProperties, new PriceLevelMapper(rentalProperties), [new DummyFilmPricePolicy()])
    }

    static class DummyFilmPricePolicy implements FilmPricePolicy {
        @Override
        boolean supports(final FilmType filmType) {
            return true
        }

        @Override
        int calculatePrice(final int basePrice, final int days) {
            return basePrice * days
        }
    }

    def "should fail when no price level mapper"() {
        when:
        new FilmPriceCalculator(new RentalProperties([:], [:]), null, [])
        then:
        thrown NullPointerException
    }

    def "should fail when no price policies"() {
        given:
        def rentalProperties = new RentalProperties([(PriceLevel.PREMIUM): 1], [(FilmType.NEW): PriceLevel.PREMIUM])
        def tested = new FilmPriceCalculator(rentalProperties, new PriceLevelMapper(rentalProperties), [])
        def filmDto = new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        when:
        tested.calculatePrice(filmDto, TestData.SOME_NUMBER_OF_DAYS)
        then:
        thrown PricePolicyNotFoundException
    }

    def "should fail when no rental properties"() {
        when:
        new FilmPriceCalculator(null, new PriceLevelMapper(new RentalProperties([:], [:])), [])
        then:
        thrown NullPointerException
    }

    def "should fail when no prices"() {
        given:
        def rentalProperties = new RentalProperties([:], [(FilmType.NEW): PriceLevel.PREMIUM])
        def tested = createCalculator(rentalProperties)
        def filmDto = new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        when:
        tested.calculatePrice(filmDto, TestData.SOME_NUMBER_OF_DAYS)
        then:
        thrown PriceNotFoundException
    }

    def "should fail when no film"() {
        given:
        def rentalProperties = new RentalProperties([:], [(FilmType.NEW): PriceLevel.PREMIUM])
        def tested = createCalculator(rentalProperties)
        when:
        tested.calculatePrice(null, TestData.SOME_NUMBER_OF_DAYS)
        then:
        thrown NullPointerException
    }

    def "should fail when no price for given price level"() {
        given:
        def rentalProperties = new RentalProperties([(PriceLevel.BASIC): TestData.SOME_PRICE], [(FilmType.NEW): PriceLevel.PREMIUM])
        def tested = createCalculator(rentalProperties)
        def filmDto = new FilmDto(TestData.SOME_FILM_ID, TestData.SOME_NAME, FilmType.NEW)
        when:
        tested.calculatePrice(filmDto, TestData.SOME_NUMBER_OF_DAYS)
        then:
        thrown PriceNotFoundException
    }
}
