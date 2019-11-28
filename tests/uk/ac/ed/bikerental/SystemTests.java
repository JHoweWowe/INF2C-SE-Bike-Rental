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
    private List<Quote> inputQuotes, expectedOutputQuotes;
    
    // Declare individual quotes to be put into Input and Output quotes:
    private Quote testQuote1, testQuote2, testQuote3, 
    testQuote4, testQuote5, testQuote6, testQuote7, testQuote8;
    
    // Variables declared for collection of bikes:
    private Collection<Bike> bikeCollection1, bikeCollection2, 
    bikeCollection3, bikeCollection4, bikeCollection5, 
    bikeCollection6, bikeCollection7, bikeCollection8;

    // Variables declared for bike providers:
    private Provider bikeProvider1, bikeProvider2, bikeProvider3, bikeProvider4, 
    bikeProvider5, bikeProvider6, bikeProvider7, bikeProvider8;

    // Variables declared for bike types:
    private BikeType bikeType1, bikeType2, bikeType3, bikeType4,
    bikeType5, bikeType6, bikeType7, bikeType8;

    // Variables declared for list of bike collection dates
    private List<DateRange> bikeCollection1BookedDates, bikeCollection2BookedDates, 
    bikeCollection3BookedDates, bikeCollection4BookedDates, bikeCollection5BookedDates,
    bikeCollection6BookedDates, bikeCollection7BookedDates, bikeCollection8BookedDates;

    // Preparing requested data- particularly used for when the Customer makes the booking or find quotes
    private RequestedData requestedData;
    private Location givenLocation;
    private DateRange requestedDates;
    private Map<BikeType, Integer> requestedMapOfBikes;
    
    //Creation of Customer and Booking objects
    private Customer customerWithNoBooking;
    
    
    private ValuationPolicy valuationPolicy;
    
    @BeforeEach
    void setUp() throws Exception {
        
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Instantiating the input and output quotes
        inputQuotes = new ArrayList<Quote>();
        expectedOutputQuotes = new ArrayList<Quote>();
        
        // Prepare data for the Collection of Bikes
        bikeCollection1 = new ArrayList<Bike>();
        bikeCollection2 = new ArrayList<Bike>();
        bikeCollection3 = new ArrayList<Bike>();
        bikeCollection4 = new ArrayList<Bike>();
        bikeCollection5 = new ArrayList<Bike>();
        bikeCollection6 = new ArrayList<Bike>();
        bikeCollection7 = new ArrayList<Bike>();
        bikeCollection8 = new ArrayList<Bike>();

        // Instantiate bikeType objects
        // Structure example- The bikeType name is "Trek" and original replacement value is 900
        bikeType1 = new BikeType("Mountain bike",new BigDecimal(900));
        bikeType2 = new BikeType("Road bike",new BigDecimal(600));
        bikeType3 = new BikeType("E-Bike",new BigDecimal(300));
        bikeType4 = new BikeType("Hybrid bike",new BigDecimal(450));
        bikeType5 = new BikeType("BMX",new BigDecimal(750));
        bikeType6 = new BikeType("Commuting bike",new BigDecimal(400));
        bikeType7 = new BikeType("Folding bike",new BigDecimal(550));
        bikeType8 = new BikeType("Track bike",new BigDecimal(650));
        
        // Create Location objects with postcode and address- used for BikeProvider store location
        Location loc1 = new Location("EH16 5AY","Pollock Halls");
        Location loc2 = new Location("G4 0JE","150 Dobbie's Loan");
        Location loc3 = new Location("EH9 1DU","8 Alvanley Terrace");
        Location loc4 = new Location("TD15 1ES","17A Bridge St");
        Location loc5 = new Location("E2 6DG","118 Bethnal Green Rd");
        Location loc6 = new Location("NE6 1DX","250 Shields Rd");
        Location loc7 = new Location("L1 4DN","17-19 Bold St");
        Location loc8 = new Location("EH9 1UU","250 Causewayside");
        
        // Create Provider objects- in this scenario assume opening hours is 9am
        bikeProvider1 = new Provider("Cycles4Hire",loc1,LocalTime.of(9, 00),new BigDecimal(0.3),valuationPolicy);
        bikeProvider2 = new Provider("Dales Cycles Ltd",loc2,LocalTime.of(9, 00),new BigDecimal(0.2),valuationPolicy);
        bikeProvider3 = new Provider("Edinburgh Bicycle Co-operative",loc3,LocalTime.of(9,00),new BigDecimal(0.1),valuationPolicy);
        bikeProvider4 = new Provider("Berwick Cycles",loc4,LocalTime.of(9, 00),new BigDecimal(0.25),valuationPolicy);
        bikeProvider5 = new Provider("Brick Lane Bikes",loc5,LocalTime.of(9, 00),new BigDecimal(0.15),valuationPolicy);
        bikeProvider6 = new Provider("Cycle Centre",loc6,LocalTime.of(9, 00),new BigDecimal(0.2),valuationPolicy);
        bikeProvider7 = new Provider("Cycle Republic",loc7,LocalTime.of(9, 00),new BigDecimal(0.07),valuationPolicy);
        bikeProvider8 = new Provider("The Bike Station",loc8,LocalTime.of(9, 00),new BigDecimal(0.15),valuationPolicy);
        
        // Prepare data for List of bike Collection booked dates 
        bikeCollection1BookedDates = new ArrayList<DateRange>();
        bikeCollection2BookedDates = new ArrayList<DateRange>();
        bikeCollection3BookedDates = new ArrayList<DateRange>();
        bikeCollection4BookedDates = new ArrayList<DateRange>();
        bikeCollection5BookedDates = new ArrayList<DateRange>();
        bikeCollection6BookedDates = new ArrayList<DateRange>();
        bikeCollection7BookedDates = new ArrayList<DateRange>();
        bikeCollection8BookedDates = new ArrayList<DateRange>();
        
        /**
         * Manually input bike collection booked dates- assume booked dates have no conflict?
         * In addition, there may be certain bike collection booked dates list which may be empty
         */
        //WARNING: bikeCollection4 and bikeCollection8 booked dates is empty for system testing purposes
        bikeCollection1BookedDates.add(new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23)));
        bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2015, 1, 10)));
        bikeCollection2BookedDates.add(new DateRange(LocalDate.of(2015, 2, 1), LocalDate.of(2015, 2, 5)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 3)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 1, 4), LocalDate.of(2019, 1, 30)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 2, 1), LocalDate.of(2019, 2, 14)));
        bikeCollection5BookedDates.add(new DateRange(LocalDate.of(2015, 12, 1), LocalDate.of(2015, 12, 5)));
        bikeCollection5BookedDates.add(new DateRange(LocalDate.of(2019, 1, 6), LocalDate.of(2015, 1, 9)));
        bikeCollection6BookedDates.add(new DateRange(LocalDate.of(2018, 12, 1), LocalDate.of(2018, 12, 25)));
        bikeCollection6BookedDates.add(new DateRange(LocalDate.of(2018, 12, 27), LocalDate.of(2019, 1, 2)));
        bikeCollection7BookedDates.add(new DateRange(LocalDate.of(2017, 1, 31), LocalDate.of(2018, 1, 30)));

        /**
         * Manually add the collection of bikes to the input of bike collections
         * Each bike collection may have one bike or more AND one or more BikeTypes
         * In addition, each bike collection may 
         */
        //Assume for simplification purposes, the bike price is 900
        bikeCollection1.add(new Bike(bikeType1,3,new BigDecimal(900),bikeProvider1,bikeCollection1BookedDates));
        bikeCollection1.add(new Bike(bikeType1,3,new BigDecimal(900),bikeProvider1,bikeCollection1BookedDates));
        
        bikeCollection2.add(new Bike(bikeType2,3,new BigDecimal(600),bikeProvider2,bikeCollection2BookedDates));
        
        //BE CAREFUL: bikeCollection3 has bikeType1
        bikeCollection3.add(new Bike(bikeType1,3,new BigDecimal(300),bikeProvider3,bikeCollection3BookedDates));
        bikeCollection3.add(new Bike(bikeType1,3,new BigDecimal(300),bikeProvider3,bikeCollection3BookedDates));
        
        //BikeCollection 4 has 2 bikeType 1 and 1 bikeType 4
        bikeCollection4.add(new Bike(bikeType1,3,new BigDecimal(300),bikeProvider4,bikeCollection4BookedDates));
        bikeCollection4.add(new Bike(bikeType1,3,new BigDecimal(300),bikeProvider4,bikeCollection4BookedDates));
        bikeCollection4.add(new Bike(bikeType4,3,new BigDecimal(300),bikeProvider4,bikeCollection4BookedDates));
        
        bikeCollection5.add(new Bike(bikeType5,3,new BigDecimal(300),bikeProvider5,bikeCollection5BookedDates));
        bikeCollection6.add(new Bike(bikeType6,3,new BigDecimal(450),bikeProvider6,bikeCollection6BookedDates));
        bikeCollection7.add(new Bike(bikeType7,3,new BigDecimal(450),bikeProvider7,bikeCollection7BookedDates));
        bikeCollection8.add(new Bike(bikeType8,3,new BigDecimal(450),bikeProvider8,bikeCollection8BookedDates));


        // Instantiate testQuote objects
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,new BigDecimal(600),new BigDecimal(480));
        testQuote2 = new Quote(bikeProvider2,bikeCollection2,new BigDecimal(600),new BigDecimal(480));
        testQuote3 = new Quote(bikeProvider3,bikeCollection3,new BigDecimal(600),new BigDecimal(480));
        testQuote4 = new Quote(bikeProvider4,bikeCollection4,new BigDecimal(600),new BigDecimal(480));
        testQuote5 = new Quote(bikeProvider5,bikeCollection5,new BigDecimal(600),new BigDecimal(480));
        testQuote6 = new Quote(bikeProvider6,bikeCollection6,new BigDecimal(600),new BigDecimal(480));
        testQuote7 = new Quote(bikeProvider7,bikeCollection7,new BigDecimal(600),new BigDecimal(480));
        testQuote8 = new Quote(bikeProvider8,bikeCollection8,new BigDecimal(600),new BigDecimal(480));
        
        // Verify that each test quote is not null before testing
        assertNotNull(testQuote1);
        assertNotNull(testQuote2);
        assertNotNull(testQuote3);
        assertNotNull(testQuote4);
        assertNotNull(testQuote5);
        assertNotNull(testQuote6);
        assertNotNull(testQuote7);
        assertNotNull(testQuote8);
        
        //Data to be added into the input quotes (eg: inputQuotes.add...)
        //Remodify SystemTests files

        
    }
    
    
    //System tests are established as a whole
    //Note: Each collection of bikes is under 1 provider
    
    //Should filter out quotes which are not near the given location
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
        givenLocation = new Location("EH16 5AJ","101 Dalkeith Road");
        
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);

        expectedOutputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote3);
        expectedOutputQuotes.add(testQuote8);
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
    @Test
    void filterByDateWithMultipleQuotes() {
        requestedDates = new DateRange(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 5));
        
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);
        
        expectedOutputQuotes.add(testQuote2);
        expectedOutputQuotes.add(testQuote4);
        expectedOutputQuotes.add(testQuote5);
        expectedOutputQuotes.add(testQuote7);
        expectedOutputQuotes.add(testQuote8);
                
        /** The 1st, 3rd and 6th quote should be removed 
        *   because those quotes have date overlaps with the requested dates
        **/        
        assertEquals(expectedOutputQuotes, MainSystem.filterByDate(inputQuotes, requestedDates));
    }
    
    @Test
    void filterByNumTypesWithMultipleQuotes() {
        Map<BikeType,Integer> map = new HashMap<BikeType,Integer>();
        map.put(bikeType1, 2);
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        
        expectedOutputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote3);
        
        assertEquals(expectedOutputQuotes, MainSystem.filterByNumType(inputQuotes, map));
    }
    //Example given...implement
    @Test
    void filterByNumTypesWithMultipleQuotesAndMap() {
        Map<BikeType,Integer> map = new HashMap<BikeType,Integer>();
        map.put(bikeType1, 2);
        map.put(bikeType2, 1);
        
        
    }

    //Implements all two(will be three) filters to find the quotes which match the customer's criteria
    @Test
    void findQuotesTest1() {
        givenLocation = new Location("EH16 5AJ", "101 Dalkeith Road");
        requestedDates = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 19));
        requestedMapOfBikes = new HashMap<BikeType,Integer>();
        requestedMapOfBikes.put(bikeType1, 2);
        
        requestedData = new RequestedData(givenLocation, requestedDates, requestedMapOfBikes);
        
        inputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote1);
        
        //Assuming in this scenario, the customer hasn't made a booking yet
        customerWithNoBooking = new Customer();
        customerWithNoBooking.findQuote(inputQuotes, requestedData);
        
        assertEquals(expectedOutputQuotes, customerWithNoBooking.findQuote(inputQuotes, requestedData));    
    }
    //TO BE IMPLEMENTED
    @Test
    void findQuotesTest2() {
        
    }
    //TO BE IMPLEMENTED
    @Test
    void bookQuotesTest() {
        
    }
    
}
