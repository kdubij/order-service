package org.kdubij.orderservice.core.order.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends MongoRepository<OrderDocument, String> {

    @Query("{'submittedAt' : { $gte: ?0, $lte: ?1 } }")
    List<OrderDocument> findAllBetweenDatesInclusive(Instant from, Instant to);

}
