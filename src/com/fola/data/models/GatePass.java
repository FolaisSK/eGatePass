package com.fola.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class GatePass {

    @Id
    private String id;
    private String code;
    private Visitor visitor;
    private String ResidentEmail;
    private Types passType;
    private boolean isValid;
    private LocalDateTime dateGenerated = LocalDateTime.now();
    private LocalTime validTill;

}
