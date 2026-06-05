package ru.omstu.fitprogwork.service;

import org.springframework.stereotype.Service;
import ru.omstu.fitprogwork.dto.RequestDto;

import java.util.Map;

@Service
public class DataProcessingService {

    private final Map<String, DataExtractorService> services;
    private final CacheService cacheService;

    public DataProcessingService(Map<String, DataExtractorService> services,
                                 CacheService cacheService) {
        this.services = services;
        this.cacheService = cacheService;
    }

    public String process(RequestDto request) throws Exception {

        String key = request.toString();

        // Проверка кеша
        String cached = cacheService.get(key);
        if (cached != null) {
            return cached;
        }
        DataExtractorService service = services.get(request.getType());

        if (service == null) {
            throw new RuntimeException("Unknown type: " + request.getType());
        }

        String result = service.extract(request.getData(), request.getPath());

        cacheService.put(key, result);

        return result;
    }
}