package uk.ac.ed.bikerental;

import java.util.List;

/**
 * This class acts as the Main class
 * Currently implementing the first use case scenario
 *
 */

public class MainSystem {
    
    private List<Quote> quotes;
    private Booking booking;
    
    //This method filters the quotes whose Providers are not near the given location
    public static List<Quote> filterProvider(List<Quote> quotes, Location givenLocation) {
        for (Quote q : quotes) {
            Location providerLocation = q.getProvider().getLocation();
            if (!(providerLocation.isNearTo(givenLocation))) {
                quotes.remove(q);
            }
        }
        return quotes;
    }

    public List<Quote> filterByDate(List<Quote> listOfQuotes, DateRange requestedDates){
        for (Quote q : listOfQuotes){
            if (search(q,requestedDates)){
                listOfQuotes.remove(q);
            }
        }
        return listOfQuotes;
    }

    public boolean search(Quote q, DateRange requestedDates){
        for (Bike b : q.getCollectionOfBikes()){
            for (DateRange range : b.getBookedDates()){
                if (range.overlaps(requestedDates)){
                    return true;
                }
            }
        }
        return false;
    }
    
    //There should be another method to filterDate
    public static List<Quote> filterDate(List<Quote> quotes, DateRange dates) {
        return null;
    }
    
    //Shows the final result
    public List<Quote> showsFilteredList() {
        return null;
    }
}
