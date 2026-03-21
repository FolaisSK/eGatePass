//package com.fola.data.repositories;
//
//import com.fola.data.models.GatePass;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class GatePassesTest {
//    private GatePassRepository gatePasses;
//    private GatePass gatePassOne;
//    private GatePass gatePassTwo;
//
//    @BeforeEach
//    public void setUp(){
//        gatePasses = new GatePasses();
//
//        gatePassOne = new GatePass();
//        gatePassOne.setResidentId(1);
//        gatePassOne.setVisitorsId(101);
//        gatePassOne.setExpirationDate(LocalDateTime.now().plusDays(1));
//
//        gatePassTwo = new GatePass();
//        gatePassTwo.setResidentId(2);
//        gatePassTwo.setVisitorsId(69);
//        gatePassTwo.setExpirationDate(LocalDateTime.now().plusDays(2));
//    }
//
//    @Test
//    public void testToSaveAGatePass_GatePassesCountIs1(){
//        assertEquals(0, gatePasses.count());
//        GatePass savedPass = gatePasses.save(gatePassOne);
//        assertEquals(1, gatePasses.count());
//        assertEquals(1, savedPass.getId());
//        assertTrue(savedPass.isValid());
//        assertNotNull(savedPass.getCreatedAt());
//    }
//
//    @Test
//    public void testToSaveTwoGatePasses_GatePassesCountIsTwo(){
//        GatePass firstSavedPass = gatePasses.save(gatePassOne);
//        GatePass secondSavedPass = gatePasses.save(gatePassTwo);
//
//        assertEquals(2, gatePasses.count());
//        assertEquals(1, firstSavedPass.getId());
//        assertEquals(2, secondSavedPass.getId());
//    }
//
//    @Test
//    public void testToFindGatePassById(){
//        GatePass savedPass = gatePasses.save(gatePassOne);
//        GatePass foundPass = gatePasses.findById(1);
//        assertNotNull(foundPass);
//        assertEquals(savedPass, foundPass);
//        assertEquals(1, foundPass.getResidentId());
//        assertEquals(101, foundPass.getVisitorsId());
//    }
//
//    @Test
//    public void testToFindGatePassWithIdNotAssigned_NoGatePassIsFound() {
//        GatePass foundPass = gatePasses.findById(999);
//        assertNull(foundPass);
//    }
//
//    @Test
//    public void testToSaveOneGatePass_DeleteGatePass_CannotFindGatePassById() {
//        GatePass savedPass = gatePasses.save(gatePassOne);
//        gatePasses.delete(savedPass);
//        assertEquals(0, gatePasses.count());
//        assertNull(gatePasses.findById(1));
//    }
//
//    @Test
//    public void testToSaveTwoGatePasses_DeleteGatePassById_CannotFindGatePassById() {
//        GatePass firstSavedPass = gatePasses.save(gatePassOne);
//        gatePasses.save(gatePassTwo);
//        gatePasses.deleteById(firstSavedPass.getId());
//        assertEquals(1, gatePasses.count());
//        assertNull(gatePasses.findById(1));
//    }
//
//    @Test
//    public void testDeleteGatePassByObject() {
//        GatePass savedPass = gatePasses.save(gatePassOne);
//        gatePasses.deleteByObject(savedPass);
//        assertEquals(0, gatePasses.count());
//        assertNull(gatePasses.findById(1));
//    }
//
//    @Test
//    public void testToSaveTwoGatePasses_DeleteAllGatePasses_GatePassesCountIsZero() {
//        gatePasses.save(gatePassOne);
//        gatePasses.save(gatePassTwo);
//        gatePasses.deleteAll();
//        assertEquals(0, gatePasses.count());
//        assertNull(gatePasses.findById(1));
//        assertNull(gatePasses.findById(2));
//    }
//
//
//}
