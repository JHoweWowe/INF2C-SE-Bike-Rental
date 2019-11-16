package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    
    private Location l1;
    private Location l2;
    private Location l3;
    
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
        l1 = new Location("EH16 5AJ", "randomAddress");
        l2 = new Location("EH16 5AJ", "diffRandomAddress");
        l3 = new Location("PH6 2PQ", "randomAddress");
        
        assertNotNull(l1);
        assertNotNull(l2);
        assertNotNull(l3);
        
    }
    
    //Should return True
    @Test
    void hasSameLocation() {
        assertTrue(l1.isNearTo(l2));
    }
    
    //Should return False
    @Test
    void hasDifferrentLocation() {
        assertFalse(l2.isNearTo(l3));
    }
    
}
