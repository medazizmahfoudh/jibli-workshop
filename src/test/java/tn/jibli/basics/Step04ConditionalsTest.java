package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step04ConditionalsTest {

    @Test
    @DisplayName("pickup is always free")
    void pickup() {
        assertThat(DeliveryFee.fee(10.0, "pickup")).isEqualTo(0.0);
        assertThat(DeliveryFee.fee(100.0, "pickup")).isEqualTo(0.0);
    }

    @Test
    @DisplayName("express costs 7 TND, even for big orders")
    void express() {
        assertThat(DeliveryFee.fee(10.0, "express")).isEqualTo(7.0);
        assertThat(DeliveryFee.fee(100.0, "express")).isEqualTo(7.0);
    }

    @Test
    @DisplayName("standard costs 4 TND below 40 TND")
    void standardBelowThreshold() {
        assertThat(DeliveryFee.fee(29.0, "standard")).as("order of 29.0 TND").isEqualTo(4.0);
        assertThat(DeliveryFee.fee(39.99, "standard")).as("just below the threshold").isEqualTo(4.0);
    }

    @Test
    @DisplayName("standard is free from 40 TND")
    void standardFree() {
        assertThat(DeliveryFee.fee(40.0, "standard")).as("exactly 40.0 TND").isEqualTo(0.0);
        assertThat(DeliveryFee.fee(48.5, "standard")).as("order of 48.5 TND").isEqualTo(0.0);
    }

    @Test
    @DisplayName("an unknown mode is rejected")
    void unknownMode() {
        assertThatThrownBy(() -> DeliveryFee.fee(20.0, "drone")).isInstanceOf(IllegalArgumentException.class);
    }
}
