package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    
    

    @BeforeEach
    void setUp() throws Exception {
        // Put setup here
    }
    
    @Test
    void LDCaseTest1() {
        BikeWithLDValuationPolicy bike = new BikeWithLDValuationPolicy(new BigDecimal(900), 3, 0.1);
        String date = "2016-08-16";
        LocalDate localDate = LocalDate.parse(date);
        assertEquals(new BigDecimal("630.00"), bike.calculateValue(bike, localDate));
    }
    
    @Test
    void DDCaseTest1() {
        BikeWithDDValuationPolicy bike = new BikeWithDDValuationPolicy(new BigDecimal(900), 3, 0.1);
        String date = "2016-08-16";
        LocalDate localDate = LocalDate.parse(date);
        assertEquals(new BigDecimal("460.80"), bike.calculateValue(bike, localDate));
    }
    
    // Basic unit tests and standard assertions are grouped together in one method
    // TODO: Could perhaps divide the methods up
    @Test
    void standardAssertions() {
    	
    }
    
    // Group assertions
    
    // TODO: Write tests for valuation policies
}
