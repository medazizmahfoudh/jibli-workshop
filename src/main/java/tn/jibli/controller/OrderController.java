package tn.jibli.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.jibli.model.Order;
import tn.jibli.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // TODO(step-31): POST must answer 201 Created
    @PostMapping
    public Order create(@RequestBody Order order) {
        return service.place(order);
    }

    // TODO(step-31): GET /orders, GET /orders/{id} (404 if missing), PUT /orders/{id} (404 if missing),
    // DELETE /orders/{id} (204, or 404 if missing)
}
