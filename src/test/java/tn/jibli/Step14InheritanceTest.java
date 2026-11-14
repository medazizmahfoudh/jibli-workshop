package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step14InheritanceTest {

    @Test
    @DisplayName("standard delivery costs the standard fee")
    void standard() {
        assertThat(new Delivery().fee()).as("standard delivery").isEqualTo(4.0);
    }

    @Test
    @DisplayName("express delivery costs 7 TND")
    void express() {
        assertThat(new ExpressDelivery().fee()).as("express delivery").isEqualTo(7.0);
    }

    @Test
    @DisplayName("pickup is free")
    void pickup() {
        assertThat(new PickupDelivery().fee()).as("pickup").isEqualTo(0.0);
    }

    @Test
    @DisplayName("express and pickup are kinds of Delivery")
    void subclassesExtendDelivery() {
        assertThat(ExpressDelivery.class.getSuperclass()).as("parent of ExpressDelivery").isEqualTo(Delivery.class);
        assertThat(PickupDelivery.class.getSuperclass()).as("parent of PickupDelivery").isEqualTo(Delivery.class);
    }
}
