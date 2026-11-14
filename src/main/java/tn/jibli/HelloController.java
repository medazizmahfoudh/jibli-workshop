package tn.jibli;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// answers HTTP requests and writes the returned value in the response body
@RestController
public class HelloController {

    // GET /hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Jibli!";
    }
}
