package org.kdubij.orderservice.core.order.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.kdubij.orderservice.core.order.domain.Product;
import org.kdubij.orderservice.core.order.domain.ProductDataProvider;
import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductDataProviderAdapter implements ProductDataProvider {

    private final ProductDataRepository repository;

    private final ProductMapper productMapper;

    @Override
    public Optional<Product> findProductById(String id) {
        return repository.findById(id).map(productMapper::toProduct);
    }

}
