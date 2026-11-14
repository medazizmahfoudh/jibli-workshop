package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step02VariablesTest {

    @Test
    @DisplayName("subtotal multiplies quantity by unit price")
    void subtotal() {
        assertThat(Basics.subtotal(2, 12.5)).as("Plat Healthy x2 should cost 25.0 TND").isEqualTo(25.0);
        assertThat(Basics.subtotal(1, 4.0)).as("one item at 4.0 TND").isEqualTo(4.0);
        assertThat(Basics.subtotal(0, 12.5)).as("nothing ordered").isEqualTo(0.0);
    }

    @Test
    @DisplayName("describeOrder builds a readable line")
    void describeOrder() {
        assertThat(Basics.describeOrder("Plat Healthy", 2, 12.5)).isEqualTo("2 x Plat Healthy = 25.0 TND");
        assertThat(Basics.describeOrder("Lablabi", 3, 3.5)).isEqualTo("3 x Lablabi = 10.5 TND");
    }
}
