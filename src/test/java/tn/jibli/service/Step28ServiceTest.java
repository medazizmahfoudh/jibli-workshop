package tn.jibli.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

@SpringBootTest
class Step28ServiceTest {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void emptyTable() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("a valid order is saved")
    void validOrderIsSaved() {
        Order saved = service.place(new Order("Mamma Chemi", "Plat Healthy", 25.0));

        assertThat(saved.getId()).as("the saved order has an id").isNotNull();
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("a total of 0 is refused")
    void zeroTotalIsRefused() {
        assertThatThrownBy(() -> service.place(new Order("Mamma Chemi", "Plat Healthy", 0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(repository.count()).as("nothing was saved").isEqualTo(0);
    }

    @Test
    @DisplayName("an order at Chapati Medina is refused, the restaurant is closed")
    void closedRestaurantIsRefused() {
        assertThatThrownBy(() -> service.place(new Order("Chapati Medina", "Chapati", 6.0)))
                .isInstanceOf(RestaurantClosedException.class)
                .hasMessage("Restaurant closed: Chapati Medina");
        assertThat(repository.count()).as("nothing was saved").isEqualTo(0);
    }

    @Test
    @DisplayName("the other restaurants are open")
    void otherRestaurantsAreOpen() {
        service.place(new Order("Dar Lablabi", "Lablabi", 6.5));
        assertThat(repository.count()).isEqualTo(1);
    }
}
