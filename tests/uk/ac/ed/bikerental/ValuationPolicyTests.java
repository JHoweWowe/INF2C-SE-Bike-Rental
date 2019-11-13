package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    
    private BikeWithLDValuationPolicy bikeLD;
    private BikeWithDDValuationPolicy bikeDD;
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
        bikeLD = new BikeWithLDValuationPolicy();
        bikeDD = new BikeWithDDValuationPolicy();
        localDate = LocalDate.now();
        
        assertNotNull(bikeLD);
        assertNotNull(bikeDD);
        assertNotNull(localDate);
    }

    @Test
    @DisplayName("Linear Depreciation Case Test")
    void LDCaseTest1() {
        bikeLD = new BikeWithLDValuationPolicy(new BigDecimal(900), 3, 0.1);
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertEquals(new BigDecimal("630.00"), bikeLD.calculateValue(bikeLD, localDate));
    }
    
    @Test
    @DisplayName("Double Declining Balance Depreciation Case Test")
    void DDCaseTest1() {
        bikeDD = new BikeWithDDValuationPolicy(new BigDecimal(900), 3, 0.1);
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertEquals(new BigDecimal("460.80"), bikeDD.calculateValue(bikeDD, localDate));
    }
    
    // TODO: Group Assertions needed for covering a generic scenario?

    
}
