package tn.jibli.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tn.jibli.model.Order;
import tn.jibli.repository.OrderRepository;

@SpringBootTest
@AutoConfigureMockMvc
class Step34RecapTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void threeOrdersFromTwoRestaurants() {
        repository.deleteAll();
        repository.save(new Order("Mamma Chemi", "Plat Healthy", 25.0));
        repository.save(new Order("Mamma Chemi", "Pasta", 15.0));
        repository.save(new Order("Dar Lablabi", "Lablabi", 6.5));
    }

    @Test
    @DisplayName("GET /orders?restaurant=Mamma Chemi returns only its orders")
    void filtersByRestaurant() throws Exception {
        mockMvc.perform(get("/orders").param("restaurant", "Mamma Chemi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].restaurant").value("Mamma Chemi"))
                .andExpect(jsonPath("$[1].restaurant").value("Mamma Chemi"));
    }

    @Test
    @DisplayName("another restaurant gives its own orders")
    void filtersAnotherRestaurant() throws Exception {
        mockMvc.perform(get("/orders").param("restaurant", "Dar Lablabi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].product").value("Lablabi"));
    }

    @Test
    @DisplayName("an unknown restaurant gives an empty list")
    void unknownRestaurant() throws Exception {
        mockMvc.perform(get("/orders").param("restaurant", "Chapati Medina"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("without the parameter, all orders are returned")
    void noParameter() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
}
