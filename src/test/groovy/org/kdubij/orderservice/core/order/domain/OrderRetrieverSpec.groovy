package org.kdubij.orderservice.core.order.domain

import org.kdubij.orderservice.exception.ValidationException
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class OrderRetrieverSpec extends Specification {

    def dataProvider = Mock(OrderDataProvider)

    @Subject
    def orderRetriever = new OrderRetriever(dataProvider)


    def "Should return orders for correct time perriod"() {
        given: "Date from and date to"
        def dateFrom = Instant.parse("2011-11-10T17:37:14.941240Z")
        def dateTo = Instant.parse("2020-12-10T17:37:14.941240Z")

        and: "Data provider is mocked"
        def mockedOrders = [Mock(Order)]
        1 * dataProvider.retrieveWithTimePeriod(dateFrom, dateTo) >> mockedOrders

        when: "Retrieving mockedOrder with time period"
        def orders = orderRetriever.retrieve(dateFrom, dateTo)

        then: "Orders are returned correctly"
        orders == mockedOrders
    }


    def "Should throw exception when dateFrom is after dateTo"() {
        given: "Date from after date to"
        def dateFrom = Instant.parse("2020-12-10T17:37:14.941240Z")
        def dateTo = Instant.parse("2011-11-10T17:37:14.941240Z")

        when: "Retrieving orders with time period"
        orderRetriever.retrieve(dateFrom, dateTo)

        then: "ValidationException is thrown"
        def exception = thrown(ValidationException)
        exception.message == "Provide correct time period"
    }

}
