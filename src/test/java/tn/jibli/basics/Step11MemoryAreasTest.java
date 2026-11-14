package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step11MemoryAreasTest {

    @Test
    @DisplayName("sumTo adds the numbers from 1 to n")
    void sumTo() {
        assertThat(Recursion.sumTo(1)).isEqualTo(1);
        assertThat(Recursion.sumTo(4)).isEqualTo(10);
        assertThat(Recursion.sumTo(100)).as("1 + 2 + ... + 100").isEqualTo(5050);
    }

    @Test
    @DisplayName("sumTo stops at zero")
    void sumToStopsAtZero() {
        assertThat(Recursion.sumTo(0)).isEqualTo(0);
        assertThat(Recursion.sumTo(-3)).isEqualTo(0);
    }

    @Test
    @DisplayName("endless recursion ends in a StackOverflowError that we catch")
    void overflows() {
        assertThat(Recursion.overflows()).isTrue();
    }
}
