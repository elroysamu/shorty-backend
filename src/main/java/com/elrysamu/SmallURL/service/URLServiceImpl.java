package com.elrysamu.SmallURL.service;

import com.elrysamu.SmallURL.model.Url;
import com.elrysamu.SmallURL.model.UrlDto;
import com.elrysamu.SmallURL.repository.UrlRepository;
import com.google.common.hash.Hashing;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class URLServiceImpl implements URLService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlDto urlDto) {
        if (StringUtils.isNotEmpty(urlDto.getUrl())){
            String encodedUrl = encodeUrl(urlDto.getUrl());
            Url urlToPersist = new Url();

            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalUrl(urlDto.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            urlToPersist.setExpirationDate(getExpirationDateTime(urlDto.getExpirationDate(),urlToPersist.getCreationDate()));
            return persistShortLink(urlToPersist);
        }
        return null;
    }

    private LocalDateTime getExpirationDateTime(String expirationDate, LocalDateTime creationDate) {
        if(StringUtils.isBlank(expirationDate)){
            return creationDate.plusSeconds(60);
        }else{
            return LocalDateTime.parse(expirationDate);
        }
    }

    private String encodeUrl(String url) {
        LocalDateTime time = LocalDateTime.now();
        return Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
    }

    @Override
    public Url persistShortLink(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url getEncodedUrl(String url) {
        return urlRepository.findByShortLink(url);
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }
}
