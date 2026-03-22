package com.fola.services;

import com.fola.data.models.Resident;
import com.fola.data.repositories.GatePassRepository;
import com.fola.data.repositories.ResidentRepository;
import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.requests.OnboardResidentRequest;
import com.fola.dtos.requests.ValidateCodeRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import com.fola.dtos.responses.OnboardResidentResponse;
import com.fola.dtos.responses.ValidateCodeResponse;
import com.fola.exceptions.ResidentAlreadyExistException;
import com.fola.exceptions.ResidentIsNotEnabledException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResidentManagementServiceTest {

    @Autowired
    private ResidentManagementService residentManagementService;

    private OnboardResidentRequest onboardResidentRequest;
    private OnboardResidentResponse onboardResidentResponse;
    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private GateAccessService gateAccessService;
    @Autowired
    private GatePassRepository gatePassRepository;

    private GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest;
    private GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse;

    private ValidateCodeRequest validateCodeRequest;
    private ValidateCodeResponse validateCodeResponse;

    @BeforeEach
    public void setUp(){
        onboardResidentRequest = new OnboardResidentRequest();
        generateResidentEntryCodeRequest = new GenerateResidentEntryCodeRequest();
        validateCodeRequest = new ValidateCodeRequest();
        residentRepository.deleteAll();
    }

    @Test
    public void onboardResident() {
        onboardResidentRequest.setName("Fola");
        onboardResidentRequest.setAddress("Block A Flat 5");
        onboardResidentRequest.setEmail("fola@gmail.com");
        onboardResidentRequest.setPhone("09079358997");
        onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);
        assertTrue(residentRepository.existsResidentByEmailOrPhoneNumber("fola@gmail.com", "08079358997"));
    }

    @Test
    public void onboardResidentTwice_ExceptionIsThrown(){
        onboardResidentRequest.setName("Fola");
        onboardResidentRequest.setAddress("Block A Flat 5");
        onboardResidentRequest.setEmail("fola@gmail.com");
        onboardResidentRequest.setPhone("09079358997");
        onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);
        assertTrue(residentRepository.existsResidentByEmailOrPhoneNumber("fola@gmail.com", "08079358997"));

        onboardResidentRequest.setName("Fola");
        onboardResidentRequest.setAddress("Block A Flat 5");
        onboardResidentRequest.setEmail("fola@gmail.com");
        onboardResidentRequest.setPhone("09079358997");
        assertThrows(ResidentAlreadyExistException.class, ()->residentManagementService.onboardResident(onboardResidentRequest));
    }

    @Test
    public void onboardResident_DeleteResident_ResidentCountIsZero(){
        onboardResidentRequest.setName("Jaiye");
        onboardResidentRequest.setAddress("Block B Flat 2");
        onboardResidentRequest.setEmail("jaiye@gmail.com");
        onboardResidentRequest.setPhone("09079358992");
        onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);
        assertEquals(1, residentRepository.count());

        System.out.println(residentManagementService.deleteResident(onboardResidentResponse.getResidentId()));
        assertEquals(0, residentRepository.count());
        assertFalse(residentRepository.existsResidentByEmailOrPhoneNumber("jaiye@gmail.com", "09079358992"));
    }

    @Test
    public void onboardResident_GenerateResidentEntryCode_CodeIsValid(){
        onboardResidentRequest.setName("Fola");
        onboardResidentRequest.setAddress("Block A Flat 5");
        onboardResidentRequest.setEmail("fola@gmail.com");
        onboardResidentRequest.setPhone("09079358997");
        onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);
        assertTrue(residentRepository.existsResidentByEmailOrPhoneNumber("fola@gmail.com", "08079358997"));

        generateResidentEntryCodeRequest.setResidentId(onboardResidentResponse.getResidentId());
        generateResidentEntryCodeRequest.setValidTill(LocalTime.of(21, 40));
        generateResidentEntryCodeResponse = gateAccessService.generateResidentEntryCode(generateResidentEntryCodeRequest);
        assertEquals("ENTRY", generateResidentEntryCodeResponse.getCodeType());
        System.out.println(generateResidentEntryCodeResponse.getCode());
        System.out.println(generateResidentEntryCodeResponse);

        validateCodeRequest.setCode(generateResidentEntryCodeResponse.getCode());
        validateCodeRequest.setCodeType("ENTRY");
        validateCodeResponse = gateAccessService.validateCode(validateCodeRequest);

        assertTrue(validateCodeResponse.isValid());
        assertEquals("ENTRY", validateCodeResponse.getCodeType());
        System.out.println(validateCodeResponse);
    }

    @Test
    public void onboardResident_DisableResident_GenerateEntryCodeThrowsException(){
        onboardResidentRequest.setName("Folashade");
        onboardResidentRequest.setAddress("Block A Flat 5");
        onboardResidentRequest.setEmail("folashade@gmail.com");
        onboardResidentRequest.setPhone("08079358997");
        onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);
        assertTrue(residentRepository.existsResidentByEmailOrPhoneNumber("folashade@gmail.com", "08079358997"));

        residentManagementService.disableResident(onboardResidentResponse.getResidentId());
        Optional<Resident> foundResident = residentRepository.findByEmail("folashade@gmail.com");
        assertTrue(foundResident.isPresent());
        System.out.println(foundResident);
        assertFalse(foundResident.get().isEnabled());

        generateResidentEntryCodeRequest.setResidentId(foundResident.get().getId());
        generateResidentEntryCodeRequest.setValidTill(LocalTime.of(14,30));
        assertThrows(ResidentIsNotEnabledException.class,()-> gateAccessService.generateResidentEntryCode(generateResidentEntryCodeRequest));
    }


}
