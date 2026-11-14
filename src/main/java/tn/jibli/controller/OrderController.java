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

    // POST /orders, the JSON body becomes an Order
    @PostMapping
    public Order create(@RequestBody Order order) {
        return service.place(order);
    }
}
