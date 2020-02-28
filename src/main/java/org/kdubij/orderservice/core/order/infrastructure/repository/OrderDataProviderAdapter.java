package org.kdubij.orderservice.core.order.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.kdubij.orderservice.core.order.domain.Order;
import org.kdubij.orderservice.core.order.domain.OrderDataProvider;
import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.OrderMapper;
import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDataProviderAdapter implements OrderDataProvider {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final ProductMapper productMapper;

    @Override
    public List<Order> retrieveWithTimePeriod(Instant from, Instant to) {
        return orderRepository.findAllBetweenDatesInclusive(from, to).stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());
    }

    private Order mapToOrder(OrderDocument orderDocument) {
        var products = productMapper.toProducts(orderDocument.getProducts());
        return orderMapper.toOrder(orderDocument, products);
    }

    @Override
    public Order save(Order order) {
        var products = productMapper.toDocuments(order.getProducts());
        var orderDocument = orderMapper.toDocument(order, products);
        var savedOrderDocument = orderRepository.save(orderDocument);
        return mapToOrder(savedOrderDocument);
    }
}
