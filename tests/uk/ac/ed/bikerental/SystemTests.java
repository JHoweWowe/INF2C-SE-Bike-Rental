package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
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
    private boolean requestDelivery;
    
    //Creation of Customer and Booking objects
    private Customer customerWithNoBooking;
    private Customer customerWithBooking;
    private Booking booking1;
    private Booking booking2;
    
    //Declaration of Pricing and Valuation policies
    private DefaultPricingPolicy defaultPricePolicy;
    private ValuationPolicy valuationPolicy;
    private LinearDepreciationValuationPolicy LDValuationPolicy1;
    private DoubleDepreciationValuationPolicy DDValuationPolicy1;
    private BigDecimal totalPriceforQuote;
    private BigDecimal totalDepositAmountforQuote;
    
    //Creating states for delivery
    private Deque<Deliverable> deque;
    
    @BeforeEach
    void setUp() throws Exception {
        
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        deque = new ArrayDeque<Deliverable>();

        
        // Integration of the pricing and valuation policy
        defaultPricePolicy = new DefaultPricingPolicy();
        totalPriceforQuote = new BigDecimal(0);
        totalDepositAmountforQuote = new BigDecimal(0);
        LDValuationPolicy1 = new LinearDepreciationValuationPolicy(new BigDecimal(0.1));
        DDValuationPolicy1 = new DoubleDepreciationValuationPolicy(new BigDecimal(0.1));

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
        // Structure example- The bikeType name is "Trek" and original replacement value is 900 and price is 450
        bikeType1 = new BikeType("Mountain bike",new BigDecimal(900),new BigDecimal(12));
        bikeType2 = new BikeType("Road bike",new BigDecimal(900),new BigDecimal(15));
        bikeType3 = new BikeType("E-Bike",new BigDecimal(900), new BigDecimal(10));
        bikeType4 = new BikeType("Hybrid bike",new BigDecimal(450), new BigDecimal(5));
        bikeType5 = new BikeType("BMX",new BigDecimal(750), new BigDecimal(10));
        bikeType6 = new BikeType("Commuting bike",new BigDecimal(400), new BigDecimal(8));
        bikeType7 = new BikeType("Folding bike",new BigDecimal(550), new BigDecimal(9));
        bikeType8 = new BikeType("Track bike",new BigDecimal(650), new BigDecimal(10));
        
        // Create Location objects with postcode and address- used for BikeProvider store location
        Location loc1 = new Location("EH16 5AY","Pollock Halls");
        Location loc2 = new Location("G4 0JE","150 Dobbie's Loan");
        Location loc3 = new Location("EH9 1DU","8 Alvanley Terrace");
        Location loc4 = new Location("G4 1ES","17A Bridge St");
        Location loc5 = new Location("G4 6DG","118 Bethnal Green Rd");
        Location loc6 = new Location("NE6 1DX","250 Shields Rd");
        Location loc7 = new Location("G1 4DN","17-19 Bold St");
        Location loc8 = new Location("EH9 1UU","250 Causewayside");
        
        // Create Provider objects- in this scenario assume opening hours is 9am
        bikeProvider1 = new Provider("Cycles4Hire",loc1,LocalTime.of(9, 00),new BigDecimal(0.2),LDValuationPolicy1,defaultPricePolicy);
        bikeProvider2 = new Provider("Dales Cycles Ltd",loc2,LocalTime.of(9, 00),new BigDecimal(0.2),DDValuationPolicy1,defaultPricePolicy);
        bikeProvider3 = new Provider("Edinburgh Bicycle Co-operative",loc3,LocalTime.of(9,00),new BigDecimal(0.2),LDValuationPolicy1,defaultPricePolicy);
        bikeProvider4 = new Provider("Berwick Cycles",loc4,LocalTime.of(9, 00),new BigDecimal(0.25),DDValuationPolicy1,defaultPricePolicy);
        bikeProvider5 = new Provider("Brick Lane Bikes",loc5,LocalTime.of(9, 00),new BigDecimal(0.15),valuationPolicy,defaultPricePolicy);
        bikeProvider6 = new Provider("Cycle Centre",loc6,LocalTime.of(9, 00),new BigDecimal(0.2),valuationPolicy,defaultPricePolicy);
        bikeProvider7 = new Provider("Cycle Republic",loc7,LocalTime.of(9, 00),new BigDecimal(0.07),valuationPolicy,defaultPricePolicy);
        bikeProvider8 = new Provider("The Bike Station",loc8,LocalTime.of(9, 00),new BigDecimal(0.15),valuationPolicy,defaultPricePolicy);
        
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
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 12)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 3)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 1, 4), LocalDate.of(2019, 1, 30)));
        bikeCollection3BookedDates.add(new DateRange(LocalDate.of(2019, 2, 1), LocalDate.of(2019, 2, 14)));
        bikeCollection5BookedDates.add(new DateRange(LocalDate.of(2015, 1, 29), LocalDate.of(2015, 2, 3)));
        bikeCollection5BookedDates.add(new DateRange(LocalDate.of(2019, 1, 6), LocalDate.of(2015, 1, 9)));
        bikeCollection6BookedDates.add(new DateRange(LocalDate.of(2018, 12, 1), LocalDate.of(2018, 12, 25)));
        bikeCollection6BookedDates.add(new DateRange(LocalDate.of(2018, 12, 27), LocalDate.of(2019, 1, 2)));
        bikeCollection7BookedDates.add(new DateRange(LocalDate.of(2017, 1, 31), LocalDate.of(2018, 1, 30)));

        /**
         * Manually add the collection of bikes to the input of bike collections
         * Each bike collection may have one bike or more AND one or more BikeTypes
         * In addition, each bike collection size may be more than 1
         */
        //Assume for simplification purposes, the bike price is 900
        bikeCollection1.add(new Bike(bikeType1,3,bikeProvider1,bikeCollection1BookedDates));
        bikeCollection1.add(new Bike(bikeType1,3,bikeProvider1,bikeCollection1BookedDates));
        
        //BikeCollection 4 has 2 bikeType2 bikes and 2 bikeType4 bikes
        bikeCollection2.add(new Bike(bikeType2,3,bikeProvider2,bikeCollection2BookedDates));
        bikeCollection2.add(new Bike(bikeType2,3,bikeProvider2,bikeCollection2BookedDates));
        bikeCollection2.add(new Bike(bikeType4,3,bikeProvider2,bikeCollection2BookedDates));
        bikeCollection2.add(new Bike(bikeType4,3,bikeProvider2,bikeCollection2BookedDates));
 
        //BE CAREFUL: bikeCollection3 has 2 bikeType3
        bikeCollection3.add(new Bike(bikeType3,3,bikeProvider3,bikeCollection3BookedDates));
        bikeCollection3.add(new Bike(bikeType3,3,bikeProvider3,bikeCollection3BookedDates));
        
        //BikeCollection 4 has 2 bikeType 1 and 1 bikeType 4
        bikeCollection4.add(new Bike(bikeType2,3,bikeProvider4,bikeCollection4BookedDates));
        bikeCollection4.add(new Bike(bikeType2,3,bikeProvider4,bikeCollection4BookedDates));
        bikeCollection4.add(new Bike(bikeType4,3,bikeProvider4,bikeCollection4BookedDates));
        
        bikeCollection5.add(new Bike(bikeType1,3,bikeProvider5,bikeCollection5BookedDates));
        bikeCollection5.add(new Bike(bikeType1,3,bikeProvider5,bikeCollection5BookedDates));

        bikeCollection6.add(new Bike(bikeType6,3,bikeProvider6,bikeCollection6BookedDates));
        
        //BE CAREFUL: bikeCollection7 has 2 bikeType1 and 1 bikeType 2
        bikeCollection7.add(new Bike(bikeType1,3,bikeProvider4,bikeCollection4BookedDates));
        bikeCollection7.add(new Bike(bikeType1,3,bikeProvider4,bikeCollection4BookedDates));
        bikeCollection7.add(new Bike(bikeType2,3,bikeProvider4,bikeCollection4BookedDates));
        
        //BE CAREFUL: bikeCollection8 has 2 bikeType1 and 1 bikeType 2
        bikeCollection8.add(new Bike(bikeType1,3,bikeProvider8,bikeCollection8BookedDates));
        bikeCollection8.add(new Bike(bikeType1,3,bikeProvider8,bikeCollection8BookedDates));

        // Instantiate testQuote objects where totalPrice and totalDepositAmount in that quote is 0 for now
        // It needs a requestedDates value to be fully implemented in each of the SystemTests required
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote2 = new Quote(bikeProvider2,bikeCollection2,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote3 = new Quote(bikeProvider3,bikeCollection3,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote4 = new Quote(bikeProvider4,bikeCollection4,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote5 = new Quote(bikeProvider5,bikeCollection5,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote6 = new Quote(bikeProvider6,bikeCollection6,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote7 = new Quote(bikeProvider7,bikeCollection7,totalPriceforQuote,totalDepositAmountforQuote);
        testQuote8 = new Quote(bikeProvider8,bikeCollection8,totalPriceforQuote,totalDepositAmountforQuote);
        
        // Verify that each test quote is not null before testing
        assertNotNull(testQuote1);
        assertNotNull(testQuote2);
        assertNotNull(testQuote3);
        assertNotNull(testQuote4);
        assertNotNull(testQuote5);
        assertNotNull(testQuote6);
        assertNotNull(testQuote7);
        assertNotNull(testQuote8);
    }
    
    //System tests are established as a whole
    //Note: Each collection of bikes is under 1 provider
    @Test
    void defaultPricePolicyTestWith1BikeType() {
        requestedDates = new DateRange(LocalDate.of(2018, 1, 5), LocalDate.of(2018, 1, 8));
        defaultPricePolicy = new DefaultPricingPolicy();
        
        assertEquals(new BigDecimal(72),defaultPricePolicy.calculatePrice(bikeCollection1, requestedDates));
    }
    @Test
    void defaultPricePolicyTestWith2BikeTypes() {
        requestedDates = new DateRange(LocalDate.of(2018, 1, 5), LocalDate.of(2018, 1, 10));
        defaultPricePolicy = new DefaultPricingPolicy();
        
        assertEquals(new BigDecimal(200),defaultPricePolicy.calculatePrice(bikeCollection2, requestedDates));
    }
    //A SystemTest where default pricing policy and Linear Valuation policy behavior is implemented
    //
    @Test
    void calculateTotalPriceAndTotalDepositAmountForQuote1() {
        requestedDates = new DateRange(LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 8));
        defaultPricePolicy = new DefaultPricingPolicy();
        
        totalPriceforQuote = defaultPricePolicy.calculatePrice(bikeCollection1, requestedDates);
        for (Bike bike : bikeCollection1) {
            totalDepositAmountforQuote = totalDepositAmountforQuote.add(testQuote1.getProvider().getValuationPolicy()
                    .calculateValue(bike, requestedDates.getStart()).multiply(testQuote1.getProvider().getDepositRate()));
        }
        totalDepositAmountforQuote = totalDepositAmountforQuote.setScale(2,RoundingMode.HALF_EVEN);
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,totalPriceforQuote,totalDepositAmountforQuote);

        
        assertEquals(new BigDecimal(72),testQuote1.getTotalPrice());
        assertEquals(new BigDecimal("252.00"),testQuote1.getTotalDepositAmount());
        
    }
    
    //Used for the booking
    @Test
    void CalculateDepositWithLinearValuationPolicy() {        
        LocalDate date = LocalDate.of(2016, 3, 3);
        //Takes the first element in the first Bike collection for testing purposes
        Bike bike = bikeCollection1.iterator().next();
        
        BigDecimal depositAmount = testQuote1.getProvider().getValuationPolicy()
                .calculateValue(bike, date).multiply(testQuote1.getProvider().getDepositRate());
        depositAmount = depositAmount.setScale(2,RoundingMode.HALF_EVEN);

        assertEquals(new BigDecimal("126.00"),depositAmount);
    }
    @Test
    void CalculateDepositWithDoubleDepreciationValuationPolicy() {
        LocalDate date = LocalDate.of(2016, 3, 3);
        //Takes first element in second Bike collection for testing purposes
        Bike bike = bikeCollection2.iterator().next();
        
        BigDecimal depositAmount = testQuote2.getProvider().getValuationPolicy()
                .calculateValue(bike, date).multiply(testQuote2.getProvider().getDepositRate());
        depositAmount = depositAmount.setScale(2,RoundingMode.HALF_EVEN);
        
        assertEquals(new BigDecimal("92.16"),depositAmount);
    }
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
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);
        
        expectedOutputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote5);
        expectedOutputQuotes.add(testQuote8);
        
        assertEquals(expectedOutputQuotes, MainSystem.filterByNumType(inputQuotes, map));
    }
    @Test
    void filterByNumTypesWithMultipleQuotesAndMap() {
        Map<BikeType,Integer> map = new HashMap<BikeType,Integer>();
        map.put(bikeType2, 2);
        map.put(bikeType4, 1);
        
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);
        
        expectedOutputQuotes.add(testQuote4);
        
        assertEquals(expectedOutputQuotes, MainSystem.filterByNumType(inputQuotes, map));
    }

    //System test to find all the quotes which match the customer's criteria
    @Test
    void findQuotesTest1() {
        givenLocation = new Location("EH16 5AJ", "101 Dalkeith Road");
        requestedDates = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 19));
        requestedMapOfBikes = new HashMap<BikeType,Integer>();
        requestedMapOfBikes.put(bikeType1, 2);
        
        requestedData = new RequestedData(givenLocation, requestedDates, requestedMapOfBikes);
        
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);
        
        expectedOutputQuotes.add(testQuote1);
        expectedOutputQuotes.add(testQuote8);
        
        //Assuming in this scenario, the customer hasn't made a booking yet
        customerWithNoBooking = new Customer();
        
        assertEquals(expectedOutputQuotes, customerWithNoBooking.findQuote(inputQuotes, requestedData));    
    }
    //System should return no possible quotes to be found
    @Test
    void findQuotesTest2() {
        givenLocation = new Location("G12 8QQ", "University Ave");
        requestedDates = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 19));
        requestedMapOfBikes = new HashMap<BikeType,Integer>();
        requestedData = new RequestedData(givenLocation, requestedDates, requestedMapOfBikes);

        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);
        
        customerWithNoBooking = new Customer();
        assertEquals(expectedOutputQuotes, customerWithNoBooking.findQuote(inputQuotes, requestedData));      
    }
    @Test
    void findQuotesTest3() {
        givenLocation = new Location("G4 8QQ", "Random Address in Glasgow");
        requestedDates = new DateRange(LocalDate.of(2019, 1, 9), LocalDate.of(2019, 1, 5));
        requestedMapOfBikes = new HashMap<BikeType,Integer>();
        requestedMapOfBikes.put(bikeType2, 2);        
        requestedMapOfBikes.put(bikeType4, 2);
        requestedData = new RequestedData(givenLocation, requestedDates, requestedMapOfBikes);
        
        inputQuotes.add(testQuote1);
        inputQuotes.add(testQuote2);
        inputQuotes.add(testQuote3);
        inputQuotes.add(testQuote4);
        inputQuotes.add(testQuote5);
        inputQuotes.add(testQuote6);
        inputQuotes.add(testQuote7);
        inputQuotes.add(testQuote8);
        
        expectedOutputQuotes.add(testQuote2);
        
        customerWithNoBooking = new Customer();
        assertEquals(expectedOutputQuotes, customerWithNoBooking.findQuote(inputQuotes, requestedData));      
    }
    //Assuming the booking dates don't conflict with the testQuote booked dates
    //Check if the booking details is the same as the testQuote that the Customer wants to book for
    @Test
    void bookQuoteNoDeliveryRequired() {
        requestedDates = new DateRange(LocalDate.of(2019, 1, 1),LocalDate.of(2019, 1, 3));
        requestDelivery = false;
        booking1 = new Booking(requestedDates, testQuote1, 0);

        customerWithBooking = new Customer(booking1, requestDelivery);
        customerWithBooking.bookQuote(testQuote1, requestedDates);
        
        //Check if the details of each quote correspond to the correct booking details
        assertEquals(0, booking1.getBookingNumber());
        assertEquals(bikeProvider1, booking1.getQuote().getProvider());
        assertEquals(totalPriceforQuote, booking1.getQuote().getTotalPrice());
        assertEquals(totalDepositAmountforQuote, booking1.getQuote().getTotalDepositAmount());
        
        //Check if all bikes in that quote is not available for rent
        for (Bike bike : booking1.getQuote().getCollectionOfBikes()) {
            assertEquals(false, bike.isBooked);
        }
    }
    //Check status if each bike is booked with delivery implementation
    @Test
    void bookQuoteDeliveryRequiredWithLinearDepreciationTest1() {
        requestDelivery = true;
        requestedDates = new DateRange(LocalDate.of(2016, 2, 1),LocalDate.of(2016, 2, 5));
        
        //The total price and deposit amount for the quote should be integrated
        defaultPricePolicy = new DefaultPricingPolicy();
        totalPriceforQuote = defaultPricePolicy.calculatePrice(bikeCollection3, requestedDates);
        for (Bike bike : bikeCollection3) {
            totalDepositAmountforQuote = totalDepositAmountforQuote.add(testQuote3.getProvider().getValuationPolicy()
                    .calculateValue(bike, requestedDates.getStart()).multiply(testQuote3.getProvider().getDepositRate()));
        }
        totalDepositAmountforQuote = totalDepositAmountforQuote.setScale(2,RoundingMode.HALF_EVEN);
        
        testQuote3 = new Quote(bikeProvider3,bikeCollection3,totalPriceforQuote,totalDepositAmountforQuote);
        //From that testQuote which now has the proper total price and total deposit amount, a Booking object is now created
        booking1 = new Booking(requestedDates, testQuote3, 1);
        
        Bike bike1 = new Bike(bikeType3,3,bikeProvider3,bikeCollection3BookedDates);
                
        customerWithBooking = new Customer(booking1, requestDelivery);
        customerWithBooking.bookQuote(testQuote3, requestedDates);

        MockDeliveryService deliveryService = new MockDeliveryService();
        deliveryService.scheduleDelivery(bike1, givenLocation, givenLocation, requestedDates.getStart());
        
        //Check if the DeliveryService collected in the Map of pickUps is the same
        //as the delivery of pickups
        assertEquals(deliveryService.getPickupsOn(requestedDates.getStart()), 
                deliveryService.pickups.get(requestedDates.getStart()));
        
        //Check if the details of each quote correspond to the correct booking details
        assertEquals(1, booking1.getBookingNumber());
        assertEquals(bikeProvider3, booking1.getQuote().getProvider());

        assertEquals(new BigDecimal(80),testQuote3.getTotalPrice());
        assertEquals(new BigDecimal("252.00"),testQuote3.getTotalDepositAmount());
        assertEquals(totalPriceforQuote, booking1.getQuote().getTotalPrice());
        assertEquals(totalDepositAmountforQuote, booking1.getQuote().getTotalDepositAmount());
        assertTrue(testQuote3.equals(booking1.getQuote()));
    }
    //Check status if each bike is booked with delivery implementation
    @Test
    void bookQuoteDeliveryRequiredWithDoubleDepreciationTest2() {
        requestDelivery = true;
        requestedDates = new DateRange(LocalDate.of(2016, 2, 1),LocalDate.of(2016, 2, 5));
        
        //The total price and deposit amount for the quote should be integrated
        defaultPricePolicy = new DefaultPricingPolicy();
        totalPriceforQuote = defaultPricePolicy.calculatePrice(bikeCollection4, requestedDates);
        for (Bike bike : bikeCollection4) {
            totalDepositAmountforQuote = totalDepositAmountforQuote.add(testQuote4.getProvider().getValuationPolicy()
                    .calculateValue(bike, requestedDates.getStart()).multiply(testQuote4.getProvider().getDepositRate()));
        }
        totalDepositAmountforQuote = totalDepositAmountforQuote.setScale(2,RoundingMode.HALF_EVEN);
        
        testQuote4 = new Quote(bikeProvider4,bikeCollection4,totalPriceforQuote,totalDepositAmountforQuote);
        //From that testQuote which now has the proper total price and total deposit amount, a Booking object is now created
        booking1 = new Booking(requestedDates, testQuote4, 1);
        
        Bike bike1 = new Bike(bikeType4,3,bikeProvider4,bikeCollection4BookedDates);
                
        customerWithBooking = new Customer(booking1, requestDelivery);
        customerWithBooking.bookQuote(testQuote4, requestedDates);

        MockDeliveryService deliveryService = new MockDeliveryService();
        deliveryService.scheduleDelivery(bike1, givenLocation, givenLocation, requestedDates.getStart());
        
        //Check if the DeliveryService collected in the Map of pickUps is the same
        //as the delivery of pickups
        assertEquals(deliveryService.getPickupsOn(requestedDates.getStart()), 
                deliveryService.pickups.get(requestedDates.getStart()));
        
        //Check if the details of each quote correspond to the correct booking details
        assertEquals(1, booking1.getBookingNumber());
        assertEquals(bikeProvider4, booking1.getQuote().getProvider());

        assertEquals(new BigDecimal(140),testQuote4.getTotalPrice());
        assertEquals(new BigDecimal("288.00"),testQuote4.getTotalDepositAmount());
        assertEquals(totalPriceforQuote, booking1.getQuote().getTotalPrice());
        assertEquals(totalDepositAmountforQuote, booking1.getQuote().getTotalDepositAmount());
        assertTrue(testQuote4.equals(booking1.getQuote()));
    }
    //Assuming a booking has been already made for testing purposes
    //Once the booking has been made, all of the bikes should be booked
    //After the bikes are returned to the original provider, all bikes should no longer be booked
    //and made available for rent
    @Test
    void returnBikesToOriginalProvider() {
        requestDelivery = false;
        requestedDates = new DateRange(LocalDate.of(2016, 2, 1),LocalDate.of(2016, 2, 5));
        
        testQuote1 = new Quote(bikeProvider1,bikeCollection1,totalPriceforQuote,totalDepositAmountforQuote);
        booking1 = new Booking(requestedDates, testQuote1, 1);
        
        for (Bike bike : booking1.getQuote().getCollectionOfBikes()) {
            assertEquals(true, bike.isBooked());
        }
        
        bikeProvider1.acceptBikeReturn(booking1);
        
        for (Bike bike : booking1.getQuote().getCollectionOfBikes()) {
            assertEquals(false, bike.isBooked());
        }
    }
    //For testing purposes, the delivery service needs to be setup to test pickups and dropoffs
    //Considering the bikes are returned to a different provider when customer requested delivery
    @Test
    void returnBikesToDifferentProvider() {
        requestDelivery = true;
        requestedDates = new DateRange(LocalDate.of(2016, 2, 1),LocalDate.of(2016, 2, 5));

        testQuote2 = new Quote(bikeProvider2,bikeCollection2,totalPriceforQuote,totalDepositAmountforQuote);
        booking1 = new Booking(requestedDates, testQuote2, 2);
        
        MockDeliveryService deliveryService = new MockDeliveryService();
        for (Bike bike : booking1.getQuote().getCollectionOfBikes()) {
            deliveryService.scheduleDelivery(bike, givenLocation, givenLocation, requestedDates.getStart());
        }
        deliveryService.carryOutPickups(requestedDates.getStart());
        
        for (Bike bike : booking1.getQuote().getCollectionOfBikes()) {
            assertEquals(true, bike.isBooked());
        }
        bikeProvider2.acceptBikeReturn(booking1);
        for (Bike bike : booking1.getQuote().getCollectionOfBikes()) {
            assertEquals(false, bike.isBooked());
        }
        deliveryService.carryOutDropoffs();
                
        assertEquals(deque.isEmpty(),deliveryService.getDropoffs().isEmpty());

        
    }
    
}
