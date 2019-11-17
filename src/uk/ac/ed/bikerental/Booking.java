package uk.ac.ed.bikerental;

public class Booking {
    
    private boolean paymentSuccess;
    //DateRange should be the Booking date range given
    protected DateRange bookingDates;
    protected Quote quote;
    
    
    public Booking(boolean paymentSuccess, DateRange bookingDates, Quote quote) {
        this.paymentSuccess = paymentSuccess;
        this.bookingDates = bookingDates;
        this.quote = quote;
    }
    
    public boolean getPaymentSuccess() {
        return paymentSuccess;
    }
    
    //Searches through all bikes in the Quote and adds the dateRange as booked
    public void addBooking(Quote q) {
        for (Bike b : q.getCollectionOfBikes()) {
            b.addToBookedDates(bookingDates);
        }
    }

}
