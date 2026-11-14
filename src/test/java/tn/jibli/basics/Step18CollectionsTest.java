package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.jibli.model.Order;
import tn.jibli.model.Restaurant;

class Step18CollectionsTest {

    private OrderBook book;

    @BeforeEach
    void freshBook() {
        book = new OrderBook();
        book.add(new Order("Mamma Chemi", "Plat Healthy", 25.0));
        book.add(new Order("Dar Lablabi", "Lablabi", 48.5));
        book.add(new Order("Mamma Chemi", "Pasta", 15.0));
    }

    @Test
    @DisplayName("add and size")
    void addAndSize() {
        assertThat(book.size()).isEqualTo(3);
        book.add(new Order("Chapati Medina", "Chapati", 6.0));
        assertThat(book.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("orders() returns the orders in the order they were added")
    void ordersInOrder() {
        assertThat(book.orders()).extracting(Order::getProduct).containsExactly("Plat Healthy", "Lablabi", "Pasta");
    }

    @Test
    @DisplayName("orders() cannot be modified from outside")
    void ordersIsReadOnly() {
        assertThat(book.orders()).as("the list must have content first").hasSize(3);
        assertThatThrownBy(() -> book.orders().add(new Order("Mamma Chemi", "Plat Healthy", 25.0)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("revenue adds up the totals")
    void revenue() {
        assertThat(book.revenue()).as("25.0 + 48.5 + 15.0").isEqualTo(88.5);
        assertThat(new OrderBook().revenue()).as("empty book").isEqualTo(0.0);
    }

    @Test
    @DisplayName("ordersFrom keeps only one restaurant")
    void ordersFrom() {
        assertThat(book.ordersFrom("Mamma Chemi")).hasSize(2);
        assertThat(book.ordersFrom("Dar Lablabi")).hasSize(1);
        assertThat(book.ordersFrom("Chapati Medina")).isEmpty();
    }

    @Test
    @DisplayName("restaurants are found by id")
    void restaurants() {
        Restaurant mammaChemi = new Restaurant(1, "Mamma Chemi", true);
        Restaurant darLablabi = new Restaurant(2, "Dar Lablabi", true);
        book.addRestaurant(mammaChemi);
        book.addRestaurant(darLablabi);

        assertThat(book.findRestaurant(1)).isSameAs(mammaChemi);
        assertThat(book.findRestaurant(2)).isSameAs(darLablabi);
        assertThat(book.findRestaurant(99)).as("unknown id").isNull();
    }
}
