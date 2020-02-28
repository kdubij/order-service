package org.kdubij.orderservice.core.order.infrastructure.configuration;

import org.kdubij.orderservice.core.order.domain.OrderDataProvider;
import org.kdubij.orderservice.core.order.domain.OrderFacade;
import org.kdubij.orderservice.core.order.domain.ProductDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

    @Bean
    OrderFacade orderFacade(OrderDataProvider orderDataProvider, ProductDataProvider productDataProvider) {
        return new OrderFacade(orderDataProvider, productDataProvider);
    }

}
