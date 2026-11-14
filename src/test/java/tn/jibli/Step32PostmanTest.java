package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step32PostmanTest {

    private record Call(String method, String url) {
    }

    private final List<Call> calls = new ArrayList<>();

    @BeforeEach
    void readCollection() throws Exception {
        JsonNode root = new ObjectMapper().readTree(Path.of("postman/jibli.postman_collection.json").toFile());
        collect(root);
    }

    // collections can have folders, so look for requests at any depth
    private void collect(JsonNode node) {
        if (node.has("request") && node.get("request").has("method")) {
            JsonNode request = node.get("request");
            JsonNode url = request.get("url");
            String text = url == null ? "" : (url.isTextual() ? url.asText() : url.path("raw").asText());
            calls.add(new Call(request.get("method").asText(), text));
        }
        node.forEach(this::collect);
    }

    private boolean has(String method, java.util.function.Predicate<String> urlCheck) {
        return calls.stream().anyMatch(c -> c.method().equalsIgnoreCase(method) && urlCheck.test(c.url()));
    }

    @Test
    @DisplayName("the collection creates an order with POST")
    void post() {
        assertThat(has("POST", url -> url.contains("/orders"))).as("a POST on /orders").isTrue();
    }

    @Test
    @DisplayName("the collection lists the orders and gets one by id")
    void gets() {
        assertThat(has("GET", url -> url.matches(".*/orders/?"))).as("GET /orders (the list)").isTrue();
        assertThat(has("GET", url -> url.matches(".*/orders/[^/]+"))).as("GET /orders/{id}").isTrue();
    }

    @Test
    @DisplayName("the collection updates and deletes by id")
    void putAndDelete() {
        assertThat(has("PUT", url -> url.matches(".*/orders/[^/]+"))).as("PUT /orders/{id}").isTrue();
        assertThat(has("DELETE", url -> url.matches(".*/orders/[^/]+"))).as("DELETE /orders/{id}").isTrue();
    }

    @Test
    @DisplayName("the requests use the baseUrl variable")
    void usesBaseUrl() {
        assertThat(calls).as("all requests").isNotEmpty();
        assertThat(calls).allMatch(c -> c.url().contains("{{baseUrl}}"), "uses {{baseUrl}}");
    }
}
