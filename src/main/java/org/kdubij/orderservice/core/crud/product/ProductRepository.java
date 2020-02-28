package org.kdubij.orderservice.core.crud.product;

import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

interface ProductRepository extends MongoRepository<ProductDocument, String> {

}
