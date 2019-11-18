package uk.ac.ed.bikerental;
import java.util.*;

public class RequestedData {

    private Location requestedLocation;
    private DateRange requestedDates;
    private Map<Integer,BikeType> requestedNumType;

    public RequestedData(Location requestedLocation, DateRange requestedDates, Map<Integer,BikeType> requestedNumTypes){
        this.requestedLocation = requestedLocation;
        this.requestedDates = requestedDates;
        this.requestedNumType = requestedNumTypes;
    }

    public Location getRequestedLocation(){
        return requestedLocation;
    }

    public DateRange getRequestedDates(){
        return requestedDates;
    }

    public Map<Integer, BikeType> getRequestedNumTypes(){
        return requestedNumType;
    }
}
