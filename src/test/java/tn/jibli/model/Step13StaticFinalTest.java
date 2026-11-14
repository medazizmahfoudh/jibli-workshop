package tn.jibli.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step13StaticFinalTest {

    @BeforeEach
    void resetTheSharedCounter() {
        Order.resetCounter();
    }

    @Test
    @DisplayName("DELIVERY_FEE is a public static final constant worth 4.0")
    void deliveryFeeIsAConstant() throws Exception {
        Field field = Order.class.getDeclaredField("DELIVERY_FEE");
        assertThat(Modifier.isStatic(field.getModifiers())).as("DELIVERY_FEE should be static").isTrue();
        assertThat(Modifier.isFinal(field.getModifiers())).as("DELIVERY_FEE should be final").isTrue();
        assertThat(Modifier.isPublic(field.getModifiers())).as("DELIVERY_FEE should be public").isTrue();
        assertThat(Order.DELIVERY_FEE).isEqualTo(4.0);
    }

    @Test
    @DisplayName("the counter counts the orders that were created")
    void counterCountsOrders() {
        assertThat(Order.getCreatedCount()).as("before any order").isEqualTo(0);
        new Order("Mamma Chemi", "Plat Healthy", 25.0);
        new Order("Dar Lablabi", "Lablabi", 6.5);
        new Order("Chapati Medina", "Chapati", 15.0);
        assertThat(Order.getCreatedCount()).as("after three orders").isEqualTo(3);
    }

    @Test
    @DisplayName("an order that fails validation is not counted")
    void invalidOrderIsNotCounted() {
        assertThatThrownBy(() -> new Order("Mamma Chemi", "Plat Healthy", -5)).isInstanceOf(IllegalArgumentException.class);
        assertThat(Order.getCreatedCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("the counter is shared, not stored in each order")
    void counterIsStatic() throws Exception {
        Field field = Order.class.getDeclaredField("createdCount");
        assertThat(Modifier.isStatic(field.getModifiers())).as("createdCount should be static").isTrue();
    }

    @Test
    @DisplayName("totalWithFee adds the delivery fee")
    void totalWithFee() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        assertThat(order.totalWithFee()).as("25.0 + 4.0 delivery").isEqualTo(29.0);
    }
}
