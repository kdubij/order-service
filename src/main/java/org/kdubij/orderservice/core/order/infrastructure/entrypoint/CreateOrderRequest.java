package org.kdubij.orderservice.core.order.infrastructure.entrypoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CreateOrderRequest {

    private String email;

    private List<String> products;

}
