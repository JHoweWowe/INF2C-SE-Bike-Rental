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
    }

    
    //Searches through all bikes in the Quote and adds the dateRange as booked
    public void addBooking(Quote q) {
        for (Bike b : q.getCollectionOfBikes()) {
            b.addToBookedDates(bookingDates);
        }
    }

}
