package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    
    

    @BeforeEach
    void setUp() throws Exception {
        // Put setup here
    }
    
    @Test
    void LDsimpleTest() {
        assertEquals(630, 900 - 3 * 0.1 * 900);
    }
    
    // Basic unit tests and standard assertions are grouped together in one method
    // TODO: Could perhaps divide the methods up
    @Test
    void standardAssertions() {
    	
    }
    
    // Group assertions
    
    // TODO: Write tests for valuation policies
}
