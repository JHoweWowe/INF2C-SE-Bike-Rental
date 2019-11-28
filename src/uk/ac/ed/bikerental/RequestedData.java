package uk.ac.ed.bikerental;
import java.util.*;

public class RequestedData {

    private Location requestedLocation;
    private DateRange requestedDates;
    private Map<BikeType, Integer> requestedMapOfBikes;

    public RequestedData(Location requestedLocation, DateRange requestedDates, Map<BikeType, Integer> requestedMapOfBikes){
        this.requestedLocation = requestedLocation;
        this.requestedDates = requestedDates;
        this.requestedMapOfBikes = requestedMapOfBikes;
    }

    public Location getRequestedLocation(){
        return requestedLocation;
    }

    public DateRange getRequestedDates(){
        return requestedDates;
    }
    
    public Map<BikeType, Integer> getRequestedMapOfBikes() {
        return requestedMapOfBikes;
    }
}