package com.fola.controllers;

import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.fola.dtos.responses.ApiResponse;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.services.GateAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resident")
public class ResidentControllers {

    @Autowired
    private GateAccessService gateAccessService;

    @PostMapping("/entry/generate-code")
    public ApiResponse generateResidentEntryCode(@RequestBody GenerateResidentEntryCodeRequest request) {
        try {
            return new ApiResponse(true, HttpStatus.CREATED.name(),  gateAccessService.generateResidentEntryCode(request));
        } catch (Exception e){
            return new ApiResponse(false, HttpStatus.BAD_REQUEST.name(), e.getMessage());
        }
    }

    @PostMapping("/visitor/entry/generate-code")
    public ApiResponse generateVisitorEntryCode(@RequestBody GenerateVisitorEntryCodeRequest generateVisitorEntryCodeRequest){
        try {
            return new ApiResponse(true, HttpStatus.CREATED.name(), gateAccessService.generateVisitorEntryCode(generateVisitorEntryCodeRequest));
        } catch (Exception e) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST.name(), e.getMessage());
        }
    }
}
