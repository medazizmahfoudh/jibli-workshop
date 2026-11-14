package tn.jibli.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

@Service
public class OrderService {

    private static final Set<String> CLOSED_RESTAURANTS = Set.of("Chapati Medina");

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Order place(Order order) {
        checkRules(order);
        return repo.save(order);
    }

    public List<Order> findAll() {
        // TODO(step-31)
        throw new UnsupportedOperationException("TODO(step-31)");
    }

    public Optional<Order> findById(Long id) {
        // TODO(step-31)
        throw new UnsupportedOperationException("TODO(step-31)");
    }

    public Optional<Order> update(Long id, Order newData) {
        // TODO(step-31): empty when the id does not exist, otherwise copy the fields, check the rules and save
        throw new UnsupportedOperationException("TODO(step-31)");
    }

    public boolean delete(Long id) {
        // TODO(step-31): false when the id does not exist
        throw new UnsupportedOperationException("TODO(step-31)");
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
