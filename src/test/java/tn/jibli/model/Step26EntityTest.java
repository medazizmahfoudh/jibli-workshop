package tn.jibli.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJpaTest
class Step26EntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private Environment environment;

    @BeforeEach
    void resetCounter() {
        Order.resetCounter();
    }

    @Test
    @DisplayName("an order can be saved and read back")
    void persistAndFind() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        entityManager.persist(order);
        entityManager.flush();
        entityManager.clear();

        assertThat(order.getId()).as("the id is generated").isNotNull();
        Order found = entityManager.find(Order.class, order.getId());
        assertThat(found.getRestaurant()).isEqualTo("Mamma Chemi");
        assertThat(found.getProduct()).isEqualTo("Plat Healthy");
        assertThat(found.getTotal()).isEqualTo(25.0);
    }

    @Test
    @DisplayName("the row is in the table called orders")
    void tableName() {
        entityManager.persist(new Order("Dar Lablabi", "Lablabi", 6.5));
        entityManager.flush();

        Integer rows = jdbc.queryForObject("select count(*) from orders", Integer.class);
        assertThat(rows).isEqualTo(1);
    }

    @Test
    @DisplayName("JPA building an order from a row does not count as a new order")
    void jpaDoesNotCount() {
        Order order = new Order("Mamma Chemi", "Plat Healthy", 25.0);
        entityManager.persist(order);
        entityManager.flush();
        entityManager.clear();
        entityManager.find(Order.class, order.getId());

        assertThat(Order.getCreatedCount()).as("only the order we created ourselves").isEqualTo(1);
    }

    @Test
    @DisplayName("application.properties configures H2, the SQL log and the console")
    void properties() {
        assertThat(environment.getProperty("spring.datasource.url")).startsWith("jdbc:h2:mem:");
        assertThat(environment.getProperty("spring.jpa.show-sql")).isEqualTo("true");
        assertThat(environment.getProperty("spring.h2.console.enabled")).isEqualTo("true");
    }
}
