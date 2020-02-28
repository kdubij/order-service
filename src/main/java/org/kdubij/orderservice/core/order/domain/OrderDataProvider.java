package org.kdubij.orderservice.core.order.domain;

import java.time.Instant;
import java.util.List;

public interface OrderDataProvider {

    List<Order> retrieveWithTimePeriod(Instant from, Instant to);

    Order save(Order order);
}
