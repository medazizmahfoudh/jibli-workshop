package tn.jibli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// scans this package and the ones below it for annotated classes
@SpringBootApplication
public class JibliApplication {

    public static void main(String[] args) {
        SpringApplication.run(JibliApplication.class, args);
    }
}
