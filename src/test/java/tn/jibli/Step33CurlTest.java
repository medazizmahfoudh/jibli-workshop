package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

// starts the real application on a random port and runs the script against it
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Step33CurlTest {

    @Value("${local.server.port}")
    private int port;

    private boolean available(String tool) {
        try {
            Process process = new ProcessBuilder("bash", "-c", "command -v " + tool).redirectErrorStream(true).start();
            process.getInputStream().readAllBytes();
            return process.waitFor(10, TimeUnit.SECONDS) && process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    @DisplayName("scripts/smoke-test.sh passes against the running application")
    void smokeTestPasses() throws Exception {
        assumeTrue(available("bash") && available("curl"), "bash and curl are needed for this test");

        ProcessBuilder builder = new ProcessBuilder("bash", "scripts/smoke-test.sh").redirectErrorStream(true);
        builder.environment().put("BASE_URL", "http://localhost:" + port);
        Process process = builder.start();

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line);
                }
            }
        }
        boolean finished = process.waitFor(60, TimeUnit.SECONDS);
        String output = String.join("\n", lines);

        assertThat(finished).as("the script should finish within 60 seconds").isTrue();
        assertThat(process.exitValue()).as("exit code of the script, output was:\n" + output).isEqualTo(0);
        assertThat(lines).as("output of the script").isNotEmpty();
        assertThat(lines.get(lines.size() - 1)).isEqualTo("ALL CHECKS PASSED");
    }
}
