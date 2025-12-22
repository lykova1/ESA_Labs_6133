package com.example.labs.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ChangeEventDto implements Serializable {

    private String operation;
    private String entity;
    private Long entityId;
    private String details;
}
