package tn.jibli.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

@Service
public class OrderService {

    // for the workshop, the closed restaurants are a fixed list
    private static final Set<String> CLOSED_RESTAURANTS = Set.of("Chapati Medina");

    // field injection: Spring fills this in. We will look at a better way in the next step.
    @Autowired
    private OrderRepository repo;

    public Order place(Order order) {
        checkRules(order);
        return repo.save(order);
    }

    private void checkRules(Order order) {
        if (order.getTotal() <= 0) {
            throw new IllegalArgumentException("total must be greater than 0");
        }
        if (CLOSED_RESTAURANTS.contains(order.getRestaurant())) {
            throw new RestaurantClosedException(order.getRestaurant());
        }
    }
}
