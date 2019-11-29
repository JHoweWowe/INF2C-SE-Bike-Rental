package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    
    private ValuationPolicy valuationPolicy;
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
     */
    
    @BeforeEach
    void setUp() throws Exception {
        openingHours = LocalTime.of(10,30);
        location = new Location("EH16 5AY", "Chancellors Court");
        depositRate = new BigDecimal(0.2);
        
        //BikeType name is Trek, has an original replacement value of 900 and price of 200
        bikeType = new BikeType("Trek",new BigDecimal(900),new BigDecimal(200));
        localDate = LocalDate.now();
        bikeAgeInYears = 3;
        dates = new ArrayList<DateRange>();
        dates.add(new DateRange(LocalDate.of(2019, 10, 15),LocalDate.of(2019, 11, 15)));
        
        //Considering in this scenario, the bikeProvider has a null pricing policy
        bikeProvider = new Provider("The Bike Station",location,openingHours,depositRate,valuationPolicy,null);
        bike = new Bike(bikeType,bikeAgeInYears,bikeProvider,dates);
        
        dd = new DoubleDepreciationValuationPolicy(new BigDecimal(0.1));

        

        assertNotNull(dd);
        assertNotNull(localDate);
    }
    
    @Test
    @DisplayName("Linear Depreciation Case Test #1")
    void LDCaseTest1() {
        BigDecimal depreciationRate = new BigDecimal(0.1);
        ld = new LinearDepreciationValuationPolicy(depreciationRate);
        bike = new Bike(bikeType,bikeAgeInYears,bikeProvider,dates);

        assertNotNull(ld);
        
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertEquals(new BigDecimal("630.00").stripTrailingZeros(), ld.calculateValue(bike, localDate).stripTrailingZeros());
    }
    
    @Test
    @DisplayName("Linear Depreciation rate must be between 0 and 1")
    void invalidLinearDepreciationRate() {
        BigDecimal depreciationRate = new BigDecimal(-0.01);
        ld = new LinearDepreciationValuationPolicy(depreciationRate);
        assertNotNull(ld);
        
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertThrows(IllegalArgumentException.class, () -> {
            ld.calculateValue(bike, localDate);
        });
    }
    
    @Test
    @DisplayName("The value of the bike with the linear depreciation rate cannot be negative")
    void invalidLLcalculatedValue() {
        BigDecimal depreciationRate = new BigDecimal(1);
        ld = new LinearDepreciationValuationPolicy(depreciationRate);
        assertNotNull(ld);
        
        stringDate = "1999-08-16";
        localDate = LocalDate.parse(stringDate);
        bike = new Bike(bikeType,20,bikeProvider,dates);
        assertThrows(IllegalArgumentException.class, () -> {
            ld.calculateValue(bike, localDate);
        });
    }
    @Test
    @DisplayName("Double Declining Balance Depreciation rate is not between 0 and 1")
    void invalidDoubleDecliningBalanceDepreciationRate() {
        BigDecimal depreciationRate = new BigDecimal(-0.1);
        dd = new DoubleDepreciationValuationPolicy(depreciationRate);
        
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertThrows(IllegalArgumentException.class, () -> {
            dd.calculateValue(bike, localDate);
        });
    }
    
    @Test
    @DisplayName("Double Declining Balance Depreciation Case Test")
    void DDCaseTest1() {
        stringDate = "2016-08-16";
        localDate = LocalDate.parse(stringDate);
        assertEquals(new BigDecimal("460.80").stripTrailingZeros(), dd.calculateValue(bike, localDate).stripTrailingZeros());
    }

}
