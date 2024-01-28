package de.telran.URL_Shortener.URL_Shortener;

import de.telran.URL_Shortener.URL_Shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronTask {

    private final UrlRepository urlRepository;

    @Autowired
    public CronTask(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void scheduleTaskUsingCronExpression() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        urlRepository.deleteByCreationDateLessThan(oneMonthAgo);
        System.out.println("Выполнена задача по удалению устаревших URL.");
    }
}