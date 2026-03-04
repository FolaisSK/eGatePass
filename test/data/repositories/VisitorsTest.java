package data.repositories;

import data.models.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorsTest {
    private Visitors visitors;
    private Visitor visitorOne;
    private Visitor visitorTwo;

    @BeforeEach
    public void setUp(){
        visitors = new Visitors();

        visitorOne = new Visitor();
        visitorOne.setName("Folajimi Lawal");
        visitorOne.setPhoneNumber("09079358997");
        visitorOne.setPurposeOfVisit("Business Meeting");

        visitorTwo = new Visitor();
        visitorTwo.setName("John Doe");
        visitorTwo.setPhoneNumber("08012345678");
        visitorTwo.setPurposeOfVisit("Family Visit");
    }
    @Test
    public void testToSaveAVisitor_VisitorsCountIs1(){
        assertEquals(0, visitors.count());
        Visitor savedVisitor = visitors.save(visitorOne);
        assertEquals(1, visitors.count());
        assertEquals(1, savedVisitor.getId());
    }

    @Test
    public void testToSaveTwoVisitors_VisitorsCountIsTwo(){
        assertEquals(0, visitors.count());
        Visitor firstSavedVisitor = visitors.save(visitorOne);
        Visitor secondSavedVisitor = visitors.save(visitorTwo);
        assertEquals(2, visitors.count());
        assertEquals(1, firstSavedVisitor.getId());
        assertEquals(2, secondSavedVisitor.getId());
    }

    @Test
    public void testToFindVisitorById(){
        assertEquals(0, visitors.count());
        Visitor foundVisitor = visitors.save(visitorOne);
        assertEquals(1, foundVisitor.getId());
        assertEquals("Folajimi Lawal", foundVisitor.getName());
        assertEquals("Business Meeting", foundVisitor.getPurposeOfVisit());
        assertEquals(foundVisitor, visitors.findById(1));
    }

    @Test
    public void testToFindVisitorWithIdNotAssigned_NoVisitorIsFound() {
        Visitor foundVisitor = visitors.findById(999);
        assertNull(foundVisitor);
    }

    @Test
    public void testToSaveOneVisitors_DeleteOneVisitor_CannotFindVisitorById() {
        assertEquals(0, visitors.count());
        Visitor firstSavedVisitor = visitors.save(visitorOne);
        visitors.delete(firstSavedVisitor);
        assertEquals(0, visitors.count());
        assertNull(visitors.findById(1));
    }

    @Test
    public void testToSaveTwoVisitors_DeleteOneVisitorById_CannotFindVisitorById() {
        Visitor firstSavedVisitor = visitors.save(visitorOne);
        Visitor secondSavedVisitor = visitors.save(visitorTwo);
        assertEquals(1, firstSavedVisitor.getId());
        assertEquals(2, secondSavedVisitor.getId());
        visitors.deleteById(firstSavedVisitor.getId());
        assertEquals(1, visitors.count());
        Visitor firstFoundVisitor = visitors.findById(1);
        assertNull(firstFoundVisitor);
    }

    @Test
    public void testDeleteVisitorByObject() {
        Visitor savedVisitor = visitors.save(visitorOne);
        assertEquals(1, visitors.count());
        visitors.deleteByObject(savedVisitor);
        assertEquals(0, visitors.count());
        assertNull(visitors.findById(1));
    }

    @Test
    public void testToSaveTwoVisitors_DeleteAllVisitors_VisitorsCountIsZero() {
        visitors.save(visitorOne);
        visitors.save(visitorTwo);
        assertEquals(2, visitors.count());
        visitors.deleteAll();
        assertEquals(0, visitors.count());
        assertNull(visitors.findById(1));
        assertNull(visitors.findById(2));
    }

    @Test
    public void testUpdateSavedVisitor() {
        Visitor savedVisitor = visitors.save(visitorOne);
        assertEquals(1, visitors.count());
        savedVisitor.setName("Lagbaja Updated");
        savedVisitor.setPurposeOfVisit("Official Meeting");
        visitors.save(savedVisitor);
        Visitor foundVisitor = visitors.findById(1);
        assertEquals("Lagbaja Updated", foundVisitor.getName());
        assertEquals("Official Meeting", foundVisitor.getPurposeOfVisit());
        assertEquals(1, visitors.count());
    }
}
