package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.customer.CustomerNotFoundException
import pl.dziedziul.videorentalstore.customer.CustomerService
import pl.dziedziul.videorentalstore.rental.RentedFilmNotFoundException
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand
import pl.dziedziul.videorentalstore.rental.command.RentalDto
import pl.dziedziul.videorentalstore.rental.command.ReturnFilmsCommand
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class DefaultRentalServiceTest extends Specification {

    def "should rent films"() {
        given:
        def rentalRepository = Mock(RentalRepository)
        def rentalCreator = Mock(RentalEntityCreator)
        def tested = new DefaultRentalService(Mock(CustomerService), rentalRepository, rentalCreator, Mock(RentedFilmRepository), Mock(SurchargeCalculator))
        1 * rentalCreator.create(_) >> new RentalEntity(TestData.SOME_CUSTOMER_ID, TestData.SOME_INSTANT)
                .withRentedFilm(new RentedFilmEntity(TestData.SOME_FILM_ID, TestData.SOME_PRICE, TestData.SOME_NUMBER_OF_DAYS))
        1 * rentalRepository.save(_) >> { args -> args[0] }
        when:
        RentalDto rental = tested.rentFilms(new RentFilmsCommand(TestData.SOME_CUSTOMER_ID, Set.of()))
        then:
        rental.id
        rental.price == TestData.SOME_PRICE
    }

    def "should fail when customer not found"() {
        given:
        def customerService = Stub(CustomerService) {
            getCustomer(_) >> { args -> throw new CustomerNotFoundException(args[0]) }
        }
        def tested = new DefaultRentalService(customerService, Mock(RentalRepository), Mock(RentalEntityCreator), Mock(RentedFilmRepository), Mock(SurchargeCalculator))
        when:
        tested.rentFilms(new RentFilmsCommand(TestData.SOME_CUSTOMER_ID, Set.of()))
        then:
        thrown(CustomerNotFoundException)
    }

    def "should return films and calculate surcharge"() {
        given:
        def rentedFilmEntity1 = new RentedFilmEntity()
        def rentedFilmEntity2 = new RentedFilmEntity()
        def rentedFilmRepository = Stub(RentedFilmRepository) {
            findFirstByRentalEntity_IdAndFilmIdAndStatus(_, _, RentalStatus.RENTED) >>> [Optional.of(rentedFilmEntity1), Optional.of(rentedFilmEntity2)]
        }
        def surchargeCalculator = Stub(SurchargeCalculator) {
            calculateSurcharge(_) >>> [2, 3]
        }
        def tested = new DefaultRentalService(Mock(CustomerService), Mock(RentalRepository), Mock(RentalEntityCreator), rentedFilmRepository, surchargeCalculator)
        when:
        def result = tested.returnFilms(new ReturnFilmsCommand(TestData.SOME_RENTAL_ID, Set.of(TestData.SOME_FILM_ID, TestData.SOME_OTHER_FILM_ID)))
        then:
        result.surcharge == 5
        rentedFilmEntity1.status == RentalStatus.RETURNED
        rentedFilmEntity1.status == RentalStatus.RETURNED
    }

    def "should fail when trying to return not rented film"() {
        given:
        def rentedFilmRepository = Stub(RentedFilmRepository) {
            findFirstByRentalEntity_IdAndFilmIdAndStatus(_, _, RentalStatus.RENTED) >> Optional.empty()
        }
        def tested = new DefaultRentalService(Mock(CustomerService), Mock(RentalRepository), Mock(RentalEntityCreator), rentedFilmRepository, Mock(SurchargeCalculator))
        when:
        tested.returnFilms(new ReturnFilmsCommand(TestData.SOME_RENTAL_ID, Set.of(TestData.SOME_FILM_ID, TestData.SOME_OTHER_FILM_ID)))
        then:
        thrown(RentedFilmNotFoundException)
    }

}
