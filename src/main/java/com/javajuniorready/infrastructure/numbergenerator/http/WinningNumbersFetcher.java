package com.javajuniorready.infrastructure.numbergenerator.http;

import org.springframework.web.client.RestTemplate;

public class WinningNumbersFetcher {
    private final RestTemplate restTemplate;

    public WinningNumbersFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchApiData(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
