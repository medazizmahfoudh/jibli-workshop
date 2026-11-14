package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step08DefaultsTest {

    @Test
    @DisplayName("a new order starts empty")
    void newOrderHasDefaults() {
        Order order = new Order();
        assertThat(order.getRestaurant()).isNull();
        assertThat(order.getProduct()).isNull();
        assertThat(order.getTotal()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("audit prints the three values")
    void auditOfAnEmptyOrder() {
        assertThat(new Order().audit()).isEqualTo("audit: null/null/0.0");
    }
}
