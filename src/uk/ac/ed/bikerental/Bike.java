package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.List;

//Note: A Bike is a Deliverable
public class Bike implements Deliverable {
    
    private BikeType bikeType;
    private int bikeAgeInYears;
    private Provider bikeProvider;
    private List<DateRange> bookedDates;
    protected boolean isBooked;
    
    //Assume when the bike is registered in the system, the bike is not booked!
    public Bike(BikeType bikeType, int bikeAgeInYears, Provider bikeProvider, List<DateRange> bookedDates) {
        this.bikeType = bikeType;
        this.bikeAgeInYears = bikeAgeInYears;
        this.bikeProvider = bikeProvider;
        this.bookedDates = bookedDates;
        this.isBooked = false;
    }
    
    public BikeType getBikeType() {
        return bikeType;
    }
    
    public int getBikeAgeInYears() {
        return bikeAgeInYears;
    }
    
    public Provider getBikeProvider() {
        return bikeProvider;
    }
    
    public List<DateRange> getBookedDates() {
        return bookedDates;
    }
    
    public void addToBookedDates(DateRange dates) {
        bookedDates.add(dates);
        this.isBooked = true;
    }
    
    public boolean isBooked() {
        return isBooked;
    }
    
    public void setAvailableForRent() {
        isBooked = false;
    }
    
    public void setNotAvailableForRent() {
        isBooked = true;
    }
    

    
    //Used for the 3rd use case scenario:
    @Override
    public void onPickup() {
        isBooked = true;
    }
    @Override
    public void onDropoff() {
        isBooked = false;
        // TODO Auto-generated method stub
    }

}
