package org.kdubij.orderservice.core.order.domain

import org.kdubij.orderservice.exception.ResourceNotFoundException
import org.kdubij.orderservice.exception.ValidationException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.Instant

class OrderSubmissionSpec extends Specification {
    def orderDataProvider = Mock(OrderDataProvider)

    def productDataProvider = Mock(ProductDataProvider)

    static def EMPTY_MAIL_MSG = "Email should be provided"
    static def NOT_VALID_MAIL_MSG = "Email is not correct"

    @Subject
    def orderSubmission = new OrderSubmission(orderDataProvider, productDataProvider)


    def "Should submit order for correctly"() {
        given: "Valid data"
        def mail = "abc@xyz.com"
        def productList = ["id"]

        and: "Mocks are prepared"
        def order = Mock(Order)
        productDataProvider.findProductById("id") >> Optional.of(getProduct())

        when: "Submitting order"
        def submittedOrder = orderSubmission.submit(mail, productList)

        then: "Order is submitted"
        submittedOrder == order

        1 * orderDataProvider.save({
            assert it.id == null
            assert !it.submittedAt.isAfter(Instant.now())
            assert it.products == [getProduct()]
            assert it.email == mail
        } as Order) >> order
    }

    def "Should throw exception when product is not found"() {
        given: "Product not exists for given id"
        def id = "not-existing-product"
        productDataProvider.findProductById(id) >> Optional.empty()

        when: "Submitting order"
        orderSubmission.submit("a@x.com", [id])

        then: "Re is thrown"
        def exception = thrown(ResourceNotFoundException)
        exception.message == "Product with given id not exists {productId=${id}}"
    }

    @Unroll
    def "Should throw exception for not valid email #givenEmail"() {
        given: "Not valid email"
        def email = givenEmail

        when: "Submitting order"
        orderSubmission.submit(email, [])

        then: "Exception is thrown"
        def exception = thrown(ValidationException)
        exception.message == givenMsg

        where:
        givenEmail | givenMsg
        null       | EMPTY_MAIL_MSG
        ""         | EMPTY_MAIL_MSG
        "abc"      | NOT_VALID_MAIL_MSG
        "123"      | NOT_VALID_MAIL_MSG
        "ab @"     | NOT_VALID_MAIL_MSG
        "ab@"      | NOT_VALID_MAIL_MSG
        "ab@."     | NOT_VALID_MAIL_MSG
        "ab@.com"  | NOT_VALID_MAIL_MSG
        "ab@.com"  | NOT_VALID_MAIL_MSG
        "@.com"    | NOT_VALID_MAIL_MSG
    }

    def "Should throw exception for empty product list"() {
        given: "Empty product list"
        def products = []

        and: "Valid email"
        def email = "abc@xyz.com"

        when: "Submitting order"
        orderSubmission.submit(email, products)

        then: "Exception is thrown"
        def exception = thrown(ValidationException)
        exception.message == "Product list must be not empty"
    }

    private static Product getProduct() {
        Product.builder().id("id").price(200L).name("Product").build()
    }
}
