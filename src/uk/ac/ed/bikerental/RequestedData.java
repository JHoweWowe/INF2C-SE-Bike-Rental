package uk.ac.ed.bikerental;
import java.util.*;

//Should the methods be static? A RequestedData shouldn't be created as an object?? 
public class RequestedData {

    private Location requestedLocation;
    private DateRange requestedDates;

    public RequestedData(Location requestedLocation, DateRange requestedDates){
        this.requestedLocation = requestedLocation;
        this.requestedDates = requestedDates;
    }

    public Location getRequestedLocation(){
        return requestedLocation;
    }

    public DateRange getRequestedDates(){
        return requestedDates;
    }
}