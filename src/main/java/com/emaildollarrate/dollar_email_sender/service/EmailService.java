package com.emaildollarrate.dollar_email_sender.service;

import com.emaildollarrate.dollar_email_sender.entity.DollarRate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String recipientAddress, String subject, String body) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("cambiorate@gmail.com");
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setText(body);

        javaMailSender.send(message);
        System.out.println("Email sent successfully");
    }

    public void sendDollarRateEmail(String recipientAddress, DollarRate dollarRate) throws MailException, MessagingException {
        String subject = "Dollar Exchange Rate";

        String body = String.format("The exchange rate from %s to %s is R$: %.2f", dollarRate.baseCode(), dollarRate.targetCode(), dollarRate.conversionRate());
        sendEmail(recipientAddress, subject, body);
    }
}