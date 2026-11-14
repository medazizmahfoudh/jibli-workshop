package tn.jibli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.jibli.model.Order;

// only an interface: Spring Data builds the implementation when the app starts
public interface OrderRepository extends JpaRepository<Order, Long> {

    // TODO(step-34): a derived query that finds the orders of one restaurant
}
