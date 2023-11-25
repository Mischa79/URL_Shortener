package de.telran.URL_Shortener.URL_Shortener.test;

import de.telran.URL_Shortener.URL_Shortener.controller.UrlController;
import de.telran.URL_Shortener.URL_Shortener.service.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


class UrlControllerRedirectTest {
     @Mock
     private UrlService urlService;

     @InjectMocks
     private UrlController urlController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
     @Test
     void testRedirect() {
         String shortUrl = "abc123";
         String longUrl = "http://example.com";
         String redirectUrl = "redirect:" + longUrl;

         Mockito.when(urlService.getRedirectUrl(shortUrl)).thenReturn(longUrl);

         String result = urlController.redirect(shortUrl);

         Assertions.assertEquals(redirectUrl, result);
         Mockito.verify(urlService).getRedirectUrl(shortUrl);
     }
 }
