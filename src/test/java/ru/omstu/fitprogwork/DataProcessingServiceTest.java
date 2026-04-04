package ru.omstu.fitprogwork;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.omstu.fitprogwork.dto.RequestDto;
import ru.omstu.fitprogwork.service.DataProcessingService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DataProcessingServiceTest {

    @Autowired
    private DataProcessingService service;

    @Test
    void testJsonProcessing() throws Exception {

        RequestDto request = new RequestDto();
        request.setType("json");
        request.setData("{\"user\":{\"name\":\"Alex\"}}");
        request.setPath("user/name");

        String result = service.process(request);

        assertEquals("Alex", result);
    }

    @Test
    void testCacheWorks() throws Exception {

        RequestDto request = new RequestDto();
        request.setType("json");
        request.setData("{\"user\":{\"name\":\"Alex\"}}");
        request.setPath("user/name");

        String first = service.process(request);
        String second = service.process(request);

        assertEquals(first, second);
    }
}