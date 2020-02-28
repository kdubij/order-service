package org.kdubij.orderservice.core.crud.product

import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument
import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.ProductMapper
import org.kdubij.orderservice.exception.ResourceNotFoundException
import spock.lang.Specification
import spock.lang.Subject

class ProductServiceSpec extends Specification {
    def repo = Mock(ProductRepository)

    def mapper = Mock(ProductMapper)

    @Subject
    def service = ProductService.builder().productMapper(mapper).productRepository(repo).build()

    def "Should not update product for not existing product"() {
        given: "Not existing product id"
        def id = "not-existing-product-id"

        and: "Update product request"
        def request = UpdateProductRequest.builder().name("New product name").price("999").build()

        and: "Repository call is mocked"
        repo.findById(id) >> Optional.empty()

        when: "Updating product"
        service.update(request, id)

        then: "ResourceNotFoundException is thrown"
        thrown(ResourceNotFoundException)
    }

    def "Should not update product with null name and null price"() {
        given: "Null values"
        def name = null
        def price = null

        and: "Existing product id"
        def id = "id"

        and: "Update product request"
        def request = UpdateProductRequest.builder().name(name).price(price).build()

        and: "Repository call is mocked"
        repo.findById(id) >> Optional.of(ProductDocument.builder().name("Name").price(100L).build())

        when: "Updating product"
        service.update(request, id)

        then: "Product is saved with non null values"
        1 * repo.save({
            assert it.name != null
            assert it.price != null
        })


    }
}
