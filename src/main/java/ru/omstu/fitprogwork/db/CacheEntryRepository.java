package ru.omstu.fitprogwork.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CacheEntryRepository extends JpaRepository<CacheEntry, String> {

    // Найти запись по ключу
    Optional<CacheEntry> findByCacheKey(String cacheKey);

    // Удалить запись по ключу
    @Modifying
    @Query("DELETE FROM CacheEntry e WHERE e.cacheKey = :key")
    void deleteByCacheKey(@Param("key") String cacheKey);

    // Удалить все записи, созданные раньше указанного момента
    @Modifying
    @Query("DELETE FROM CacheEntry e WHERE e.createdAt < :threshold")
    void deleteByCreatedAtBefore(@Param("threshold") LocalDateTime threshold);
}