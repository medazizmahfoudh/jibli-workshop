package tn.jibli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

@Service
public class OrderService {

    // Spring fills this field in. We will look at a better way in the next step.
    @Autowired
    private OrderRepository repo;

    public Order place(Order order) {
        // TODO(step-28): reject a total <= 0 with IllegalArgumentException,
        // reject the restaurant "Chapati Medina" with RestaurantClosedException, otherwise save the order
        throw new UnsupportedOperationException("TODO(step-28)");
    }
}
