package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class Bike {
    
    private BikeType bikeType;
    private int bikeAgeInYears;
    protected BigDecimal bikePrice;
    private Provider bikeProvider;
    
    
    public Bike(BikeType bikeType, int bikeAgeInYears, BigDecimal bikePrice, Provider bikeProvider) {
        this.bikeType = bikeType;
        this.bikeAgeInYears = bikeAgeInYears;
        this.bikePrice = bikePrice;
        this.bikeProvider = bikeProvider;
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

}
