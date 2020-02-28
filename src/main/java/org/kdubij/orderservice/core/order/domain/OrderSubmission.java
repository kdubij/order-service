package org.kdubij.orderservice.core.order.domain;

import lombok.AllArgsConstructor;
import org.kdubij.orderservice.exception.ResourceNotFoundException;
import org.kdubij.orderservice.exception.ValidationException;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
class OrderSubmission {

    private OrderDataProvider orderDataProvider;

    private ProductDataProvider productDataProvider;

    Order submit(String email, List<String> productIds) {
        validateEmail(email);
        validateProductIds(productIds);

        //TODO find products by list of ids with logic finding duplicates and missing products to avoid multiple db request
        var products = productIds.stream()
                .map(this::getProduct)
                .collect(Collectors.toList());

        Instant now = Instant.now();
        var order = Order.builder()
                .email(email)
                .products(products)
                .submittedAt(now)
                .build();
        return orderDataProvider.save(order);
    }

    private Product getProduct(String id) {
        return productDataProvider.findProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with given id not exists {productId=%s}", id)));
    }

    private void validateProductIds(List<String> productIds) {
        if (productIds.isEmpty()) {
            throw new ValidationException("Product list must be not empty");
        }
    }

    private void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new ValidationException("Email should be provided");
        }
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidationException("Email is not correct");
        }
    }
}
