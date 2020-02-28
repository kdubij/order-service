package org.kdubij.orderservice.core.order.domain;

import org.kdubij.orderservice.exception.ValidationException;

import java.time.Instant;
import java.util.List;

class OrderRetriever {

    OrderRetriever(OrderDataProvider orderDataProvider) {
        this.orderDataProvider = orderDataProvider;
    }

    private OrderDataProvider orderDataProvider;

    List<Order> retrieve(Instant from, Instant to) {
        validateTimePeriod(from, to);
        return orderDataProvider.retrieveWithTimePeriod(from, to);
    }

    private void validateTimePeriod(Instant from, Instant to) {
        if (from.isAfter(to)) {
            throw new ValidationException("Provide correct time period");
        }
    }
}
