package de.telran.URL_Shortener.URL_Shortener.controller;

import de.telran.URL_Shortener.URL_Shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/go/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        String redirectUrl = urlService.getRedirectUrl(shortUrl);

        if (redirectUrl != null) {
            return "redirect:" + redirectUrl;
        } else {
            return "error_page"; // error_page.html для отображения сообщения об ошибке.
        }
    }

    @GetMapping("/create")
    public String create(
            @RequestParam(name = "url") String longUrl,
            Model model
    ) {
        String shortUrl = urlService.createShortUrl(longUrl);
        model.addAttribute("SHORT_URL", shortUrl);
        model.addAttribute("LONG_URL", longUrl);
        return "create_url";
    }
}
