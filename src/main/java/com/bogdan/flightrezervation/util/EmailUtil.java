package com.bogdan.flightrezervation.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Value("${com.bogdan.flight-rezervation.itinerary.email.body}")
    public String EMAIL_BODY;
    @Value("${com.bogdan.flight-rezervation.itinerary.email.subject}")
    public String EMAIL_SUBJECT;
    @Autowired
    private JavaMailSender sender;

    public void sendItinerary(String toAddress, String filePath) throws MessagingException {

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(EMAIL_SUBJECT);
        messageHelper.setText(EMAIL_BODY);
        messageHelper.addAttachment("Itinerary", new File(filePath));
        sender.send(message);

    }
}
