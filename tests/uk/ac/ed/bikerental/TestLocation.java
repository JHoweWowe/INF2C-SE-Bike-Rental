package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    
    private Location location1;
    private Location location2;
    private Location location3;
    private Location location4;
    private Location location5;
    private Location location6;
    
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
        location1 = new Location("EH16 5AJ", "Chancellors Court");
        location2 = new Location("EH16 5AJ", "5 Dalkeith Road");
        location3 = new Location("EH7 6TQ", "Inchview Terrace");
        location4 = new Location("EX4 6JW", "University of Exeter");
        location5 = new Location("PH6 2PQ", "randomAddress");
        location6 = new Location("E", "sameRandomAddress");
        
        
        assertNotNull(location1);
        assertNotNull(location2);
        assertNotNull(location3);
        assertNotNull(location4);
        
    }
    
    //Should return True because both postcodes are exactly same
    @Test
    void Location1isNearToLocation2() {
        assertTrue(location1.isNearTo(location2));
    }
    
    //Should return True because first two characters of both postcodes are exactly same
    @Test
    void Location1isNearToLocation3() {
        assertTrue(location1.isNearTo(location3));
    }
    
    //Should return False because only the first character of both postcodes are the same
    @Test
    void Location1isNotNearToLocation4() {
        assertFalse(location1.isNearTo(location4));
    }
    
    //Should return False because only the postcodes for both locations are completely different
    @Test
    void Location1isNotNearToLocation5() {
        assertFalse(location1.isNearTo(location5));
    }
    
    //Should throw an IllegalArgumentException because the 6th Location postcode is invalid
    @Test
    void invalidPostCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            location1.isNearTo(location6);
        });
    }

        
}
