package ru.omstu.fitprogwork.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CACHE_ENTRIES")
public class CacheEntry {

    @Id
    @Column(name = "CACHE_KEY", unique = true, nullable = false, length = 10000)
    private String cacheKey;

    // Закешированный результат
    @Column(name = "CACHE_VALUE", nullable = false, length = 10000)
    private String cacheValue;

    // Время создания записи. Устанавливается автоматически
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    // Автоматически проставляет время перед первой вставкой
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Конструкторы
    public CacheEntry() {}

    public CacheEntry(String cacheKey, String cacheValue) {
        this.cacheKey   = cacheKey;
        this.cacheValue = cacheValue;
    }

    // Геттеры / Сеттеры
    public String getCacheKey()            { return cacheKey; }
    public void setCacheKey(String k)    { this.cacheKey = k; }

    public String getCacheValue()          { return cacheValue; }
    public void setCacheValue(String v)  { this.cacheValue = v; }

    public LocalDateTime getCreatedAt()           { return createdAt; }
    public void setCreatedAt(LocalDateTime t) { this.createdAt = t; }
}