package com.fola.controllers;

import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.requests.OnboardResidentRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.services.GateAccessService;
import com.fola.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResidentControllers {
    @Autowired
    private GateAccessService gateAccessService;

    @GetMapping("/generateCode")
    public GenerateResidentEntryCodeResponse generateResidentEntryCode( GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest){
        return gateAccessService.generateResidentEntryCode(generateResidentEntryCodeRequest);
    }
}
