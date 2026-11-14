package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step10MemoryTest {

    @Test
    @DisplayName("a copy is a new object with the same values")
    void copyIsAnotherObject() {
        Order original = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        Order copy = MemoryLab.copyOf(original);

        assertThat(copy).as("the copy must be a new object").isNotSameAs(original);
        assertThat(copy.getRestaurant()).isEqualTo("Mamma Chemi");
        assertThat(copy.getProduct()).isEqualTo("Plat Healthy");
        assertThat(copy.getTotal()).isEqualTo(25.0);
    }

    @Test
    @DisplayName("sameObject compares references")
    void sameObjectComparesReferences() {
        Order a = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        Order alias = a;
        Order lookAlike = new Order("Mamma Chemi", "Plat Healthy", 25.0);

        assertThat(MemoryLab.sameObject(a, alias)).as("two names for one object").isTrue();
        assertThat(MemoryLab.sameObject(a, lookAlike)).as("two different objects").isFalse();
    }

    @Test
    @DisplayName("sameValues compares the content")
    void sameValuesComparesContent() {
        Order a = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        Order b = new Order(new String("Mamma Chemi"), "Plat Healthy", 25.0);
        Order otherTotal = new Order("Mamma Chemi", "Plat Healthy", 12.5);
        Order otherRestaurant = new Order("Dar Lablabi", "Plat Healthy", 25.0);

        assertThat(MemoryLab.sameValues(a, b)).as("same values, strings built differently").isTrue();
        assertThat(MemoryLab.sameValues(a, otherTotal)).as("different total").isFalse();
        assertThat(MemoryLab.sameValues(a, otherRestaurant)).as("different restaurant").isFalse();
    }

    @Test
    @DisplayName("two equal strings are not always the same object")
    void stringsAndReferences() {
        String literal = "Mamma Chemi";
        String built = new String("Mamma Chemi");
        assertThat(built).isEqualTo(literal);
        assertThat(built).isNotSameAs(literal);
    }
}
