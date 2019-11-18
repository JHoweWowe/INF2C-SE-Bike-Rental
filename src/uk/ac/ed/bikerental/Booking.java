package uk.ac.ed.bikerental;

public class Booking {
    
    //DateRange should be the Booking date range given
    protected DateRange bookingDates;
    protected Quote quote;
    
    
    public Booking(DateRange bookingDates, Quote quote) {
        this.bookingDates = bookingDates;
        this.quote = quote;
    }

    
    //Searches through all bikes in the Quote and adds the dateRange as booked
    public void addBooking(Quote q) {
        for (Bike b : q.getCollectionOfBikes()) {
            b.addToBookedDates(bookingDates);
        }
    }

}
