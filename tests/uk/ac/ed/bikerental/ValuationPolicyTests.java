package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    
    protected LinearDepreciationValuationPolicy ld;
    protected Bike bike;
    
    private String stringDate;
    private LocalDate localDate;
    
    /**
     * Test environment must be setup properly. Classes should be initialised properly.
     * Bikes with each respective valuation policy should be constructed with default values.
     * Default localDate value should be set to current date & time for simple purposes
     * 
     * TODO: Identify what values/fields should @throws Exception (Perhaps negative values?)
     * 
     */
    
    @BeforeEach
    void setUp() throws Exception {
        bike = new Bike(new BikeType(new BigDecimal(900)),3);
        ld = new LinearDepreciationValuationPolicy(new BigDecimal(0.1));
        localDate = LocalDate.now();
        
        assertNotNull(ld);
        assertNotNull(localDate);
    }
    
    //TODO: Further testing can be implemented where the value may result in a negative value to throw Exception
    @Test
    @DisplayName("Linear Depreciation Case Test #1")
    void LDCaseTest1() {
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertEquals(new BigDecimal("630.00"), ld.calculateValue(bike, localDate));
    }
    
    @Test
    @DisplayName("Double Declining Balance Depreciation Case Test")
    void DDCaseTest1() {

    }
    
    /** 
     * TODO: Throw a NegativeArgumentException when calculating the value of the Bike is negative [ROHAN]
     * TODO: Rate should between 0-1, any other value should throw some kind of Exception (your job to figure it out)
     */

    
}
