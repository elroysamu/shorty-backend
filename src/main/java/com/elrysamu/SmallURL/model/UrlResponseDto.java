package com.elrysamu.SmallURL.model;

import java.time.LocalDateTime;

public class UrlResponseDto {
    private String OriginalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;

    public UrlResponseDto(String originalUrl, String shortLink, LocalDateTime expirationDate) {
        OriginalUrl = originalUrl;
        this.shortLink = shortLink;
        this.expirationDate = expirationDate;
    }

    public UrlResponseDto() {
    }

    public String getOriginalUrl() {
        return OriginalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        OriginalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlResponseDto{" +
                "OriginalUrl='" + OriginalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
