package ru.omstu.fitprogwork.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class DataProcessingService {

    private final Map<String, DataExtractorService> services;

    public DataProcessingService(Map<String, DataExtractorService> services) {
        this.services = services;
    }

    public String process(ru.omstu.fitprogwork.dto.RequestDto request) throws Exception {

        DataExtractorService service = services.get(request.getType());

        if (service == null) {
            throw new RuntimeException("Unknown type: " + request.getType());
        }

        return service.extract(request.getData(), request.getPath());
    }
}