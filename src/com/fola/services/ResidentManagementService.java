package com.fola.services;

import com.fola.data.models.Resident;
import com.fola.data.repositories.ResidentRepository;
import com.fola.dtos.requests.OnboardResidentRequest;
import com.fola.dtos.responses.OnboardResidentResponse;
import com.fola.exceptions.ResidentAlreadyExistException;
import com.fola.exceptions.ResidentDoesNotExistException;
import com.fola.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentManagementService {
    @Autowired
    private ResidentRepository residentRepository;

    public OnboardResidentResponse onboardResident(OnboardResidentRequest onboardResidentRequest){
        Resident resident = Mapper.map(onboardResidentRequest);
        validateCheckDuplicateFor(resident);
        residentRepository.save(resident);
        return Mapper.map(resident);
    }

    public String deleteResident(String residentId){
        Optional<Resident> resident = Optional.of(residentRepository.findById(residentId).orElseThrow(()-> new ResidentDoesNotExistException("Resident Does Not Exist!")));
        residentRepository.delete(resident.get());
        return "Resident has been deleted!";
    }

    private void validateCheckDuplicateFor(Resident resident){
        if(residentRepository.existsResidentByEmailOrPhoneNumber(resident.getEmail(), resident.getPhoneNumber())) throw new ResidentAlreadyExistException("Resident already exists");
    }

    public String disableResident(String residentId) {
        Optional<Resident> resident = Optional.of(residentRepository.findById(residentId).orElseThrow(()-> new ResidentDoesNotExistException("Resident Does Not Exist!")));
        resident.get().setEnabled(false);
        residentRepository.save(resident.get());
        return "Resident has been disabled!";
    }

    public List<OnboardResidentResponse> viewAllResidents(){
        return residentRepository.findAll().stream().map(resident -> Mapper.map(resident)).toList();
    }
}
