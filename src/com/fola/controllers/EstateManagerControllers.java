package com.fola.controllers;

import com.fola.dtos.requests.OnboardResidentRequest;
import com.fola.dtos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.fola.services.ResidentManagementService;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete-resident/{residentId}")
    public ApiResponse deleteResident(@PathVariable("residentId") String residentId){
        try {
            return new ApiResponse(true, HttpStatus.OK.name(), residentManagementService.deleteResident(residentId));
        } catch (Exception e) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST.name(), e.getMessage());
        }
    }

    @PatchMapping("/disable-resident/{residentId}")
    public ApiResponse disableResident(@PathVariable("residentId") String residentId){
        try {
            return new ApiResponse(true, HttpStatus.OK.name(), residentManagementService.disableResident(residentId));
        } catch (Exception e) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST.name(), e.getMessage());
        }
    }

    @GetMapping("/view-residents")
    public ApiResponse viewAllResidents(){
        try {
            return new ApiResponse(true, HttpStatus.OK.name(), residentManagementService.viewAllResidents());
        } catch (Exception e) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST.name(), e.getMessage());
        }
    }
}

