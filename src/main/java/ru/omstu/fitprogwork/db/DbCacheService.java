package ru.omstu.fitprogwork.db;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.omstu.fitprogwork.service.ICacheService;

import java.time.LocalDateTime;
import java.util.Optional;

@Primary
@Service
@Transactional // Каждый метод выполняется в транзакции

public class DbCacheService implements ICacheService {

    private final CacheEntryRepository repository;

    public DbCacheService(CacheEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public String get(String key) {
        Optional<CacheEntry> entry = repository.findByCacheKey(key);
        if (entry.isPresent()) {
            System.out.println("CACHE HIT (DB): " + key);
            return entry.get().getCacheValue();
        }
        System.out.println("CACHE MISS (DB): " + key);
        return null;
    }

    @Override
    public void put(String key, String value) {
        System.out.println("CACHE PUT (DB): " + key);
        // Если запись уже есть — обновляем значение, иначе создаём новую
        Optional<CacheEntry> existing = repository.findByCacheKey(key);
        if (existing.isPresent()) {
            CacheEntry entry = existing.get();
            entry.setCacheValue(value);
            repository.save(entry);
        } else {
            repository.save(new CacheEntry(key, value));
        }
    }

    @Override
    public void evict(String key) {
        System.out.println("CACHE EVICT (DB): " + key);
        repository.deleteByCacheKey(key);
    }

    @Override
    public void evictOlderThan(LocalDateTime threshold) {
        System.out.println("CACHE EVICT OLDER THAN (DB): " + threshold);
        repository.deleteByCreatedAtBefore(threshold);
    }

    @Override
    public void evictAll() {
        System.out.println("CACHE EVICT ALL (DB)");
        repository.deleteAll();
    }
}
