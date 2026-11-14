package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Step22MavenTest {

    @Autowired
    private ApplicationContext context;

    private String pom() throws Exception {
        return Files.readString(Path.of("pom.xml"));
    }

    @Test
    @DisplayName("the pom has the web and JPA starters")
    void starters() throws Exception {
        assertThat(pom()).contains("<artifactId>spring-boot-starter-web</artifactId>");
        assertThat(pom()).contains("<artifactId>spring-boot-starter-data-jpa</artifactId>");
    }

    @Test
    @DisplayName("H2 is a runtime dependency")
    void h2IsRuntime() throws Exception {
        assertThat(Pattern.compile("<artifactId>h2</artifactId>\\s*<scope>runtime</scope>").matcher(pom()).find())
                .as("h2 with scope runtime").isTrue();
    }

    @Test
    @DisplayName("the Spring Boot plugin knows the main class")
    void plugin() throws Exception {
        assertThat(pom()).contains("<artifactId>spring-boot-maven-plugin</artifactId>");
        assertThat(pom()).contains("<mainClass>tn.jibli.JibliApplication</mainClass>");
    }

    @Test
    @DisplayName("the application still starts with the new dependencies")
    void contextStarts() {
        assertThat(context.getBeanDefinitionCount()).isGreaterThan(0);
    }
}
