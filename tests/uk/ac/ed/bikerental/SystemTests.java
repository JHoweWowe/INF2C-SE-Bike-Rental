package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemTests {
    
    
    // Declare List of Input quotes and Output quotes used for System testing:
    private List<Quote> inputQuotes;
    private List<Quote> expectedOutputQuotes;
    
    // Declare individual quotes to be put into Input and Output quotes:
    private Quote testQuote1;
    private Quote testQuote2;
    private Quote testQuote3;
    
    // Variables declared for the 1st collection of Bikes:
    private Collection<Bike> bikeCollection1;
    private BikeType bikeType1;
    private Provider bikeProvider1;
    private List<DateRange> bikeCollection1BookedDates;

    // Variables declared for the 2nd collection of Bikes:
    private Collection<Bike> bikeCollection2;
    private BikeType bikeType2;
    private Provider bikeProvider2;
    private List<DateRange> bikeCollection2BookedDates;
    
    // Variables declared for the 3rd collection of Bikes:
    private Collection<Bike> bikeCollection3;
    private BikeType bikeType3;
    private Provider bikeProvider3;
    private List<DateRange> bikeCollection3BookedDates;


    // Preparing requested data
    private RequestedData requestedData;
    private Location givenLocation;
    private DateRange requestedDates;
    
    //Creation of Customer and Booking objects
    private Customer customer;
    

    private Collection<Bike> bikesCollection2;
    
    private ValuationPolicy valuationPolicy;
    
    @BeforeEach
    void setUp() throws Exception {
        
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Instantiating using above format
        inputQuotes = new ArrayList<Quote>();
        expectedOutputQuotes = new ArrayList<Quote>();
        
        // Prepare data for the Collection of Bikes
        bikeCollection1 = new ArrayList<Bike>();
        //The bikeType name is "Trek" and original replacement value is 900
        bikeType1 = new BikeType("Trek",new BigDecimal(900));
        Location loc1 = new Location("EH16 5AJ","Pollock Halls");
        bikeProvider1 = new Provider("Cycles4Hire",loc1,LocalTime.of(9, 00),new BigDecimal(0.3),valuationPolicy);
        bikeCollection1BookedDates = new ArrayList<DateRange>();
        
        //bikeCollection1BookedDates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        bikeCollection1BookedDates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        
        //Assume for simplication purposes, the bike price is 900
        bikeCollection1.add(new Bike(bikeType1,3,new BigDecimal(900),bikeProvider1,bikeCollection1BookedDates));

        bikeCollection2 = new ArrayList<Bike>();
        bikeType2 = new BikeType("Gocycle",new BigDecimal(600));
        Location loc2 = new Location("IH83 5NF","Marchmont");
        bikeProvider2 = new Provider("Wheely",loc2,LocalTime.of(9, 00),new BigDecimal(0.2),valuationPolicy);
        bikeCollection2BookedDates = new ArrayList<DateRange>();
        bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2015, 1, 10)));
        //bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));

        bikeCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,bikeCollection2BookedDates));
        
        bikeCollection3 = new ArrayList<Bike>();
        bikeType3 = new BikeType("E-Bike",new BigDecimal(300));
        Location loc3 = new Location("EH8 5MY","Somewhere in Edinburgh");
        bikeProvider3 = new Provider("The Bike Station",loc3,LocalTime.of(9,00),new BigDecimal(0.1),valuationPolicy);
        bikeCollection3BookedDates = new ArrayList<DateRange>();
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2016, 1, 7), LocalDate.of(2016, 2, 10)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 31)));
        bikeCollection3.add(new Bike(bikeType3,3,new BigDecimal(300),bikeProvider3,bikeCollection3BookedDates));
        
        
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,new BigDecimal(600),new BigDecimal(480));
        testQuote2 = new Quote(bikeProvider2,bikeCollection2,new BigDecimal(600),new BigDecimal(480));
        testQuote3 = new Quote(bikeProvider3,bikeCollection3,new BigDecimal(600),new BigDecimal(480));

        //dates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        //dates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
                

        //Assume each Collection of Bikes has the same bike provider
        //bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,bikeCollection2BookedDates));
        //bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(900),bikeProvider2,bikeCollection2BookedDates));
        
        
        

        
        
        // Put your test setup here
    }
    
    
    //System tests are established as a whole
    @Test
    void doesFilterByProviderWithOneQuote() {
        givenLocation = new Location("NE42 5FJ", "Tilley Cres");
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        
        //expectedOutputQuotes should be empty- because filterByProvider method should filter that Quote based on givenLocation
        assertEquals(expectedOutputQuotes, MainSystem.filterByProvider(inputQuotes, givenLocation));  
    }
    @Test
    void doesNotFilterByProviderWithOneQuote() {
        givenLocation = new Location("EH16 5AJ", "101 Dalkeith Road");
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote1);
        
        //Following JUnit unit test case should not fail because it is near the given location
        assertTrue(expectedOutputQuotes.equals(MainSystem.filterByProvider(inputQuotes, givenLocation)));
    }
    @Test
    void filterByProviderWithMultipleQuotes() {
        givenLocation = new Location("IH83 5NF","Marchmont");
        assertNotNull(testQuote1);
        assertNotNull(testQuote2);
        assertNotNull(testQuote3);
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        
        expectedOutputQuotes.add(testQuote2);
        //The first and third quote should be removed because the 1st and 3rd provider is NOT near the given location
        //Therefore the second quote should only exist in the list of expected quotes
        //inputQuotes should have a length of 2, but expectedOutputQuotes should have length of 1
        assertEquals(expectedOutputQuotes, MainSystem.filterByProvider(inputQuotes, givenLocation));
    }
    @Test
    void doesFilterByDateWithOneQuote() {
        requestedDates = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 19));
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        
        //expectedOutputQuotes should be empty- because filterByProvider method should filter that Quote based on requestedDates
        assertEquals(expectedOutputQuotes, MainSystem.filterByDate(inputQuotes, requestedDates));
    }
    @Test
    void doesNotFilterByDateWithOneQuote() {
        requestedDates = new DateRange(LocalDate.of(2018, 1, 5), LocalDate.of(2019, 1, 4));
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        
        expectedOutputQuotes.add(testQuote1);
        //filterByProvider method should not filter that Quote based on requestedDates given- nothing is filtered
        assertEquals(expectedOutputQuotes, MainSystem.filterByDate(inputQuotes, requestedDates));
    }    
    //Currently giving me an AssertionError for filterByDate...
    @Test
    void filterByDateWithMultipleQuotes() {
        requestedDates = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 19));
        assertNotNull(testQuote1);
        assertNotNull(testQuote2);
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        expectedOutputQuotes.add(testQuote2);
        
        
        MainSystem.filterByDate(inputQuotes, requestedDates);
                
        /** The first and third quote should be removed 
        *   because the 1st and 3rd quote has the date overlaps with the requested dates
        *   Therefore the second quote should only exist in the list of expected quotes
        **/
        assertNotNull(expectedOutputQuotes);
        assertNotNull(MainSystem.filterByDate(inputQuotes, requestedDates));
        
        assertEquals(expectedOutputQuotes, MainSystem.filterByDate(inputQuotes, requestedDates));
    }
    
    /**
    @Test
    void doesNotFilterByNumTypesWithOneQuote() {
        Map<BikeType,Integer> map = new HashMap<BikeType,Integer>();
        map.put(bikeType1, 0);
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
                
        assertEquals(expectedOutputQuotes, MainSystem.filterByNumType(inputQuotes, map));
    }
    @Test
    void doesFilterByNumTypesWithOneQuote() {
        Map<BikeType,Integer> map = new HashMap<BikeType,Integer>();
        map.put(bikeType1, 1);
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote1);
                
        assertEquals(expectedOutputQuotes, MainSystem.filterByNumType(inputQuotes, map));
    }
    **/
    @Test
    void filterByNumTypesWithMultipleQuotes() {
        Map<BikeType,Integer> map = new HashMap<BikeType,Integer>();
        map.put(bikeType1, 0);
        map.put(bikeType2, 1);
        assertNotNull(testQuote1);
        assertNotNull(testQuote2);
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        expectedOutputQuotes.add(testQuote2);
        
        assertEquals(expectedOutputQuotes, MainSystem.filterByNumType(inputQuotes, map));
        
    }
    //Implements all two(will be three) filters to find the quotes which match the customer's criteria
    
    //This should give a positive quote because 
    
    /**
    @Test
    void findQuotesWhichDoesNotFilter() {
        givenLocation = new Location("EH16 5AJ", "101 Dalkeith Road");
        requestedDates = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 19));
        requestedData = new RequestedData(givenLocation, requestedDates);
        
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote1);
        
        //Assuming in this scenario, the customer hasn't made a booking yet
        customer = new Customer();
        customer.findQuote(inputQuotes, requestedData);
        
        assertEquals(expectedOutputQuotes, customer.findQuote(inputQuotes, requestedData));    
    }
    @Test
    void findQuotesWhichFiltersLocation() {
        givenLocation = new Location("EX4 5AY", "University of Exeter");
        requestedDates = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 19));
        requestedData = new RequestedData(givenLocation, requestedDates);
        
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        
        //Assuming in this scenario, the customer hasn't made a booking yet
        customer = new Customer();
        customer.findQuote(inputQuotes, requestedData);
        
        assertEquals(expectedOutputQuotes, customer.findQuote(inputQuotes, requestedData));    
    }
    
    //Should be filtered out because testQuote1 bikes is booked sometime during this period
    @Test
    void findQuotesWhichDoesFiltersRequestedDates() {
        givenLocation = new Location("EH16 5AJ", "101 Dalkeith Road");
        requestedDates = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 19));
        requestedData = new RequestedData(givenLocation, requestedDates);
        
        assertNotNull(testQuote1);
        inputQuotes.add(testQuote1);
        
        //Assuming in this scenario, the customer hasn't made a booking yet
        customer = new Customer();
        customer.findQuote(inputQuotes, requestedData);
        
        assertEquals(expectedOutputQuotes, customer.findQuote(inputQuotes, requestedData));    
    }
    **/
    
    
    
}
