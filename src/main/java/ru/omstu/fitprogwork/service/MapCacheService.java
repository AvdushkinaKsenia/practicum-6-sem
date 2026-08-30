package ru.omstu.fitprogwork.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MapCacheService implements ICacheService {

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    @Override
    public String get(String key) {
        if (cache.containsKey(key)) {
            System.out.println("CACHE HIT (Map): " + key);
            return cache.get(key);
        }
        System.out.println("CACHE MISS (Map): " + key);
        return null;
    }

    @Override
    public void put(String key, String value) {
        System.out.println("CACHE PUT (Map): " + key);
        cache.put(key, value);
    }

    @Override
    public void evict(String key) {
        System.out.println("CACHE EVICT (Map): " + key);
        cache.remove(key);
    }

    @Override
    public void evictOlderThan(LocalDateTime threshold) {
        System.out.println("CACHE EVICT OLDER THAN (Map): не поддерживается, очищаем всё");
        cache.clear();
    }

    @Override
    public void evictAll() {
        System.out.println("CACHE EVICT ALL (Map)");
        cache.clear();
    }
}
