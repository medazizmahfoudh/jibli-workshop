package tn.jibli.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.jibli.repository.OrderRepository;

// the whole application with the real H2 database, called through MockMvc
@SpringBootTest
@AutoConfigureMockMvc
class Step31RestTest {

    private static final String PLAT_HEALTHY = """
            {"restaurant": "Mamma Chemi", "product": "Plat Healthy", "total": 25.0}
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void emptyTable() {
        repository.deleteAll();
    }

    private long createOrder() throws Exception {
        String body = mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(PLAT_HEALTHY))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.parse(body).read("$.id", Long.class);
    }

    @Test
    @DisplayName("POST /orders answers 201 with the saved order")
    void create() throws Exception {
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(PLAT_HEALTHY))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.restaurant").value("Mamma Chemi"))
                .andExpect(jsonPath("$.total").value(25.0));
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("GET /orders lists the orders")
    void list() throws Exception {
        createOrder();
        createOrder();
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].product").value("Plat Healthy"));
    }

    @Test
    @DisplayName("GET /orders/{id} returns one order")
    void getOne() throws Exception {
        long id = createOrder();
        mockMvc.perform(get("/orders/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.restaurant").value("Mamma Chemi"));
    }

    @Test
    @DisplayName("GET /orders/{id} answers 404 for an unknown id")
    void getUnknown() throws Exception {
        mockMvc.perform(get("/orders/999999")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /orders/{id} updates the order")
    void update() throws Exception {
        long id = createOrder();
        mockMvc.perform(put("/orders/" + id).contentType(MediaType.APPLICATION_JSON).content("""
                        {"restaurant": "Dar Lablabi", "product": "Lablabi", "total": 6.5}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.restaurant").value("Dar Lablabi"))
                .andExpect(jsonPath("$.total").value(6.5));

        mockMvc.perform(get("/orders/" + id)).andExpect(jsonPath("$.product").value("Lablabi"));
        assertThat(repository.count()).as("update must not create a second order").isEqualTo(1);
    }

    @Test
    @DisplayName("PUT /orders/{id} answers 404 for an unknown id")
    void updateUnknown() throws Exception {
        mockMvc.perform(put("/orders/999999").contentType(MediaType.APPLICATION_JSON).content(PLAT_HEALTHY))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /orders/{id} answers 204 and the order is gone")
    void deleteOrder() throws Exception {
        long id = createOrder();
        mockMvc.perform(delete("/orders/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/orders/" + id)).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /orders/{id} answers 404 for an unknown id")
    void deleteUnknownOrder() throws Exception {
        mockMvc.perform(delete("/orders/999999")).andExpect(status().isNotFound());
    }
}
