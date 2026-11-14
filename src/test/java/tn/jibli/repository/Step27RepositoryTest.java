package tn.jibli.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.jibli.model.Order;

@DataJpaTest
class Step27RepositoryTest {

    @Autowired
    private ApplicationContext context;

    // the test asks Spring for the repository bean, so it compiles even before you write the interface
    @SuppressWarnings("unchecked")
    private JpaRepository<Order, Long> repository() {
        return (JpaRepository<Order, Long>) context.getBean(OrderRepository.class);
    }

    @Test
    @DisplayName("save gives the order an id")
    void save() {
        Order saved = repository().save(new Order("Mamma Chemi", "Plat Healthy", 25.0));
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @DisplayName("findAll returns what was saved")
    void findAll() {
        repository().save(new Order("Mamma Chemi", "Plat Healthy", 25.0));
        repository().save(new Order("Dar Lablabi", "Lablabi", 6.5));
        assertThat(repository().findAll()).extracting(Order::getRestaurant)
                .containsExactlyInAnyOrder("Mamma Chemi", "Dar Lablabi");
    }

    @Test
    @DisplayName("findById returns an Optional")
    void findById() {
        Order saved = repository().save(new Order("Mamma Chemi", "Plat Healthy", 25.0));

        Optional<Order> found = repository().findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTotal()).isEqualTo(25.0);
        assertThat(repository().findById(9999L)).as("unknown id").isEmpty();
    }

    @Test
    @DisplayName("deleteById removes the order")
    void deleteById() {
        Order saved = repository().save(new Order("Mamma Chemi", "Plat Healthy", 25.0));
        repository().deleteById(saved.getId());
        assertThat(repository().findById(saved.getId())).isEmpty();
    }
}
