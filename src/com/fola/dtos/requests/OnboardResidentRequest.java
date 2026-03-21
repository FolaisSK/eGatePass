package com.fola.dtos.requests;

import lombok.Data;

@Data
public class OnboardResidentRequest {
    private String name;
    private String phone;
    private String email;
    private String address;
}