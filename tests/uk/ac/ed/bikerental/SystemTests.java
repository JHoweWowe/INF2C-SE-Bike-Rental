package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SystemTests {
    
    //Testing using 2 quotes: one has overlapping dates; the other does not
    // Each has 1 bike
    
    // Variables declared for the 1st collection of Bikes:
    private Collection<Bike> bikeCollection1;
    private BikeType bikeType1;
    private Provider bikeProvider1;
    private List<DateRange> bikeCollection1BookedDates;

    // Variables declared for the 2nd collection of Bikes:
    private Collection<Bike> bikeCollection2;
    private BikeType bike2Type;
    private Provider bike2Provider;
    private List<DateRange> bikeCollection2BookedDates;
    
    // Variables declared for the 3rd collection of Bikes:
    private Collection<Bike> bikeCollection3;
    private BikeType bike3Type;
    private Provider bike3Provider;
    private List<DateRange> bikeCollection3BookedDates;


    
    // Variables declared for the fourth bike:

    // Preparing requested data
    private Location requestedLocation;
    private DateRange requestedDates;

    // Preparing input & output quotes:
    private List<Quote> unfilteredQuotes;
    private List<Quote> trueQuotes;

    private List<Quote> allQuotes;
    private List<Quote> expectedQuotes;
    
    private Quote quote1;
    private Quote quote2;
    private Quote testQuote1;
    private Quote testQuote2;

    private Location givenLocation;

    private Collection<Bike> bikesCollection2;
    private BikeType bikeType2;
    
    @BeforeEach
    void setUp() throws Exception {
        
        // Instantiating using above format
        bikeCollection1 = new ArrayList<Bike>();
        bikeType1 = new BikeType("Trek",new BigDecimal(399));
        Location loc1 = new Location("EH54 9WN","Leith");
        bikeProvider1 = new Provider("Cycles4Hire",loc1,LocalTime.of(9, 00),new BigDecimal(0.3));
        bikeCollection1BookedDates = new ArrayList<DateRange>();
        bikeCollection1BookedDates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        bikeCollection1BookedDates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));

        bikeCollection2 = new ArrayList<Bike>();
        bike2Type = new BikeType("Gocycle",new BigDecimal(600));
        Location loc2 = new Location("IH83 5NF","Marchmont");
        bike2Provider = new Provider("Wheely",loc2,LocalTime.of(9, 00),new BigDecimal(0.2));
        bikeCollection2BookedDates = new ArrayList<DateRange>();
        bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        
        unfilteredQuotes = new ArrayList<Quote>();
        trueQuotes = new ArrayList<Quote>();
        

        bikeCollection1.add(new Bike(bikeType1,3,new BigDecimal(399),bikeProvider1,bikeCollection1BookedDates));
        bikeCollection2.add(new Bike(bike2Type,3,new BigDecimal(600),bike2Provider,bikeCollection2BookedDates));
        
        
        

        
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,new BigDecimal(600),new BigDecimal(480));
        testQuote2 = new Quote(bike2Provider,bikesCollection2,new BigDecimal(1500),new BigDecimal(1200));

        //dates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        //dates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        
        
        
        allQuotes = new ArrayList<Quote>();
        expectedQuotes = new ArrayList<Quote>();
        bikeCollection3BookedDates = new ArrayList<DateRange>();
        
        //Not sure if bikes should be ArrayList??
        bikeCollection3 = new ArrayList<Bike>();
        bikesCollection2 = new ArrayList<Bike>();
        givenLocation = new Location("EH16 5AJ", "293 Dalkeith Road");
        bike3Type = new BikeType("Trek",new BigDecimal(600));
        bikeType2 = new BikeType("Gocycle",new BigDecimal(900));
        
        //
        Location location1 = new Location("EH16 5AJ","101 Dalkeith Road");
        Location location2 = new Location("PH6 2PQ", "diffRandomAddress");
        LocalTime openingHours = LocalTime.of(10, 30);
        
        //Constructing the bike providers for testing purposes
        Provider bikeProvider1 = new Provider("The Bike Station",loc1,openingHours,new BigDecimal(0.2));
        Provider bikeProvider2 = new Provider("The Bike Station",location2,openingHours,new BigDecimal(0.2));


        bikeCollection3.add(new Bike(bike3Type,3,new BigDecimal(600),bikeProvider1,bikeCollection3BookedDates));
        
        //Assume each Collection of Bikes has the same bike provider
        bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,bikeCollection3BookedDates));
        bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(900),bikeProvider2,bikeCollection3BookedDates));
        
        

        
        quote1 = new Quote(bikeProvider1,bikeCollection1,new BigDecimal(600),new BigDecimal(480));
        quote2 = new Quote(bikeProvider2,bikesCollection2,new BigDecimal(1500),new BigDecimal(1200));
        

        
        
        
        // Setup mock delivery service before each tests
        
        //DeliveryServiceFactory.setupMockDeliveryService();
        
        
        // Put your test setup here
    }
    
    
    //System tests are established
    //TODO: Will need to implement more usability tests soon and define them under System testing
    @Test
    void filterByProvider() {
        assertNotNull(testQuote1);
        allQuotes.add(testQuote1);
        expectedQuotes.add(testQuote1);
        //Following JUnit unit test case should not fail because it is near the given location
        assertEquals(expectedQuotes,MainSystem.filterByProvider(allQuotes, givenLocation));
        assertTrue(expectedQuotes.equals(MainSystem.filterByProvider(allQuotes, givenLocation)));
        
        allQuotes.add(testQuote2);
        expectedQuotes.add(testQuote2);
        //The second quote should be removed because the 2nd provider is near the given location
        //Therefore the first quote should only exist in the list of expected quotes- expectedQuotes has length of 2, but all quotes filtered is 1
        assertFalse(expectedQuotes.equals(MainSystem.filterByProvider(allQuotes, givenLocation)));
        
        expectedQuotes.remove(testQuote2);
        assertEquals(expectedQuotes,MainSystem.filterByProvider(allQuotes, givenLocation));
        assertTrue(expectedQuotes.equals(MainSystem.filterByProvider(allQuotes, givenLocation)));

    }
    
    //TODO: To be filled out- still not functioning properly; doesn't consider multiple implementations of 
    @Test
    void filterByDateFalse() {
        requestedDates = new DateRange(LocalDate.of(2018, 1, 5), LocalDate.of(2019, 1, 23));
        assertNotNull(testQuote1);
        unfilteredQuotes.add(testQuote1);
        trueQuotes.add(testQuote1);
        assertFalse(trueQuotes.equals(MainSystem.filterByDate(unfilteredQuotes, requestedDates)));
    }
    
    //WRONG IMPLEMENTATION- SHOULD BE FALSE BUT ITS TRUE
    void filterByDateT() {
        requestedDates = new DateRange(LocalDate.of(2015, 1, 10), LocalDate.of(2015, 1, 23));
        assertNotNull(testQuote2);
        unfilteredQuotes.add(testQuote2);
        trueQuotes.add(testQuote2);
        assertEquals(trueQuotes, MainSystem.filterByDate(unfilteredQuotes, requestedDates));
    }
    
}
