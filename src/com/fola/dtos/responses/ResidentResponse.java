package com.fola.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResidentResponse {
    private String name;
    private String phoneNumber;
    private String email;
    private String houseAddress;
    private boolean isEnabled = true;
    private LocalDateTime dateRegistered =  LocalDateTime.now();
}