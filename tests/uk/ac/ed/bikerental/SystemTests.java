package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {

    private List<Quote> allQuotes;
    private List<Quote> expectedQuotes;
    private List<DateRange> dates;
    
    private Quote quote1;
    private Quote quote2;

    private Location givenLocation;
    private Collection<Bike> bikesCollection1;
    private Collection<Bike> bikesCollection2;
    private BikeType bikeType1;
    private BikeType bikeType2;
    
    @BeforeEach
    void setUp() throws Exception {
        allQuotes = new ArrayList<Quote>();
        expectedQuotes = new ArrayList<Quote>();
        dates = new ArrayList<DateRange>();
        
        //Not sure if bikes should be ArrayList??
        bikesCollection1 = new ArrayList<Bike>();
        bikesCollection2 = new ArrayList<Bike>();
        givenLocation = new Location("EH16 5AJ", "293 Dalkeith Road");
        bikeType1 = new BikeType("Trek",new BigDecimal(600));
        bikeType2 = new BikeType("Gocycle",new BigDecimal(900));

        
        //Constructing the dates for testing purposes
        //dates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        //dates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        
        //
        Location loc1 = new Location("EH16 5AJ","101 Dalkeith Road");
        Location loc2 = new Location("PH6 2PQ", "diffRandomAddress");
        LocalTime openingHours = LocalTime.of(10, 30);
        
        //Constructing the bike providers for testing purposes
        Provider bikeProvider1 = new Provider("The Bike Station",loc1,openingHours,new BigDecimal(0.2));
        Provider bikeProvider2 = new Provider("The Bike Station",loc2,openingHours,new BigDecimal(0.2));


        bikesCollection1.add(new Bike(bikeType1,3,new BigDecimal(600),bikeProvider1,dates));
        
        //Assume each Collection of Bikes has the same bike provider
        bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,dates));
        bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(900),bikeProvider2,dates));
        
        

        
        quote1 = new Quote(bikeProvider1,bikesCollection1,new BigDecimal(600),new BigDecimal(480));
        quote2 = new Quote(bikeProvider2,bikesCollection2,new BigDecimal(1500),new BigDecimal(1200));
        
        
        
        
        
        // Setup mock delivery service before each tests
        
        //DeliveryServiceFactory.setupMockDeliveryService();
        
        
        // Put your test setup here
    }
    
    //System tests are established
    //TODO: Will need to implement more usability tests soon and define them under System testing
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
