package com.fola.controllers;

import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.services.GateAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resident")
public class ResidentControllers {

    @Autowired
    private GateAccessService gateAccessService;

    @PostMapping("/generate-code")
    public GenerateResidentEntryCodeResponse generateResidentEntryCode(@RequestBody GenerateResidentEntryCodeRequest request) {
        return gateAccessService.generateResidentEntryCode(request);
    }
}
