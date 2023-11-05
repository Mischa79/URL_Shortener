package de.telran.URL_Shortener.URL_Shortener.repository;

import de.telran.URL_Shortener.URL_Shortener.model.Url;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UrlRepository extends CrudRepository<Url, String> {


    List<Url> findByShortLink(String shortLink);


   // https://www.baeldung.com/spring-data-derived-queries
    @Transactional
    void deleteByCreationDateLessThan(LocalDateTime dateTime);

}
