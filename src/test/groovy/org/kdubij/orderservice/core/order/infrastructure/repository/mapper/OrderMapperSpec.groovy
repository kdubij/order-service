package org.kdubij.orderservice.core.order.infrastructure.repository.mapper

import org.kdubij.orderservice.core.order.domain.Order
import org.kdubij.orderservice.core.order.domain.Product
import org.kdubij.orderservice.core.order.infrastructure.repository.OrderDocument
import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument
import org.mapstruct.factory.Mappers
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class OrderMapperSpec extends Specification {

    @Subject
    def mapper = Mappers.getMapper(OrderMapper.class)

    def "Should map document to dto"() {
        given: "Order document"
        def document = getOrderDocument()
        def productList = [getProduct()]

        when: "Mapping document to dto"
        def dto = mapper.toOrder(document, productList)

        then: "Dto is correct"
        verifyAll(dto) {
            id == "id"
            products == productList
            email == "mail"
            totalPrice == 200L
            submittedAt == Instant.parse("2011-11-10T17:37:14.941240Z")
        }
    }

    def "Should map dto to document"() {
        given: "Order dto"
        def dto = getOrder()
        def productList = [getProductDocument()]

        when: "Mapping dto to document"
        def document = mapper.toDocument(dto, productList)

        then: "Dto is correct"
        verifyAll(document) {
            id == "id"
            products == productList
            email == "mail"
            submittedAt == Instant.parse("2011-11-10T17:37:14.941240Z")
        }
    }

    private static OrderDocument getOrderDocument() {
        OrderDocument.builder().id("id").email("mail").submittedAt(Instant.parse("2011-11-10T17:37:14.941240Z")).products([getProductDocument()]).build()
    }

    private static Order getOrder() {
        Order.builder().id("id").email("mail").submittedAt(Instant.parse("2011-11-10T17:37:14.941240Z")).products([getProduct()]).build()
    }

    private static ProductDocument getProductDocument() {
        ProductDocument.builder().id("id").price(200L).name("Product").build()
    }

    private static Product getProduct() {
        Product.builder().id("id").price(200L).name("Product").build()
    }

}
