package org.kdubij.orderservice.core.crud.product;

import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductInjector {

    @Bean
    ProductService productService(ProductRepository productRepository, ProductMapper productMapper) {
        return ProductService.builder().productRepository(productRepository).productMapper(productMapper).build();
    }

}
