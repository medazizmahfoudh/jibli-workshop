package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.annotation.Annotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@AutoConfigureMockMvc
class Step25LayersTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private Class<?> load(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new AssertionError(className + " was not found, check the package and the name");
        }
    }

    private void assertLayer(String className, Class<? extends Annotation> annotation) {
        Class<?> type = load(className);
        assertThat(type.isAnnotationPresent(annotation))
                .as(className + " should have @" + annotation.getSimpleName()).isTrue();
        assertThat(context.getBean(type)).as("Spring should have created " + className).isNotNull();
    }

    @Test
    @DisplayName("HelloController lives in the controller package")
    void helloController() {
        assertLayer("tn.jibli.controller.HelloController", RestController.class);
    }

    @Test
    @DisplayName("OrderController, OrderService and OrderRepository exist in their layers")
    void layers() {
        assertLayer("tn.jibli.controller.OrderController", RestController.class);
        assertLayer("tn.jibli.service.OrderService", Service.class);
        // no annotation check here: in step 27 this class becomes an interface that Spring Data implements
        assertThat(context.getBean(load("tn.jibli.repository.OrderRepository")))
                .as("Spring should have created OrderRepository").isNotNull();
    }

    @Test
    @DisplayName("/hello still answers after the move")
    void helloStillWorks() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello from Jibli!"));
    }
}
