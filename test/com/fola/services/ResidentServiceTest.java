//package com.fola.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class ResidentServiceTest {
//    private ResidentServiceImpl residentService;
//    @BeforeEach
//    public void setUp(){
//        residentService = new ResidentServiceImpl();
//    }
//
//    @Test
//    public void RegisterResident_ResidentsCountIsOne(){
//        assertEquals(0, residentService.getResidentsCount());
//        String resident = residentService.registerResident("Fola", "09079358997", "Block A Flat4");
//        assertEquals("FolaRegistered successfully!", resident);
//        assertEquals(1, residentService.getResidentsCount());
//    }
//
//    @Test
//    public void RegisterTwoResidentsWithSamePhoneNumber_ExceptionIsThrown(){
//        assertEquals(0, residentService.getResidentsCount());
//        String resident = residentService.registerResident("Fola", "09079358997", "Block A Flat4");
//        assertEquals(1, residentService.getResidentsCount());
//        assertEquals("FolaRegistered successfully!", resident);
//        assertThrows(IllegalArgumentException.class, ()-> residentService.registerResident("Bimbo", "09079358997", "Block B Flat2"));
//        assertEquals(1, residentService.getResidentsCount());
//    }
//
//    @Test
//    public void RegisterOneResident_UpdateResident_ResidentsCountIsOne(){
//        assertEquals(0, residentService.getResidentsCount());
//        String resident = residentService.registerResident("Fola", "09079358997", "Block A Flat4");
//        assertEquals(1, residentService.getResidentsCount());
//        assertEquals("FolaRegistered successfully!", resident);
//        String updatedResident = residentService.updateResident("Fola", "09012345678", "Block C Flat 6");
//        assertEquals(1, residentService.getResidentsCount());
//        assertEquals("LawalUpdated successfully!", resident);
//    }
//
//    @Test
//    public void RegisterOneResident_DeleteResident_ResidentsCountIsZeor(){
//        assertEquals(0, residentService.getResidentsCount());
//        String resident = residentService.registerResident("Fola", "09079358997", "Block A Flat4");
//        assertEquals(1, residentService.getResidentsCount());
//        assertEquals("FolaRegistered successfully!", resident);
//        residentService.deleteResident("09079358997");
//        assertEquals(0, residentService.getResidentsCount());
//    }
//
//    @Test
//    public void RegisterTwoResidents_DeleteAllResidents_ResidentsCountIsZero(){
//        assertEquals(0, residentService.getResidentsCount());
//        String resident = residentService.registerResident("Fola", "09079358997", "Block A Flat4");
//        assertEquals(1, residentService.getResidentsCount());
//        assertEquals("FolaRegistered successfully!", resident);
//        String residentTwo = residentService.registerResident("Flash", "09012345678", "Block B Flat4");
//        assertEquals(2, residentService.getResidentsCount());
//        assertEquals("FlashRegistered successfully!", residentTwo);
//        residentService.deleteAllResidents();
//        assertEquals(0, residentService.findAllResidents().size());
//    }
//}
