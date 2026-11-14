package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step12EncapsulationTest {

    private Order validOrder() {
        return new Order("Mamma Chemi", "Plat Healthy", 25.0);
    }

    @Test
    @DisplayName("the constructor rejects a negative total")
    void constructorRejectsNegativeTotal() {
        assertThatThrownBy(() -> new Order("Mamma Chemi", "Plat Healthy", -50))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("the constructor rejects a blank restaurant or product")
    void constructorRejectsBlankText() {
        assertThatThrownBy(() -> new Order(" ", "Plat Healthy", 25.0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Order("Mamma Chemi", null, 25.0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("setTotal accepts a good value and rejects a negative one")
    void setTotal() {
        Order order = validOrder();
        order.setTotal(30.0);
        assertThat(order.getTotal()).isEqualTo(30.0);

        assertThatThrownBy(() -> order.setTotal(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThat(order.getTotal()).as("a refused value must not be stored").isEqualTo(30.0);
    }

    @Test
    @DisplayName("setRestaurant and setProduct reject null and blank")
    void textSetters() {
        Order order = validOrder();
        order.setRestaurant("Dar Lablabi");
        order.setProduct("Lablabi");
        assertThat(order.getRestaurant()).isEqualTo("Dar Lablabi");
        assertThat(order.getProduct()).isEqualTo("Lablabi");

        assertThatThrownBy(() -> order.setRestaurant(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> order.setRestaurant("   ")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> order.setProduct("")).isInstanceOf(IllegalArgumentException.class);
        assertThat(order.getRestaurant()).as("still the last valid value").isEqualTo("Dar Lablabi");
    }
}
