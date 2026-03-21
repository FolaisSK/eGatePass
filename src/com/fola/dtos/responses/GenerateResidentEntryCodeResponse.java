package com.fola.dtos.responses;

import lombok.Data;

@Data
public class GenerateResidentEntryCodeResponse {
    private String code;
    private String ResidentName;
    private String codeType;
    private String ValidTill;
    private String createdAT;
    private String destination;
}