package tn.jibli.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.jibli.model.Order;
import tn.jibli.service.OrderService;

// only the web layer is started, the service is replaced by a fake
@WebMvcTest(OrderController.class)
class Step30ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService service;

    @Test
    @DisplayName("POST /orders places the order and returns it as JSON")
    void postPlacesTheOrder() throws Exception {
        when(service.place(any(Order.class))).thenAnswer(call -> call.getArgument(0));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"restaurant": "Mamma Chemi", "product": "Plat Healthy", "total": 25.0}
                                """))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.restaurant").value("Mamma Chemi"))
                .andExpect(jsonPath("$.product").value("Plat Healthy"))
                .andExpect(jsonPath("$.total").value(25.0));
    }
}
