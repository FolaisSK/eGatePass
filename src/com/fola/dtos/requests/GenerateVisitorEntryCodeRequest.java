package com.fola.dtos.requests;

import lombok.Data;

import java.time.LocalTime;

@Data
public class GenerateVisitorEntryCodeRequest {
    private String residentEmail;
    private String visitorPhone;
    private String purposeOfComing;
    private String visitorsName;
    private LocalTime validTill;
}