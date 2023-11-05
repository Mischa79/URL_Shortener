package de.telran.URL_Shortener.URL_Shortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Url {
    @Id
    private String originalUrl;
    private String shortLink;
    private LocalDateTime creationDate;


    public Url( String originalUrl, String shortLink, LocalDateTime creationDate) {

        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.creationDate = creationDate;

    }

    public Url() {
    }

    @Override
    public String toString() {
        return "url{" +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", creationDate=" + creationDate +

                '}';
    }
}

