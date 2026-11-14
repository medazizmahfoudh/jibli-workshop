package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.model.Restaurant;

class Step19ExceptionsTest {

    private final Restaurant open = new Restaurant(1, "Mamma Chemi", true);
    private final Restaurant closed = new Restaurant(3, "Chapati Medina", false);

    @Test
    @DisplayName("the exception is unchecked and carries the restaurant name")
    void exceptionMessage() {
        RestaurantClosedException e = new RestaurantClosedException("Chapati Medina");
        assertThat(e).isInstanceOf(RuntimeException.class);
        assertThat(e.getMessage()).isEqualTo("Restaurant closed: Chapati Medina");
    }

    @Test
    @DisplayName("placeOrder returns an order for an open restaurant")
    void placeOrderOpen() {
        Order order = OrderPlacer.placeOrder(open, "Plat Healthy", 25.0);
        assertThat(order.getRestaurant()).isEqualTo("Mamma Chemi");
        assertThat(order.getProduct()).isEqualTo("Plat Healthy");
        assertThat(order.getTotal()).isEqualTo(25.0);
    }

    @Test
    @DisplayName("placeOrder throws for a closed restaurant")
    void placeOrderClosed() {
        assertThatThrownBy(() -> OrderPlacer.placeOrder(closed, "Chapati", 6.0))
                .isInstanceOf(RestaurantClosedException.class)
                .hasMessage("Restaurant closed: Chapati Medina");
    }

    @Test
    @DisplayName("tryPlace turns the exception into a message")
    void tryPlace() {
        assertThat(OrderPlacer.tryPlace(open, "Plat Healthy", 25.0)).isEqualTo("Order accepted");
        assertThat(OrderPlacer.tryPlace(closed, "Chapati", 6.0)).isEqualTo("Restaurant closed");
    }
}
