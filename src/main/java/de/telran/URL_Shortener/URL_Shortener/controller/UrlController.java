package de.telran.URL_Shortener.URL_Shortener.controller;

import de.telran.URL_Shortener.URL_Shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @GetMapping("/go/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl) {
        String redirectUrl = urlService.getRedirectUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
    }

    @PostMapping("/go")
    public ResponseEntity<Map<String, String>> create(@RequestParam String url) {
        String shortUrl = urlService.createShortUrl(url);
        Map<String, String> response = new HashMap<>();
        response.put("shortUrl", shortUrl);
        return ResponseEntity.ok(response);
    }


}


