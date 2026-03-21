//package com.fola.data.repositories;
//
//import com.fola.data.models.Resident;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ResidentsTest {
//    private Residents residents;
//    private Resident residentOne;
//    private Resident residentTwo;
//
//    @BeforeEach
//    public void setUp() {
//        residents = new Residents();
//
//        residentOne = new Resident();
//        residentOne.setName("Folajimi Lawal");
//        residentOne.setPhoneNumber("09079358997");
//        residentOne.setHouseAddress("Block A, Flat 1");
//
//        residentTwo = new Resident();
//        residentTwo.setName("Bob Williams");
//        residentTwo.setPhoneNumber("0801234567");
//        residentTwo.setHouseAddress("Block B, Flat 10");
//    }
//
//    @Test
//    public void testToSaveAResident_ResidentsCountIs1() {
//        assertEquals(0, residents.count());
//        Resident savedResident = residents.save(residentOne);
//        assertEquals(1, residents.count());
//        assertEquals(1, savedResident.getId());
//    }
//
//    @Test
//    public void testToSaveTwoResidents_ResidentsCountIsTwo() {
//        residents.save(residentOne);
//        residents.save(residentTwo);
//        assertEquals(2, residents.count());
//        assertEquals(1, residentOne.getId());
//        assertEquals(2, residentTwo.getId());
//    }
//
//    @Test
//    public void testToFindResidentById() {
//        residents.save(residentOne);
//        Resident foundResident = residents.findById(1);
//        assertEquals("Folajimi Lawal", foundResident.getName());
//        assertEquals("Block A, Flat 1", foundResident.getHouseAddress());
//    }
//
//    @Test
//    public void testToFindResidentWithIdNotAssigned_NoResidentIsFound() {
//        Resident foundResident = residents.findById(999);
//        assertNull(foundResident);
//    }
//
//    @Test
//    public void testToSaveTwoResidents_SizeOfAllTheResidentsIs2() {
//        residents.save(residentOne);
//        residents.save(residentTwo);
//        assertEquals(2, residents.findAll().size());
//    }
//
//    @Test
//    public void testToSaveTwoResidents_DeleteOneResident_CannotFindResidentById() {
//        residents.save(residentOne);
//        residents.save(residentTwo);
//        residents.delete(residentOne);
//        assertEquals(1, residents.count());
//        assertNull(residents.findById(1));
//    }
//
//    @Test
//    public void testToDeleteResidentById_CannotFindResidentById() {
//        residents.save(residentOne);
//        residents.save(residentTwo);
//        residents.deleteById(1);
//        assertEquals(1, residents.count());
//        assertNull(residents.findById(1));
//    }
//
//    @Test
//    public void testDeleteResidentByObject() {
//        residents.save(residentOne);
//        residents.deleteByObject(residentOne);
//        assertEquals(0, residents.count());
//    }
//
//    @Test
//    public void testToSaveTwoResidents_DeleteAllResidents_ResidentsCountIsZero() {
//        residents.save(residentOne);
//        residents.save(residentTwo);
//        residents.deleteAll();
//        assertEquals(0, residents.count());
//    }
//
//    @Test
//    public void testUpdateSavedResident() {
//        residents.save(residentOne);
//        residentOne.setName("Alice Updated");
//        residentOne.setHouseAddress("Block C, Flat 15");
//        residents.save(residentOne);
//
//        Resident found = residents.findById(1);
//        assertEquals("Alice Updated", found.getName());
//        assertEquals("Block C, Flat 15", found.getHouseAddress());
//        assertEquals(1, residents.count());
//    }
//}
