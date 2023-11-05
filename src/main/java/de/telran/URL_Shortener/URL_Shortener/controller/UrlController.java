package de.telran.URL_Shortener.URL_Shortener.controller;


import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import de.telran.URL_Shortener.URL_Shortener.model.Url;
import de.telran.URL_Shortener.URL_Shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UrlController {
    private UrlRepository urlRepository;


    static HashFunction murmur = Hashing.murmur3_32_fixed(123);

    @Autowired
    public UrlController(UrlRepository repository) {
        this.urlRepository = repository;
    }

    //GET http://localhost:8080/go/123
    @GetMapping("/go/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        List<Url> urls = urlRepository.findByShortLink(shortUrl);
        if (urls.size() > 0)
            return "redirect:" + urls.get(0).getOriginalUrl();

        return "redirect:index.html";
    }

    //http://localhost:8080/create -> create_url.html
    @GetMapping("/create")
    public String create(
            @RequestParam (name = "url") String longUrl,
            Model model
    ) {
        // получить короткую ссылку по длинной ссылке
        // localhost:8080/go/7837c903
        String shortUrl = murmur.hashString(longUrl, Charset.defaultCharset()).toString();
        // shortUrl = "http://localhost:8080/go/" + shortUrl;
        Url url = new Url(longUrl, shortUrl, LocalDateTime.now());

        urlRepository.save(url);

        model.addAttribute("SHORT_URL", "http://localhost:8080/go/" + shortUrl);
        model.addAttribute("LONG_URL", longUrl);

        return "create_url";
    }
    // https://dev.mysql.com/downloads/mysql/

}
