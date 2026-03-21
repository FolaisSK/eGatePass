package com.fola.controllers;

import com.fola.dtos.requests.ValidateCodeRequest;
import org.springframework.http.ResponseEntity;
import com.fola.services.GateAccessService;

public class GateManControllers {
    private GateAccessService gateAccessService;

    public ResponseEntity<?> validateGatePass(ValidateCodeRequest validateCodeRequest){
        return null;
    }
}
