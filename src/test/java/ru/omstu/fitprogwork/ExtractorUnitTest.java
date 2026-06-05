package ru.omstu.fitprogwork;

import org.junit.jupiter.api.Test;
import ru.omstu.fitprogwork.service.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractorUnitTest {

    @Test
    void jsonTest() throws Exception {
        JsonExtractorService s = new JsonExtractorService();
        assertEquals("Alex",
                s.extract("{\"user\":{\"name\":\"Alex\"}}", "user/name"));
    }

    @Test
    void xmlTest() throws Exception {
        XmlExtractorService s = new XmlExtractorService();
        assertEquals("Alex",
                s.extract("<user><name>Alex</name></user>", "name"));
    }

    @Test
    void yamlTest() {
        YamlExtractorService s = new YamlExtractorService();

        String yaml = """
            user:
              name: Alex
        """;

        assertEquals("Alex", s.extract(yaml, "user/name"));
    }
}