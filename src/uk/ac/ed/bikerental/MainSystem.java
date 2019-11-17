package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as the Main class
 * Currently implementing the first use case scenario
 *
 */

public class MainSystem {
    
    /**
     * This method filters the quotes whose Providers are not near the given location
     * This approach is to avoid ConcurrentModification error
     */
    public static List<Quote> filterByProvider(List<Quote> quotes, Location givenLocation) {
        List<Quote> quotesToRemove = new ArrayList<>();
        for (Quote q : quotes) {
            Location providerLocation = q.getProvider().getLocation();
            if (!(providerLocation.isNearTo(givenLocation))) {
                quotesToRemove.add(q);
            }
        }
        quotes.removeAll(quotesToRemove);
        return quotes;
    }
    
    /**
     * This method filters the quotes whose Dates are not in the given Dates
     * This approach is to avoid ConcurrentModification error
     */
    public static List<Quote> filterByDate(List<Quote> listOfQuotes, DateRange requestedDates) {
        List<Quote> quotesToRemove = new ArrayList<>();
        for (Quote q : listOfQuotes) {
            if (search(q,requestedDates)) {
                quotesToRemove.add(q);
            }
        }
        listOfQuotes.removeAll(quotesToRemove);
        return listOfQuotes;
    }
    
    //Helper function to search through whether the bike has been booked for the requested dates
    public static boolean search(Quote q, DateRange requestedDates) {
        for (Bike b : q.getCollectionOfBikes()) {
            for (DateRange range : b.getBookedDates()) {
                if (range.overlaps(requestedDates)) {
                    return true;
                }
            }
        }
        return false;
    }
}
