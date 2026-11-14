package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step07ClassesTest {

    @Test
    @DisplayName("an object stores its own values")
    void fieldsStoreValues() {
        Order order = new Order();
        order.restaurant = "Mamma Chemi";
        order.product = "Plat Healthy";
        order.total = 25.0;

        assertThat(order.restaurant).isEqualTo("Mamma Chemi");
        assertThat(order.product).isEqualTo("Plat Healthy");
        assertThat(order.total).as("total of Dhia's order").isEqualTo(25.0);
    }

    @Test
    @DisplayName("two objects are independent")
    void objectsAreIndependent() {
        Order first = new Order();
        first.restaurant = "Mamma Chemi";
        first.total = 25.0;

        Order second = new Order();
        second.restaurant = "Dar Lablabi";
        second.total = 48.5;

        assertThat(first.restaurant).isEqualTo("Mamma Chemi");
        assertThat(first.total).isEqualTo(25.0);
        assertThat(second.restaurant).isEqualTo("Dar Lablabi");
        assertThat(second.total).isEqualTo(48.5);
    }
}
