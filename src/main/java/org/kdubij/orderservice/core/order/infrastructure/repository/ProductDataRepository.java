package org.kdubij.orderservice.core.order.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDataRepository extends MongoRepository<ProductDocument, String> {

}
