package de.telran.URL_Shortener.URL_Shortener;

import de.telran.URL_Shortener.URL_Shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CronTask {

    @Autowired
    public CronTask(UrlRepository urlRepository) {
    }

    @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
    public void scheduleTaskUsingCronExpression() {
        // Получить текущее время в миллисекундах
        long currentTimeMillis = System.currentTimeMillis();

        // Преобразовать текущее время в объект Instant
        Instant currentInstant = Instant.ofEpochMilli(currentTimeMillis);

        // Вычесть 30 дней из текущего времени
        Duration duration = Duration.ofDays(30);
        Instant thirtyDaysAgoInstant = currentInstant.minus(duration);

        // Преобразовать Instant в LocalDateTime в часовом поясе "Europe/Paris"
        LocalDateTime thirtyDaysAgoLocalDateTime = LocalDateTime.ofInstant(thirtyDaysAgoInstant, ZoneId.of("Europe/Paris"));

        System.out.println("Current time: " + LocalDateTime.ofInstant(currentInstant, ZoneId.of("Europe/Paris")));
        System.out.println("Thirty days ago: " + thirtyDaysAgoLocalDateTime);
        System.out.println("Schedule tasks using cron jobs");
    }
}