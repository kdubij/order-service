package org.kdubij.orderservice.core.order.infrastructure.repository.mapper;

import org.kdubij.orderservice.core.order.domain.Order;
import org.kdubij.orderservice.core.order.domain.Product;
import org.kdubij.orderservice.core.order.infrastructure.repository.OrderDocument;
import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    abstract public OrderDocument toDocument(Order order, List<ProductDocument> products);

    @Mapping(target = "totalPrice", expression = "java(countTotalPrice(products))")
    public abstract Order toOrder(OrderDocument orderDocument, List<Product> products);

    Long countTotalPrice(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(0L, (a, b) -> a + b);
    }
}
