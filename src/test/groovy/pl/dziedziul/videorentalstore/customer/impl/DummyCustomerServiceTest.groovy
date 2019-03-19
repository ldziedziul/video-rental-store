package pl.dziedziul.videorentalstore.customer.impl

import pl.dziedziul.videorentalstore.customer.CustomerDto
import pl.dziedziul.videorentalstore.customer.CustomerNotFoundException
import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification

class DummyCustomerServiceTest extends Specification {

    def "should return customer"() {
        given:
        def tested = new DummyCustomerService()
        when:
        CustomerDto customerDto = tested.getCustomer(TestData.SOME_CUSTOMER_ID)
        then:
        customerDto.id == TestData.SOME_CUSTOMER_ID
        customerDto.name == "customer-name"
    }

    def "should fail when customer not found"() {
        given:
        def tested = new DummyCustomerService()
        when:
        tested.getCustomer(TestData.SOME_NONEXISTING_CUSTOMER_ID)
        then:
        thrown(CustomerNotFoundException)
    }
}
