package com.example.labs.service;

import com.example.labs.dto.ChangeEventDto;
import com.example.labs.model.ChangeLog;
import com.example.labs.repository.ChangeLogRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.example.labs.util.JmsDestinations;

import java.time.LocalDateTime;

@Component
public class JournalListener {

    private final ChangeLogRepo repo;

    @Autowired
    private ObjectMapper objectMapper;

    public JournalListener(ChangeLogRepo repo) {
        this.repo = repo;
    }

    @JmsListener(destination = JmsDestinations.CHANGE_TOPIC)
    public void receive(String messageJson) throws JsonProcessingException {
        ChangeEventDto event = objectMapper.readValue(messageJson, ChangeEventDto.class);

        ChangeLog log = new ChangeLog();
        log.setEventTime(LocalDateTime.now());
        log.setOperationType(event.getOperation());
        log.setEntityName(event.getEntity());
        log.setEntityId(event.getEntityId());
        log.setDetails(event.getDetails());

        repo.save(log);
    }
}
