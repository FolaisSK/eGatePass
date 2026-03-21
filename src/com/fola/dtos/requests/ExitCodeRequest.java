package com.fola.dtos.requests;

import lombok.Data;

@Data
public class ExitCodeRequest {
    private String entryCode;
    private String residentEmail;
}
