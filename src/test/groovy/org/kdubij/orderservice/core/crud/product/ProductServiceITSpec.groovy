package org.kdubij.orderservice.core.crud.product

import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@ActiveProfiles("test")
@Import(EmbeddedMongoAutoConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceITSpec extends Specification {

    @Subject
    @Autowired
    private ProductService service

    @Autowired
    private ProductRepository repository

    def setup() {
        repository.save(ProductDocument.builder().id("id").name("Product").price(100L).build())
    }

    def cleanup() {
        repository.deleteAll()
    }

    def "Should return all products"() {
        when: "Getting all products"
        def products = service.getProducts()

        then: "Products are correctly returned"
        products.size() == 1
        verifyAll(products.first()) {
            id == "id"
            name == "Product"
            price == 100L
        }
    }

    def "Should create new product"() {
        given: "New product request"
        def request = CreateProductRequest.builder().name("Best product").price("1200").build()

        when: "Creating new product"
        def createdProduct = service.create(request)

        then: "Product is created"
        verifyAll(createdProduct) {
            id != null
            name == "Best product"
            price == 1200L
        }

        and: "Saved in db"
        repository.findById(createdProduct.id).isPresent()
    }

    def "Should update product"() {
        given: "Update product request"
        def request = UpdateProductRequest.builder().name("New product name").price("999").build()

        and: "Product id"
        def productId = "id"

        when: "Updating product"
        def updatedProduct = service.update(request, productId)

        then: "Product is created"
        verifyAll(updatedProduct) {
            id == "id"
            name == "New product name"
            price == 999L
        }
    }
}
