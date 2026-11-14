package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class Step23InitializrTest {

    private static final Map<String, String> INITIALIZR_NAME_TO_ARTIFACT = Map.of(
            "Spring Web", "spring-boot-starter-web",
            "Spring Data JPA", "spring-boot-starter-data-jpa",
            "H2 Database", "h2");

    private Properties notes;
    private Element project;

    @BeforeEach
    void load() throws Exception {
        notes = new Properties();
        try (Reader reader = new FileReader("docs/initializr.properties")) {
            notes.load(reader);
        }
        Document pom = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(Files.newInputStream(Path.of("pom.xml")));
        project = pom.getDocumentElement();
    }

    private String childText(Element parent, String tag) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element element && element.getTagName().equals(tag)) {
                return element.getTextContent().trim();
            }
        }
        return null;
    }

    @Test
    @DisplayName("group and artifact match the pom")
    void groupAndArtifact() {
        assertThat(notes.getProperty("group")).as("group").isEqualTo(childText(project, "groupId"));
        assertThat(notes.getProperty("artifact")).as("artifact").isEqualTo(childText(project, "artifactId"));
    }

    @Test
    @DisplayName("packaging and Java version match the pom")
    void packagingAndJava() {
        String packaging = childText(project, "packaging");
        assertThat(notes.getProperty("packaging")).as("packaging (jar is the default)")
                .isEqualTo(packaging == null ? "jar" : packaging);

        Element properties = (Element) project.getElementsByTagName("properties").item(0);
        assertThat(notes.getProperty("java")).as("java").isEqualTo(childText(properties, "java.version"));
    }

    @Test
    @DisplayName("the dependency list matches the dependencies of the pom")
    void dependencies() {
        Set<String> names = Arrays.stream(notes.getProperty("dependencies", "").split(","))
                .map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toCollection(TreeSet::new));
        assertThat(names).as("dependency names").containsExactlyInAnyOrder("Spring Web", "Spring Data JPA", "H2 Database");

        NodeList artifacts = project.getElementsByTagName("artifactId");
        Set<String> inPom = new TreeSet<>();
        for (int i = 0; i < artifacts.getLength(); i++) {
            inPom.add(artifacts.item(i).getTextContent().trim());
        }
        for (String name : names) {
            assertThat(inPom).as("pom entry for " + name).contains(INITIALIZR_NAME_TO_ARTIFACT.get(name));
        }
    }
}
