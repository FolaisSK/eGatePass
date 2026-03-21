package com.fola.dtos.requests;

import lombok.Data;

@Data
public class ValidateCodeRequest {
    private String codeType;
    private String code;
}
