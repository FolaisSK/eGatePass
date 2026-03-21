package com.fola.services;

import com.fola.data.repositories.GatePassRepository;
import com.fola.dtos.requests.GenerateResidentEntryCodeRequest;
import com.fola.dtos.responses.GenerateResidentEntryCodeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GateAccessServiceTest {
    @Autowired
    private GateAccessService gateAccessService;
    @Autowired
    private GatePassRepository gatePassRepository;

    private GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest;
    private GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse;

    @BeforeEach
    public void setUp(){
        generateResidentEntryCodeRequest = new GenerateResidentEntryCodeRequest();
    }


}
