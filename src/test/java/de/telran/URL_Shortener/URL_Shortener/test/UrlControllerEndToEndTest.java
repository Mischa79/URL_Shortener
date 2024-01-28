package de.telran.URL_Shortener.URL_Shortener.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlControllerEndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    public UrlControllerEndToEndTest() {
    }

    @Test
    public void testCreateAndRedirectUrl() {

        String longUrl = "https://example.com";
        ResponseEntity<UrlResponse> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/go?url=" + longUrl, null, UrlResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        String shortUrl = responseEntity.getBody().getShortUrl();
        shortUrl = shortUrl.replaceAll(".*/", "");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ResponseEntity<Void> redirectResponse = restTemplate.getForEntity("http://localhost:" + port + "/api/go/" + shortUrl, Void.class);
        assertThat(redirectResponse.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(redirectResponse.getHeaders().getLocation().toString()).isEqualTo(longUrl);

    }

    static class UrlResponse {
        private String shortUrl;

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }
    }
}
