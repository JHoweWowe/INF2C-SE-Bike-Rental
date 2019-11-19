package uk.ac.ed.bikerental;

import java.util.List;

/**
 * Question: Customer should technically implement DeliveryService class?
 * Even though logically speaking, Customer is not a DeliveryService
 * However if customer requests delivery, then DeliveryService must be used
 * @author Justin Howe
 *
 */
public class Customer {
    
    private Booking booking;
    private boolean requestDelivery;
    
    public Customer(Booking booking, boolean requestDelivery) {
        this.booking = booking;
        this.requestDelivery = requestDelivery;
    }
    
    //This method is used for the first use case scenario
    //To check: Unsure if this approach is correct
    public List<Quote> findQuote(List<Quote> quotes, RequestedData requestedData) {
        return MainSystem.filterByDate(MainSystem.filterByProvider(quotes, requestedData.getRequestedLocation()), requestedData.getRequestedDates());
    }
    
    //This method is used for the second use case scenario
    public Booking bookQuote(Quote quote) {
        if (requestDelivery) {
            //Set up the DeliveryService- assume it has been done
            DeliveryServiceFactory.getDeliveryService();
            //Return a booking
        }
        //Else return just a booking
        return null;
    }

}
