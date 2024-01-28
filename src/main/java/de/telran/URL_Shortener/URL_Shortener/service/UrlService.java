package de.telran.URL_Shortener.URL_Shortener.service;

import com.google.common.hash.Hashing;
import de.telran.URL_Shortener.URL_Shortener.model.Url;
import de.telran.URL_Shortener.URL_Shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlService {
    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository repository) {
        this.urlRepository = repository;
    }

    public String getRedirectUrl(String shortUrl) {
        List<Url> urls = urlRepository.findByShortLink(shortUrl);
        if (!urls.isEmpty()) {
            return urls.get(0).getOriginalUrl();
        }
        return "redirect:index.html";
    }


    public String createShortUrl(String longUrl) {
        String shortUrl = Hashing.murmur3_32_fixed(123).hashString(longUrl, Charset.defaultCharset()).toString();
        Url url = new Url(longUrl, shortUrl, LocalDateTime.now());
        urlRepository.save(url);
        return "http://localhost:8080/go/" + shortUrl;
    }
}

