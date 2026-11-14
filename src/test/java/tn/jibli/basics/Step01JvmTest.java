package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.jibli.Main;

class Step01JvmTest {

    @Test
    @DisplayName("welcome() greets the user by name")
    void welcomeUsesTheName() {
        assertThat(Greeter.welcome("Dhia")).as("welcome message for Dhia")
                .isEqualTo("Welcome to Jibli, Dhia!");
        assertThat(Greeter.welcome("Amal")).as("welcome message for Amal")
                .isEqualTo("Welcome to Jibli, Amal!");
    }

    @Test
    @DisplayName("Main prints the welcome message for Dhia")
    void mainPrintsTheMessage() {
        PrintStream original = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
        try {
            Main.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        assertThat(captured.toString().trim()).as("what Main prints")
                .isEqualTo("Welcome to Jibli, Dhia!");
    }
}
