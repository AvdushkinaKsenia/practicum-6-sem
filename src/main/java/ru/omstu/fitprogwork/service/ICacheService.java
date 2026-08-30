package ru.omstu.fitprogwork.service;

import java.time.LocalDateTime;

public interface ICacheService {

    String get(String key);

    void put(String key, String value);

    void evict(String key);

    void evictOlderThan(LocalDateTime threshold);

    void evictAll();
}
