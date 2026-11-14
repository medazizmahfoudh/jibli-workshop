package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step05LoopsTest {

    @Test
    @DisplayName("sum adds every price")
    void sum() {
        assertThat(Cart.sum(new double[] {12.5, 3.5, 5.0})).as("cart of three items").isEqualTo(21.0);
        assertThat(Cart.sum(new double[] {})).as("empty cart").isEqualTo(0.0);
    }

    @Test
    @DisplayName("mostExpensive finds the biggest price")
    void mostExpensive() {
        assertThat(Cart.mostExpensive(new double[] {12.5, 3.5, 5.0})).isEqualTo(12.5);
        assertThat(Cart.mostExpensive(new double[] {3.5, 18.0, 5.0})).isEqualTo(18.0);
        assertThat(Cart.mostExpensive(new double[] {})).as("empty cart").isEqualTo(0.0);
    }

    @Test
    @DisplayName("indexOf returns the position or -1")
    void indexOf() {
        String[] names = {"Plat Healthy", "Lablabi", "Chapati"};
        assertThat(Cart.indexOf(names, "Plat Healthy")).isEqualTo(0);
        assertThat(Cart.indexOf(names, "Chapati")).isEqualTo(2);
        assertThat(Cart.indexOf(names, "Couscous")).as("not in the cart").isEqualTo(-1);
        assertThat(Cart.indexOf(new String[] {}, "Lablabi")).isEqualTo(-1);
    }

    @Test
    @DisplayName("indexOf compares text, not references")
    void indexOfUsesEquals() {
        String[] names = {"Plat Healthy", "Lablabi"};
        String typedByTheUser = new String("Lablabi");
        assertThat(Cart.indexOf(names, typedByTheUser)).isEqualTo(1);
    }
}
