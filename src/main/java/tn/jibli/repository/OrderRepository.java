package tn.jibli.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.jibli.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // a derived query: Spring Data reads the method name and writes the SQL
    List<Order> findByRestaurant(String restaurant);
}
