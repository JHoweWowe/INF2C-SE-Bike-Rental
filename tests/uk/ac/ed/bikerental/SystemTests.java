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


    // Preparing requested data
    private RequestedData requestedData;
    private Location givenLocation;
    private DateRange requestedDates;
    
    //Creation of Customer and Booking objects
    private Customer customer;
    

    private Collection<Bike> bikesCollection2;
    private BikeType bikeType2;
    
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
        bike2Type = new BikeType("Gocycle",new BigDecimal(600));
        Location loc2 = new Location("IH83 5NF","Marchmont");
        bike2Provider = new Provider("Wheely",loc2,LocalTime.of(9, 00),new BigDecimal(0.2),valuationPolicy);
        bikeCollection2BookedDates = new ArrayList<DateRange>();
        bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        //bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        

        


        bikeCollection2.add(new Bike(bike2Type,3,new BigDecimal(600),bike2Provider,bikeCollection2BookedDates));
        
        
        
        
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,new BigDecimal(600),new BigDecimal(480));
        testQuote2 = new Quote(bike2Provider,bikesCollection2,new BigDecimal(1500),new BigDecimal(1200));

        //dates.add(new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        //dates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10)));
        //dates.add(new DateRange(LocalDate.of(2016, 2, 15), LocalDate.of(2017, 3, 30)));
        
        
        

        bikeCollection3BookedDates = new ArrayList<DateRange>();
        
        //Not sure if bikes should be ArrayList??
        bikeCollection3 = new ArrayList<Bike>();
        bikesCollection2 = new ArrayList<Bike>();
        bike3Type = new BikeType("Trek",new BigDecimal(600));
        bikeType2 = new BikeType("Gocycle",new BigDecimal(900));
        
        //
        Location location1 = new Location("EH16 5AJ","101 Dalkeith Road");
        Location location2 = new Location("PH6 2PQ", "diffRandomAddress");
        LocalTime openingHours = LocalTime.of(10, 30);
        
        //Constructing the bike providers for testing purposes
        Provider bikeProvider1 = new Provider("The Bike Station",loc1,openingHours,new BigDecimal(0.2),valuationPolicy);
        Provider bikeProvider2 = new Provider("The Bike Station",location2,openingHours,new BigDecimal(0.2),valuationPolicy);


        bikeCollection3.add(new Bike(bike3Type,3,new BigDecimal(600),bikeProvider1,bikeCollection3BookedDates));
        
        //Assume each Collection of Bikes has the same bike provider
        //bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,bikeCollection2BookedDates));
        //bikesCollection2.add(new Bike(bikeType2,3,new BigDecimal(900),bikeProvider2,bikeCollection2BookedDates));
        
        
        

        
        
        // Put your test setup here
    }
    
    
    //System tests are established
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
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        
        expectedOutputQuotes.add(testQuote2);
        //The first quote should be removed because the 1st provider is near the given location
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
    
    //Currently giving me a NullPointerException when there is more than 1 quote added into the system for filterByDate...
    @Test
    void filterByDateWithMultipleQuotes() {
        requestedDates = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 19));
        assertNotNull(testQuote1);
        assertNotNull(testQuote2);
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        expectedOutputQuotes.add(testQuote2);
        
        //The first quote should be removed because the 1st quote has the date overlaps with the dates the Customer booked the bike for
        //Therefore the second quote should only exist in the list of expected quotes
        //inputQuotes should have a length of 2, but expectedOutputQuotes should have length of 1
        assertEquals(expectedOutputQuotes, MainSystem.filterByDate(inputQuotes, requestedDates));
    }
    
    //Still implementing
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
    
    
    
}
