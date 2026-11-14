package tn.jibli.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Step29DiContextTest {

    @Autowired
    private OrderService service;

    @Test
    @DisplayName("Spring still builds the service through its constructor")
    void springBuildsTheService() {
        assertThat(service).isNotNull();
    }
}
