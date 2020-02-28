package org.kdubij.orderservice.core.order.infrastructure.repository.mapper;

import org.kdubij.orderservice.core.order.domain.Product;
import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDocument> toDocuments(List<Product> products);

    Product toProduct(ProductDocument productDocument);

    List<Product> toProducts(List<ProductDocument> products);
}
