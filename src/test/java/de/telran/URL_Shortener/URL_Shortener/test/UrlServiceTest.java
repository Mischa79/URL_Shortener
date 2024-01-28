package de.telran.URL_Shortener.URL_Shortener.test;

import de.telran.URL_Shortener.URL_Shortener.model.Url;
import de.telran.URL_Shortener.URL_Shortener.repository.UrlRepository;
import de.telran.URL_Shortener.URL_Shortener.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    @Test
    public void testGetRedirectUrl() {

        when(urlRepository.findByShortLink("abc123")).thenReturn(Collections.singletonList(new Url("http://example.com", "abc123", LocalDateTime.now())));

        String redirectUrl = urlService.getRedirectUrl("abc123");

        assertEquals("http://example.com", redirectUrl);
    }

    @Test
    public void testCreateShortUrl() {

    }
}
