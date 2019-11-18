package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    
    private LinearDepreciationValuationPolicy ld;
    private DoubleDepreciationValuationPolicy dd;
    
    private Provider bikeProvider;
    private Location location;
    private LocalTime openingHours;
    
    private Bike bike;
    private BikeType bikeType;
    private BigDecimal depositRate;
    private BigDecimal bikePrice;
    private int bikeAgeInYears;
    private List<DateRange> dates;
    
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
        openingHours = LocalTime.of(10,30);
        location = new Location("EH16 5AY", "Chancellors Court");
        depositRate = new BigDecimal(0.2);
        
        bikeType = new BikeType("Trek",new BigDecimal(900));
        bikeAgeInYears = 3;
        bikePrice = new BigDecimal(200);
        dates = new ArrayList<DateRange>();
        dates.add(new DateRange(LocalDate.of(2019, 10, 15),LocalDate.of(2019, 11, 15)));
        
        bikeProvider = new Provider("The Bike Station",location,openingHours,depositRate);
        bike = new Bike(bikeType,bikeAgeInYears,bikePrice,bikeProvider,dates);
        
        ld = new LinearDepreciationValuationPolicy(new BigDecimal(0.1));
        dd = new DoubleDepreciationValuationPolicy(new BigDecimal(0.1));
        localDate = LocalDate.now();
        
        assertNotNull(ld);
        assertNotNull(dd);
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
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertEquals(new BigDecimal("460.80"), dd.calculateValue(bike, localDate));
        
    }
    
    /** 
     * TODO: Throw a NegativeArgumentException when calculating the value of the Bike is negative [ROHAN]
     * TODO: Rate should between 0-1, any other value should throw some kind of Exception (your job to figure it out)
     */

    
}
