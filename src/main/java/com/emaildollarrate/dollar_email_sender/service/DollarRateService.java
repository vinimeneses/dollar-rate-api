package com.emaildollarrate.dollar_email_sender.service;

import com.emaildollarrate.dollar_email_sender.entity.DollarRate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DollarRateService {

    private final WebClient webClient;

    public DollarRateService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<DollarRate> getDolarRate() {
        String apiKey = System.getenv("EXCHANGE_RATE_API_KEY");
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/USD/BRL";
        return webClient.get().uri(url).retrieve().bodyToMono(DollarRate.class);
    }

}