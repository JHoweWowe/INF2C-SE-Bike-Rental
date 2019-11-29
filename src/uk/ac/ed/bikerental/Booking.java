package uk.ac.ed.bikerental;

public class Booking {
    
    //DateRange should be the Booking date range given
    private DateRange bookingDates;
    private Quote quote;
    private int bookingNumber;
    
    
    public Booking(DateRange bookingDates, Quote quote, int bookingNumber) {
        this.bookingDates = bookingDates;
        this.quote = quote;
        this.bookingNumber = bookingNumber;
        
        for (Bike bike : quote.getCollectionOfBikes()) {
            bike.setNotAvailableForRent();
        }
    }
    
    public DateRange getBookingDates() {
        return bookingDates;
    }
    
    public Quote getQuote() {
        return quote;
    }
    
    public int getBookingNumber() {
        return bookingNumber;
    }
    
    //This should be called after the booking has been done- the booking number should be incremented by 1
    public void incrementBookingNumber() {
        bookingNumber++;
    }


}
