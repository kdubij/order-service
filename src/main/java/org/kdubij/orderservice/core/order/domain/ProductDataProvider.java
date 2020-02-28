package org.kdubij.orderservice.core.order.domain;

import java.util.Optional;

public interface ProductDataProvider {

    Optional<Product> findProductById(String id);

}
