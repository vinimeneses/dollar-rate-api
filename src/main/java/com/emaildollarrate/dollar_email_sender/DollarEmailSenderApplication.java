package com.emaildollarrate.dollar_email_sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DollarEmailSenderApplication {

	String apiKey = System.getenv("EXCHANGE_RATE_API_KEY");

	public static void main(String[] args) {
		SpringApplication.run(DollarEmailSenderApplication.class, args);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public CommandLineRunner run(WebClient.Builder webClientBuilder) {
		return args -> {
			WebClient webClient = webClientBuilder.build();
			String url = "https://v6.exchangerate-api.com/v6/" + apiKey +"/pair/USD/BRL";

			Mono<String> response = webClient.get()
					.uri(url)
					.retrieve()
					.bodyToMono(String.class);

			response.subscribe(json -> {
				try {
					ObjectMapper mapper = new ObjectMapper();
					Object jsonObject = mapper.readValue(json, Object.class);
					ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
					String prettyJson = writer.writeValueAsString(jsonObject);
					System.out.println(prettyJson);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		};
	}
}