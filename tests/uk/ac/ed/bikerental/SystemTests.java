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
    private List<Quote> filteredQuotes;
    private Quote quote1;
    private Quote quote2;
    private List<DateRange> dates;
    private Location givenLocation;
    private Collection<Bike> bikesCollection1;
    private Collection<Bike> bikesCollection2;
    private BikeType bikeType1;
    private BikeType bikeType2;
    
    @BeforeEach
    void setUp() throws Exception {
        allQuotes = new ArrayList<Quote>();
        filteredQuotes = new ArrayList<Quote>();
        
        
        dates = new ArrayList<DateRange>();
        //Not sure if bikes should be ArrayList??
        bikesCollection1 = new ArrayList<Bike>();
        bikesCollection2 = new ArrayList<Bike>();
        givenLocation = new Location("EH16 5AJ", "293 Dalkeith Road");
        bikeType1 = new BikeType(new BigDecimal(600));
        bikeType2 = new BikeType(new BigDecimal(900));

        
        //Constructing the dates for testing purposes
        dates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        dates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        dates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        dates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        
        //
        Location loc1 = new Location("EH16 5AJ","101 Dalkeith Road");
        Location loc2 = new Location("EH16 5AJ", "diffRandomAddress");
        Location loc3 = new Location("PH6 2PQ", "randomAddress");
        LocalTime openingHours1 = LocalTime.of(10, 30);
        
        //Constructing the bike providers for testing purposes
        Provider bikeProvider1 = new Provider("The Bike Station",loc1,openingHours1,new BigDecimal(0.2));
        Provider bikeProvider2 = new Provider("The Bike Station",loc2,openingHours1,new BigDecimal(0.2));
        Provider bikeProvider3 = new Provider("The Bike Station",loc3,openingHours1,new BigDecimal(0.2));


        bikesCollection1.add(new Bike(bikeType1,3,new BigDecimal(600),bikeProvider1,dates));
        bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,dates));
        bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(900),bikeProvider3,dates));
        
        

        
        quote1 = new Quote(bikeProvider1,bikesCollection1,new BigDecimal(600),new BigDecimal(480));
        quote2 = new Quote(bikeProvider2,bikesCollection2,new BigDecimal(1500),new BigDecimal(1200));
        
        
        
        
        
        // Setup mock delivery service before each tests
        
        //DeliveryServiceFactory.setupMockDeliveryService();
        
        
        // Put your test setup here
    }
    
    //Unit tests are established in the first use case:
    @Test
    void filterProvider() {
        assertNotNull(quote1);
        //Should have only 1 quote
        allQuotes.add(quote1);
        filteredQuotes.add(quote1);
        assertEquals(filteredQuotes,MainSystem.filterProvider(allQuotes, givenLocation));
        
    }
}
