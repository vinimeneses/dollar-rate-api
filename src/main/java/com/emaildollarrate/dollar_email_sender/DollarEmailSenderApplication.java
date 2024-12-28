package com.emaildollarrate.dollar_email_sender;

import com.emaildollarrate.dollar_email_sender.service.DollarRateService;
import com.emaildollarrate.dollar_email_sender.service.EmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;

@SpringBootApplication
public class DollarEmailSenderApplication {

	private static final Logger log = LoggerFactory.getLogger(DollarEmailSenderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DollarEmailSenderApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(DollarRateService dollarRateService, EmailService emailService) {
		return args -> {
			dollarRateService.getDolarRate().subscribe(dollarRate -> {
				log.info(dollarRate.toString());
				try {
					emailService.sendDollarRateEmail("viniciusmmeneses15@gmail.com", dollarRate);
				} catch (MailException | MessagingException e) {
					log.error("Failed to send email", e);
				}
			});
		};
	}
}