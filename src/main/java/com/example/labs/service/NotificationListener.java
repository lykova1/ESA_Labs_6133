package com.example.labs.service;

import com.example.labs.dto.ChangeEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;

    public NotificationListener(JavaMailSender mailSender, ObjectMapper objectMapper) {
        this.mailSender = mailSender;
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "change.queue")
    public void notify(String messageJson) {
        try {
            ChangeEventDto event = objectMapper.readValue(messageJson, ChangeEventDto.class);

            if ("DELETE".equals(event.getOperation())
                    && "Book".equals(event.getEntity())) {

                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo("admin@example.com");
                msg.setSubject("Book deleted");
                msg.setText("Book with id " + event.getEntityId() + " was deleted");

                mailSender.send(msg);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при обработке JMS сообщения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
