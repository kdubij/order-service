package org.kdubij.orderservice.core.order.infrastructure.entrypoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kdubij.orderservice.core.order.domain.Order;
import org.kdubij.orderservice.core.order.domain.OrderFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    Order submitOrder(@RequestBody @Valid CreateOrderRequest request) {
        log.info("[Order SUBMIT] START");
        var order = orderFacade.submitOrder(request.getEmail(), request.getProducts());
        log.info("[Order SUBMIT] END");
        return order;
    }

    @GetMapping
    List<Order> retrieveOrders(@RequestParam Instant from, @RequestParam Instant to) {
        log.info("[Order RETRIEVAL] START");
        List<Order> orders = orderFacade.retrieveOrders(from, to);
        log.info("[Order RETRIEVAL] END");
        return orders;
    }

}
