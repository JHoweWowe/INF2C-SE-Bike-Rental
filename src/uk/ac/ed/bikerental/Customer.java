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
    
    //Empty constructor for Customer- assuming the Customer has not made a booking yet
    //before finding quotes
    //Mainly used for testing purposes
    public Customer() {
        
    }
    
    public Customer(Booking booking, boolean requestDelivery) {
        this.booking = booking;
        this.requestDelivery = requestDelivery;
    }
    
    //This method is used for the first use case scenario
    public List<Quote> findQuote(List<Quote> allQuotes, RequestedData requestedData) {
        return MainSystem.filterByDate(MainSystem.filterByProvider(allQuotes, 
                requestedData.getRequestedLocation()), requestedData.getRequestedDates());
    }
    
    //This method is used for the second use case scenario
    public Booking bookQuote(Quote quote, DateRange dates) {
        Booking bookingObject = new Booking(dates,quote,booking.getBookingNumber());
        
        //For each bike in the quote, the bike booking dates must be added
        for (Bike bike : quote.getCollectionOfBikes()) {
            bike.addToBookedDates(dates);
            quote.getProvider().setNotAvailableForRent(bike);
        }
        
        //Assume the dropoff location is the same as the pickup location
        if (requestDelivery) {
            for (Bike bike : quote.getCollectionOfBikes()) {
                DeliveryServiceFactory.getDeliveryService()
                .scheduleDelivery(bike, quote.getProvider().getLocation(), quote.getProvider().getLocation(), dates.getStart());
            }
            //Booking object should be updated by 1
            bookingObject.incrementBookingNumber();
            return bookingObject;
        }
        //Else only return just a booking without the delivery
        return bookingObject;
    }

}
