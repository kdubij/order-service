package org.kdubij.orderservice.core.order.infrastructure.repository.mapper

import org.kdubij.orderservice.core.order.domain.Product
import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument
import org.mapstruct.factory.Mappers
import spock.lang.Specification
import spock.lang.Subject

class ProductMapperSpec extends Specification {
    @Subject
    def mapper = Mappers.getMapper(ProductMapper.class)

    def "Should map document to dto"() {
        given: "Product document"
        def document = ProductDocument.builder().id("id").name("name").price(100L).build()

        when: "Mapping document to dto"
        def dto = mapper.toProduct(document)

        then: "Dto is correct"
        verifyAll(dto) {
            id == "id"
            name == "name"
            price == 100L
        }
    }

    def "Should map list of dto to list of documents"() {
        given: "Product list"
        def productList = [Product.builder().id("id").name("name").price(100L).build()]

        when: "Mapping dto to document"
        def document = mapper.toDocuments(productList)

        then: "Document is correct"
        verifyAll(document.first()) {
            id == "id"
            name == "name"
            price == 100L
        }
    }

    def "Should map list of documents to list of dtos"() {
        given: "Product document"
        def documents = [ProductDocument.builder().id("id").name("name").price(100L).build()]

        when: "Mapping documents to dtos"
        def dtos = mapper.toProducts(documents)

        then: "Dto is correct"
        dtos.size() == 1
        verifyAll(dtos.first()) {
            id == "id"
            name == "name"
            price == 100L
        }
    }

}
