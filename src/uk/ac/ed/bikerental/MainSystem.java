package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class acts as the Main class- all methods should be static
 * Currently implementing the first and second use case scenario
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
    public static boolean search(Quote quote, DateRange requestedDates) {
        for (Bike b : quote.getCollectionOfBikes()) {
            for (DateRange range : b.getBookedDates()) {
                if (range.overlaps(requestedDates)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * This method filters the quotes where the number of BikeTypes requested is not equal to the customer's desire
     * Instead of removing quotes that don't match, we add quotes which matches the map
     */
    public static List<Quote> filterByNumType(List<Quote> listOfQuotes, Map<BikeType,Integer> map) {
        List<Quote> quotesToKeep = new ArrayList<Quote>();
        for (Quote q : listOfQuotes) {
            Map<BikeType,Integer> quoteMap = q.getMapOfBikes();
            if (quoteMap.equals(map)) {
              quotesToKeep.add(q);
            }
              /*List<BikeType> quoteBikes = new ArrayList<BikeType>();
              for (Bike b : q.getCollectionOfBikes()){
                quoteBikes.add(b.getBikeType());
              }
              // Now compare whether quote bike types match requested bike types
              // Convert the Map to a List
              List<BikeType> requestedBikes = new ArrayList<BikeType>();
              */
        }
        return quotesToKeep;
      }
    
    //Tester function
    public static List<Quote> filterByNumType2(List<Quote> listOfQuotes, Map<BikeType,Integer> map) {
        List<Quote> quotesToKeep = new ArrayList<Quote>();
        Map<BikeType,Integer> bikeTypeMap = new HashMap<BikeType,Integer>();
        for (Quote q : listOfQuotes) {
            for (Bike bike : q.getCollectionOfBikes()) {
                if (!bikeTypeMap.containsKey(bike.getBikeType())) { 
                    bikeTypeMap.put(bike.getBikeType(),0);
                }
                bikeTypeMap.replace(bike.getBikeType(), bikeTypeMap.get(bike.getBikeType()) + 1);
            }
            if (bikeTypeMap.equals(map)) {
                quotesToKeep.add(q);
            }
        }
        return quotesToKeep;
    }

    
}
