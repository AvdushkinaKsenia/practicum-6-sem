package ru.omstu.fitprogwork.controller;

import org.springframework.web.bind.annotation.*;
import ru.omstu.fitprogwork.dto.RequestDto;
import ru.omstu.fitprogwork.service.DataProcessingService;

import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataExtractorController {

    private final DataProcessingService service;

    public DataExtractorController(DataProcessingService service) {
        this.service = service;
    }

    @PostMapping("/extract")
    public Map<String, String> extract(@RequestBody RequestDto request) {
        try {
            String value = service.process(request);
            return Map.of("value", value);
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }
}