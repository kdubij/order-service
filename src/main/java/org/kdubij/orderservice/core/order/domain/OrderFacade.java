package org.kdubij.orderservice.core.order.domain;

import java.time.Instant;
import java.util.List;

public class OrderFacade {

    private OrderSubmission orderSubmission;

    private OrderRetriever orderRetriever;

    public OrderFacade(OrderDataProvider orderDataProvider, ProductDataProvider productDataProvider) {
        this.orderSubmission = new OrderSubmission(orderDataProvider, productDataProvider);
        this.orderRetriever = new OrderRetriever(orderDataProvider);
    }

    public Order submitOrder(String email, List<String> productIds) {
        return orderSubmission.submit(email, productIds);
    }

    public List<Order> retrieveOrders(Instant from, Instant to) {
        return orderRetriever.retrieve(from, to);
    }

}
