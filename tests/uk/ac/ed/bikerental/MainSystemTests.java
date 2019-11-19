package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class SystemTests {

    //Testing using 2 quotes: one has overlapping dates; the other does not
    // Each has 1 bike
    // Preparing first bike:

    private Collection<Bike> bikeForQuote1;
    private BikeType bike1Type;
    private Provider bike1Provider;
    private List<DateRange> bike1Booked;

    // Preparing first bike:
    private Collection<Bike> bikeForQuote2;
    private BikeType bike2Type;
    private Provider bike2Provider;
    private List<DateRange> bike2Booked;

    // Preparing requested data
    private Location requestedLocation;
    private DateRange requestedDates;

    // Preparing input & output quotes:
    private List<Quote> unfilteredQuotes;
    private List<Quote> trueQuotes;
    
    

    
    
    
    @BeforeEach
    void setUp() throws Exception {

        // Instantiating using above format
        bikeForQuote1 = new ArrayList<Bike>();
        bike1Type = new BikeType(new BigDecimal(399));
        Location loc1 = new Location("EH54 9WN","Leith");
        bike1Provider = new Provider("Cycles4Hire",loc1,LocalTime.of(9, 00),new BigDecimal(0.3));
        bike1Booked = new ArrayList<DateRange>();
        bike1Booked.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        bike1Booked.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));

        bikeForQuote2 = new ArrayList<Bike>();
        bike2Type = new BikeType(new BigDecimal(600));
        Location loc2 = new Location("IH83 5NF","Marchmont");
        bike2Provider = new Provider("Wheely",loc2,LocalTime.of(9, 00),new BigDecimal(0.2));
        bike2Booked = new ArrayList<DateRange>();
        bike2Booked.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        bike2Booked.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        
        unfilteredQuotes = new ArrayList<Quote>();
        trueQuotes = new ArrayList<Quote>();
        

        bikeForQuote1.add(new Bike(bike1Type,3,new BigDecimal(399),bike1Provider,bike1Booked));
        bikeForQuote2.add(new Bike(bike2Type,3,new BigDecimal(600),bike2Provider,bike2Booked));
        
        
        

        
        Quote testquote1 = new Quote(bikeProvider1,bikesCollection1,new BigDecimal(600),new BigDecimal(480));
        Quote testquote2 = new Quote(bikeProvider2,bikesCollection2,new BigDecimal(1500),new BigDecimal(1200));
        
        
        
        
        
        // Setup mock delivery service before each tests
        
        //DeliveryServiceFactory.setupMockDeliveryService();
        
        
        // Put your test setup here
    }
    
    //Unit tests are established in the first use case:
    @Test
    void filterByProvider() {
        assertNotNull(quote1);
        allQuotes.add(quote1);
        expectedQuotes.add(quote1);
        //Following JUnit unit test case should not fail because it is near the given location
        assertEquals(expectedQuotes,MainSystem.filterByProvider(allQuotes, givenLocation));
        
        allQuotes.add(quote2);
        //The second quote should be removed because the 2nd provider is near the given location
        //Therefore the first quote should only exist in the list of expected quotes
        assertEquals(expectedQuotes,MainSystem.filterByProvider(allQuotes, givenLocation));
    }
    
    //TODO: To be filled out
    @Test
    void filterByDate() {
        
    }
}