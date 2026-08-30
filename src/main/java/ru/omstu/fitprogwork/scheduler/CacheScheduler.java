package ru.omstu.fitprogwork.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.omstu.fitprogwork.service.ICacheService;

import java.time.LocalDateTime;

@Component
public class CacheScheduler {

    private final ICacheService cacheService;

    public CacheScheduler(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Scheduled(fixedRate = 30_000)
    @Transactional
    public void evictExpiredEntries() {
        LocalDateTime threshold = LocalDateTime.now().minusSeconds(60);
        System.out.println("[Scheduler] Удаление записей старше 60 сек (порог: " + threshold + ")");
        try {
            cacheService.evictOlderThan(threshold);
        } catch (Exception e) {
            System.err.println("[Scheduler] Ошибка при очистке устаревших записей: " + e.getMessage());
            throw e;
        }
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    @Transactional
    public void evictAllEntriesWeekly() {
        System.out.println("[Scheduler] Еженедельная полная очистка кеша (воскресенье 00:00)");
        try {
            cacheService.evictAll();
        } catch (Exception e) {
            System.err.println("[Scheduler] Ошибка при полной очистке кеша: " + e.getMessage());
            throw e;
        }
    }
}
