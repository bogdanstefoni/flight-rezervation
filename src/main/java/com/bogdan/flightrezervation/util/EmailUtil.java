package com.bogdan.flightrezervation.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender sender;

    public void sendItinerary(String toAddress, String filePath) throws MessagingException {

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject("Itinerary for your Flight");
        messageHelper.setText("Please find your Itinerary attached");
        messageHelper.addAttachment("Itinerary", new File(filePath));
        sender.send(message);

    }
}
