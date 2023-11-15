package de.telran.URL_Shortener.URL_Shortener.test;

import de.telran.URL_Shortener.URL_Shortener.controller.UrlController;
import de.telran.URL_Shortener.URL_Shortener.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class UrlControllerTest {

  @Mock
  private UrlService urlService;

  @InjectMocks
  private UrlController urlController;

  @BeforeEach
  void setUp() {
   MockitoAnnotations.initMocks(this);
  }

  @Test
  void testCreate() {
   String longUrl = "http://example.com";
   String shortUrl = "abc123";
   when(urlService.createShortUrl(longUrl)).thenReturn(shortUrl);

   Model model = mock(Model.class);
   String result = urlController.create(longUrl, model);

   assertEquals("create_url", result);
   verify(model).addAttribute("SHORT_URL", shortUrl);
   verify(model).addAttribute("LONG_URL", longUrl);
  }
}
