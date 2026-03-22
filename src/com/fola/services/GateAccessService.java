package com.fola.services;

import com.fola.data.models.GatePass;
import com.fola.data.models.Resident;
import com.fola.data.models.Types;
import com.fola.data.models.Visitor;
import com.fola.data.repositories.GatePassRepository;
import com.fola.data.repositories.ResidentRepository;
import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.fola.dtos.requests.ValidateCodeRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.fola.dtos.responses.ValidateCodeResponse;
import com.fola.exceptions.ResidentDoesNotExistException;
import com.fola.exceptions.ResidentIsNotEnabledException;
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

        checkResidentIsEnabled(resident.get());

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

        Optional<Resident> resident = residentRepository.findByEmail(gatePass.getResidentEmail());
        if(resident.isEmpty()) throw new ResidentDoesNotExistException("No Resident");

        validateCodeResponse.setCreatedBy(resident.get().getName());
        validateCodeResponse.setResidentName(resident.get().getName());

        return validateCodeResponse;
    }

    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest generateVisitorEntryCodeRequest){
        Optional<Resident> resident = residentRepository.findByEmail(generateVisitorEntryCodeRequest.getResidentEmail());
        if(resident.isEmpty()) throw new ResidentDoesNotExistException("Resident Does Not Exist");
        checkResidentIsEnabled(resident.get());

        Visitor visitor = new Visitor();
        visitor.setName(generateVisitorEntryCodeRequest.getVisitorsName());
        visitor.setPhoneNumber(generateVisitorEntryCodeRequest.getVisitorPhone());
        visitor.setPurposeOfVisit(generateVisitorEntryCodeRequest.getPurposeOfComing());

        GenerateVisitorEntryCodeResponse generateVisitorEntryCodeResponse = new GenerateVisitorEntryCodeResponse();
        generateVisitorEntryCodeResponse.setVisitorName(generateVisitorEntryCodeRequest.getVisitorsName());
        generateVisitorEntryCodeResponse.setCodeType(String.valueOf(Types.ENTRY));
        generateVisitorEntryCodeResponse.setCode(generateCode());
        generateVisitorEntryCodeResponse.setValidTill(String.valueOf(generateVisitorEntryCodeRequest.getValidTill()));

        GatePass gatePass = new GatePass();
        gatePass.setCode(generateVisitorEntryCodeResponse.getCode());
        gatePass.setValid(true);
        gatePass.setPassType(Types.ENTRY);
        gatePass.setValidTill(generateVisitorEntryCodeRequest.getValidTill());
        gatePass.setResidentEmail(generateVisitorEntryCodeRequest.getResidentEmail());
        gatePass.setVisitor(visitor);
        gatePassRepository.save(gatePass);

        return generateVisitorEntryCodeResponse;
    }

    private String generateCode(){
        return RandomCodeGenerator.generateCode(6);
    }

    private void checkResidentIsEnabled(Resident resident){
        if(!resident.isEnabled()) throw new ResidentIsNotEnabledException("Resident Is Disabled");
    }
}
