package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step03OperatorsTest {

    @Test
    @DisplayName("total adds the delivery fee")
    void total() {
        assertThat(Calc.total(25.0, 4.0)).as("25.0 + 4.0 delivery").isEqualTo(29.0);
    }

    @Test
    @DisplayName("applyDiscount takes a percentage off")
    void applyDiscount() {
        assertThat(Calc.applyDiscount(40.0, 10)).as("10% off 40.0").isCloseTo(36.0, within(0.0001));
        assertThat(Calc.applyDiscount(25.0, 20)).as("20% off 25.0").isCloseTo(20.0, within(0.0001));
        assertThat(Calc.applyDiscount(25.0, 0)).as("0% off").isCloseTo(25.0, within(0.0001));
    }

    @Test
    @DisplayName("dinars cuts the decimals")
    void dinars() {
        assertThat(Calc.dinars(12.9)).as("12.9 TND").isEqualTo(12);
        assertThat(Calc.dinars(25.0)).isEqualTo(25);
    }

    @Test
    @DisplayName("averageItems keeps the decimals")
    void averageItems() {
        assertThat(Calc.averageItems(7, 2)).as("7 items in 2 orders").isEqualTo(3.5);
        assertThat(Calc.averageItems(6, 3)).isEqualTo(2.0);
    }

    @Test
    @DisplayName("every fifth order is a loyalty order")
    void loyalty() {
        assertThat(Calc.isLoyaltyOrder(5)).isTrue();
        assertThat(Calc.isLoyaltyOrder(10)).isTrue();
        assertThat(Calc.isLoyaltyOrder(7)).isFalse();
        assertThat(Calc.isLoyaltyOrder(11)).isFalse();
    }
}
