package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step06MethodsTest {

    @Test
    @DisplayName("withTip adds the tip")
    void withTip() {
        assertThat(Receipt.withTip(25.0, 2.0)).isEqualTo(27.0);
    }

    @Test
    @DisplayName("a primitive argument is passed by value")
    void primitiveIsCopied() {
        double total = 25.0;
        Receipt.tryToChange(total);
        assertThat(total).as("the caller's variable must not change").isEqualTo(25.0);
    }

    @Test
    @DisplayName("an array argument shares the elements")
    void arrayContentChanges() {
        double[] prices = {10.0, 20.0};
        Receipt.applyDiscountInPlace(prices, 50);
        assertThat(prices[0]).as("first price after 50% off").isCloseTo(5.0, within(0.0001));
        assertThat(prices[1]).as("second price after 50% off").isCloseTo(10.0, within(0.0001));
    }
}
