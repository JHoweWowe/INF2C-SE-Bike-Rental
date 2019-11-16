package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Provider {
    
    private String name;
    private Location location;
    private LocalTime openingHours;
    private BigDecimal depositRate;
    
    public Provider(String name, Location location, LocalTime openingHours, BigDecimal depositRate) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
        this.depositRate = depositRate;
    }
    
    public String getName() {
        return name;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public LocalTime getOpeningHours() {
        return openingHours;
    }
    
    public BigDecimal getDepositRate() {
        return depositRate;
    }
    
    //TODO: Check to be confirmed if obtaining bike price should be in Bike or BikeType
    public void setPrice(Bike bike) {
        bike.bikePrice = bike.getBikePrice();
    }
    
    public void setDepositRate(BigDecimal newDepositRate) {
        depositRate = newDepositRate;
    }
    
    
    
    
    
    
    

}
