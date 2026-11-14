package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Step21FrameworkTest {

    @Autowired
    private WelcomeMessage message;

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("Spring creates the WelcomeMessage and injects it")
    void springCreatesTheBean() {
        assertThat(message).as("WelcomeMessage injected by Spring").isNotNull();
        assertThat(message.text()).isEqualTo("Welcome to Jibli");
    }

    @Test
    @DisplayName("there is exactly one WelcomeMessage, shared by everyone")
    void oneSharedInstance() {
        assertThat(context.getBeansOfType(WelcomeMessage.class)).hasSize(1);
        assertThat(context.getBean(WelcomeMessage.class)).isSameAs(message);
    }
}
