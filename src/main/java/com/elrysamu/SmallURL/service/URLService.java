package com.elrysamu.SmallURL.service;

import com.elrysamu.SmallURL.model.Url;
import com.elrysamu.SmallURL.model.UrlDto;

public interface URLService {
    public Url generateShortLink(UrlDto urlDto);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public void deleteShortLink(Url url);
}
