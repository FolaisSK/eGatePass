package com.fola.controllers;

import com.fola.dtos.requests.OnboardResidentRequest;
import com.fola.dtos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.fola.services.ResidentManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class EstateManagerControllers {
    @Autowired
    private ResidentManagementService residentManagementService;

    @PostMapping("/resident")
    public ApiResponse onboardNewResident(@RequestBody OnboardResidentRequest request) {
        try {
            return new ApiResponse(true, HttpStatus.CREATED.name(), residentManagementService.onboardResident(request));
        } catch (Exception e) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST.name(), e.getMessage());
        }
    }


}

