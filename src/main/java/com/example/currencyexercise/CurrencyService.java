package com.example.currencyexercise;

import com.example.currencyexercise.rest.Currency;;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyService {
    private final WebClient webClient;

    public CurrencyService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://api.currencyapi.com/v3/").build();
    }

    public Currency getExchange(String baseCurrency, String exchangeCurrency) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("apikey", "") // put API key here
                        .queryParam("base_currency", baseCurrency)
                        .queryParam("currencies", exchangeCurrency)
                        .build())
                .retrieve() // Perform the HTTP request and retrieve the response body
                .bodyToMono(Currency.class)
                .block(); // returns the body as an array of User
    }
}
