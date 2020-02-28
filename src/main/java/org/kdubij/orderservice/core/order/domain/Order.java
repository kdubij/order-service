package org.kdubij.orderservice.core.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;

    private String email;

    private Instant submittedAt;

    private List<Product> products;

    private Long totalPrice;

}
