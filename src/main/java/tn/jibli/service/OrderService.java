package tn.jibli.service;

import java.util.Set;
import org.springframework.stereotype.Service;
import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

@Service
public class OrderService {

    private static final Set<String> CLOSED_RESTAURANTS = Set.of("Chapati Medina");

    private final OrderRepository repo;

    // constructor injection: Spring passes the repository in, a test can pass a fake
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

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
