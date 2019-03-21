package pl.dziedziul.videorentalstore.rental.impl

import pl.dziedziul.videorentalstore.customer.CustomerNotFoundException
import pl.dziedziul.videorentalstore.customer.CustomerService
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand
import pl.dziedziul.videorentalstore.rental.command.RentalDto
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class DefaultRentalServiceTest extends Specification {

    def "should rent films"() {
        given:
        def rentalRepository = Mock(RentalRepository)
        def rentalCreator = Mock(RentalEntityCreator)
        def tested = new DefaultRentalService(Mock(CustomerService), rentalRepository, rentalCreator)
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
        def tested = new DefaultRentalService(customerService, Mock(RentalRepository), Mock(RentalEntityCreator))
        when:
        tested.rentFilms(new RentFilmsCommand(TestData.SOME_CUSTOMER_ID, Set.of()))
        then:
        thrown(CustomerNotFoundException)
    }

}
