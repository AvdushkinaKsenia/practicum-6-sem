package ru.omstu.fitprogwork.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public String get(String key) {
        if (cache.containsKey(key)) {
            System.out.println("CACHE HIT: " + key);
            return cache.get(key);
        }
        System.out.println("CACHE MISS: " + key);
        return null;
    }

    public void put(String key, String value) {
        System.out.println("CACHE PUT: " + key);
        cache.put(key, value);
    }
}