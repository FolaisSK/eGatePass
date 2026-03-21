package com.fola.controllers;

import com.fola.dtos.requests.ValidateCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.fola.services.GateAccessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gate")
public class GateManControllers {

    @Autowired
    private GateAccessService gateAccessService;

    @PostMapping("/validate-code")
    public ResponseEntity<?> validateGatePass(@RequestBody ValidateCodeRequest request) {
        try {
            return ResponseEntity.ok(gateAccessService.validateCode(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
