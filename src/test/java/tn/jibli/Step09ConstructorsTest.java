package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step09ConstructorsTest {

    @Test
    @DisplayName("the constructor stores the three values")
    void constructorStoresValues() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        assertThat(order.getRestaurant()).isEqualTo("Mamma Chemi");
        assertThat(order.getProduct()).isEqualTo("Plat Healthy");
        assertThat(order.getTotal()).as("total").isEqualTo(25.0);
    }

    @Test
    @DisplayName("two orders built with different values stay different")
    void twoOrders() {
        Order first = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        Order second = new Order("Dar Lablabi", "Lablabi", 6.5);
        assertThat(first.getRestaurant()).isEqualTo("Mamma Chemi");
        assertThat(second.getRestaurant()).isEqualTo("Dar Lablabi");
        assertThat(second.getTotal()).isEqualTo(6.5);
    }

    @Test
    @DisplayName("audit shows what the constructor stored")
    void auditAfterConstruction() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 12.5);
        assertThat(order.audit()).isEqualTo("audit: Mamma Chemi/Plat Healthy/12.5");
    }
}
