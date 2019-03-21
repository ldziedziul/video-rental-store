package pl.dziedziul.videorentalstore.bonus.impl

import pl.dziedziul.videorentalstore.test.TestData
import spock.lang.Specification
import spock.lang.Unroll

class DefaultBonusServiceTest extends Specification {
    @Unroll
    def "should return customer bonus points"() {
        def bonusRepository = Stub(BonusRepository) {
            getPointSumForCustomer(TestData.SOME_CUSTOMER_ID) >> 3
        }
        given:
        def tested = new DefaultBonusService(bonusRepository)
        when:
        def points = tested.getCustomerBonusPoints(customerId)
        then:
        points == expectedPoints
        where:
        customerId                            | expectedPoints
        TestData.SOME_CUSTOMER_ID             | 3
        TestData.SOME_NONEXISTING_CUSTOMER_ID | 0
    }
}
