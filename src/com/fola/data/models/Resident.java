package com.fola.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Resident {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String houseAddress;
    private boolean isEnabled = true;
    private LocalDateTime dateRegistered =  LocalDateTime.now();



    @Override
    public boolean equals(Object object) {
        if(object instanceof Resident resident){
            return this.getEmail().equals(resident.getEmail()) || this.getPhoneNumber().equals(resident.getPhoneNumber());
        }return false;
    }
}


