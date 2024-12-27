package com.emaildollarrate.dollar_email_sender;

import com.emaildollarrate.dollar_email_sender.service.DollarRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DollarEmailSenderApplication {

	private static final Logger log = LoggerFactory.getLogger(DollarEmailSenderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DollarEmailSenderApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(DollarRateService dolarRateService) {
		return args -> {
			dolarRateService.getDolarRate().subscribe(dolarRate -> log.info(dolarRate.toString()));
		};
	}
}