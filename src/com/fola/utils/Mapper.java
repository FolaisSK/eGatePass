package com.fola.utils;

import com.fola.data.models.GatePass;
import com.fola.data.models.Resident;
import com.fola.data.models.Types;
import com.fola.data.repositories.GatePassRepository;
import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.requests.OnboardResidentRequest;
import com.fola.dtos.requests.ValidateCodeRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.dtos.responses.OnboardResidentResponse;
import com.fola.dtos.responses.ValidateCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class Mapper {
    @Autowired
    private GatePassRepository gatePassRepository;

    public static Resident map(OnboardResidentRequest onboardResidentRequest) {
        Resident resident = new Resident();
        resident.setName(onboardResidentRequest.getName());
        resident.setEmail(onboardResidentRequest.getEmail());
        resident.setPhoneNumber(onboardResidentRequest.getPhone());
        resident.setDateRegistered(LocalDateTime.now());
        resident.setHouseAddress(onboardResidentRequest.getAddress());
        return resident;
    }

    public static OnboardResidentResponse map(Resident resident) {
        OnboardResidentResponse onBoardResidentResponse = new OnboardResidentResponse();
        onBoardResidentResponse.setResidentId(resident.getId());
        onBoardResidentResponse.setResidentName(resident.getName());
        onBoardResidentResponse.setDateRegistered(resident.getDateRegistered().toString());
        onBoardResidentResponse.setHouseAddress(resident.getHouseAddress());
        return  onBoardResidentResponse;
    }


//    public static GenerateResidentEntryCodeResponse map(GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest){
//        GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse = new GenerateResidentEntryCodeResponse();
//        Optional<Resident> resident = residentRepository.findById(generateResidentEntryCodeRequest.getResidentId());
//        generateResidentEntryCodeResponse.setResidentName(resident.get().getName());
//        generateResidentEntryCodeResponse.setCodeType(String.valueOf(Types.ENTRY));
//        generateResidentEntryCodeResponse.setValidTill(String.valueOf(generateResidentEntryCodeRequest.getValidTill()));
//        generateResidentEntryCodeResponse.setCreatedAT(String.valueOf(LocalTime.now()));
//        generateResidentEntryCodeResponse.setDestination(resident.get().getHouseAddress());
//        return generateResidentEntryCodeResponse;
//    }
}
