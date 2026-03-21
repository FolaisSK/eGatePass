package com.fola.services;

import com.fola.data.models.GatePass;
import com.fola.data.models.Resident;
import com.fola.data.models.Types;
import com.fola.data.repositories.GatePassRepository;
import com.fola.data.repositories.ResidentRepository;
import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.fola.dtos.requests.ValidateCodeRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.fola.dtos.responses.ValidateCodeResponse;
import com.fola.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fola.utils.RandomCodeGenerator;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class GateAccessService {
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private GatePassRepository gatePassRepository;

    public GenerateResidentEntryCodeResponse generateResidentEntryCode(GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest){
        GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse = new GenerateResidentEntryCodeResponse();
        Optional<Resident> resident = residentRepository.findById(generateResidentEntryCodeRequest.getResidentId());
        generateResidentEntryCodeResponse.setCode(generateCode());
        generateResidentEntryCodeResponse.setResidentName(resident.get().getName());
        generateResidentEntryCodeResponse.setCodeType(String.valueOf(Types.ENTRY));
        generateResidentEntryCodeResponse.setValidTill(String.valueOf(generateResidentEntryCodeRequest.getValidTill()));
        generateResidentEntryCodeResponse.setCreatedAT(String.valueOf(LocalTime.now()));
        generateResidentEntryCodeResponse.setDestination(resident.get().getHouseAddress());

        GatePass gatePass = new GatePass();
        gatePass.setCode(generateResidentEntryCodeResponse.getCode());
        gatePass.setValid(true);
        gatePass.setPassType(Types.ENTRY);
        gatePass.setResidentEmail(resident.get().getEmail());
        gatePass.setValidTill(generateResidentEntryCodeRequest.getValidTill());
        gatePassRepository.save(gatePass);

        return generateResidentEntryCodeResponse;
    }

    public ValidateCodeResponse validateCode(ValidateCodeRequest validateCodeRequest){
        ValidateCodeResponse validateCodeResponse = new ValidateCodeResponse();
        validateCodeResponse.setCodeType(validateCodeRequest.getCodeType());
        GatePass gatePass = gatePassRepository.getByCode(validateCodeRequest.getCode());
        validateCodeResponse.setValid(gatePass.isValid());
        return validateCodeResponse;
    }

    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest generateVisitorEntryCodeRequest){
        GenerateVisitorEntryCodeResponse generateVisitorEntryCodeResponse = new GenerateVisitorEntryCodeResponse();
        generateVisitorEntryCodeResponse.setVisitorName(generateVisitorEntryCodeRequest.getVisitorsName());
        generateVisitorEntryCodeResponse.setCodeType(String.valueOf(Types.ENTRY));
        return generateVisitorEntryCodeResponse;
    }
    private String generateCode(){
        return RandomCodeGenerator.generateCode(6);
    }
}
