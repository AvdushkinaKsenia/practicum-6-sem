package ru.omstu.fitprogwork;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testEndpoint() throws Exception {

        String json = """
        {
          "type": "json",
          "data": "{\\"user\\":{\\"name\\":\\"Alex\\"}}",
          "path": "user/name"
        }
        """;

        mockMvc.perform(post("/api/data/extract")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Alex"));
    }
}