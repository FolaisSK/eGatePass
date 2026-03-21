package com.fola.dtos.requests;

import lombok.Data;

@Data
public class GenerateVisitorEntryCodeRequest {
    private String residentEmail;
    private String visitorPhone;
    private String purposeOfComing;
    private String visitorsName;
}