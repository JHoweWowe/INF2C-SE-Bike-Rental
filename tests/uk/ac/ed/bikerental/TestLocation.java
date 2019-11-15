package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
    }
    
    //Should return True
    @Test
    void hasSameLocation() {
        Location l1 = new Location("EH16 5AJ", "randomAddress");
        Location l2 = new Location("EH16 5AJ", "diffRandomAddress");
        assertTrue(l1.isNearTo(l2));
    }
    
}
