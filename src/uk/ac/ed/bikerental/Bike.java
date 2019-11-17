package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.List;

public class Bike {
    
    private BikeType bikeType;
    private int bikeAgeInYears;
    protected BigDecimal bikePrice;
    private Provider bikeProvider;
    private List<DateRange> bookedDates;
    
    
    public Bike(BikeType bikeType, int bikeAgeInYears, BigDecimal bikePrice, Provider bikeProvider, List<DateRange> bookedDates) {
        this.bikeType = bikeType;
        this.bikeAgeInYears = bikeAgeInYears;
        this.bikePrice = bikePrice;
        this.bikeProvider = bikeProvider;
        this.bookedDates = bookedDates;
    }
    
    public BikeType getBikeType() {
        return bikeType;
    }
    
    public int getBikeAgeInYears() {
        return bikeAgeInYears;
    }
    
    public BigDecimal getBikePrice() {
        return bikePrice;
    }
    
    public Provider getBikeProvider() {
        return bikeProvider;
    }
    
    public List<DateRange> getBookedDates() {
        return bookedDates;
    }
    
    public void addToBookedDates(DateRange dates) {
        bookedDates.add(dates);
    }

}
