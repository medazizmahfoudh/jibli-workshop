package tn.jibli.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

// no Spring here: the repository is a Mockito fake
class Step29DiTest {

    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void build() {
        repository = mock(OrderRepository.class);
        service = new OrderService(repository);
    }

    @Test
    @DisplayName("a valid order goes to the repository")
    void validOrderIsSaved() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        when(repository.save(order)).thenReturn(order);

        assertThat(service.place(order)).isSameAs(order);
        verify(repository).save(order);
    }

    @Test
    @DisplayName("an order at a closed restaurant never reaches the repository")
    void closedRestaurantIsNotSaved() {
        Order order = new Order("Chapati Medina", "Chapati", 6.0);

        assertThatThrownBy(() -> service.place(order)).isInstanceOf(RestaurantClosedException.class);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("an order with a total of 0 never reaches the repository")
    void zeroTotalIsNotSaved() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 0);

        assertThatThrownBy(() -> service.place(order)).isInstanceOf(IllegalArgumentException.class);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("the repository field is final and not injected by field")
    void fieldIsFinal() {
        Field[] fields = OrderService.class.getDeclaredFields();
        Field repo = Arrays.stream(fields).filter(f -> f.getType() == OrderRepository.class).findFirst()
                .orElseThrow(() -> new AssertionError("no field of type OrderRepository"));

        assertThat(Modifier.isFinal(repo.getModifiers())).as("the repository field should be final").isTrue();
        assertThat(Arrays.stream(fields).noneMatch(f -> f.isAnnotationPresent(Autowired.class)))
                .as("no field should use @Autowired").isTrue();
    }
}
