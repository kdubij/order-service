package org.kdubij.orderservice.core.crud.product;

import lombok.Builder;
import org.kdubij.orderservice.core.order.domain.Product;
import org.kdubij.orderservice.core.order.infrastructure.repository.ProductDocument;
import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.ProductMapper;
import org.kdubij.orderservice.exception.ResourceNotFoundException;

import java.util.List;

@Builder
class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    List<Product> getProducts() {
        return productMapper.toProducts(productRepository.findAll());
    }

    Product create(CreateProductRequest request) {
        return productMapper.toProduct(productRepository.insert(ProductDocument.builder()
                .name(request.getName())
                .price(Long.valueOf(request.getPrice()))
                .build()));
    }

    Product update(UpdateProductRequest productRequest, String productId) {
        ProductDocument product = getProductById(productId);
        if (productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }
        if (productRequest.getPrice() != null) {
            product.setPrice(Long.valueOf(productRequest.getPrice()));
        }
        return productMapper.toProduct(productRepository.save(product));
    }

    private ProductDocument getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with given id not exists {id=%s}", id)));
    }

}
