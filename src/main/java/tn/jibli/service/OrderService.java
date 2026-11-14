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
        return repo.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repo.findById(id);
    }

    public Optional<Order> update(Long id, Order newData) {
        Optional<Order> existing = repo.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        checkRules(newData);
        Order order = existing.get();
        order.setRestaurant(newData.getRestaurant());
        order.setProduct(newData.getProduct());
        order.setTotal(newData.getTotal());
        return Optional.of(repo.save(order));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
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
