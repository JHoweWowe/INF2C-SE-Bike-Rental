package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class Bike {
    
    private BigDecimal originalReplacementValue;
    private int bikeAgeInYears;
    private BigDecimal depreciationRate;
    
    public Bike() {
        this.originalReplacementValue = new BigDecimal(0.0);
        this.bikeAgeInYears = 0;
        this.depreciationRate = new BigDecimal(0.0);
    }
    
    public Bike(BigDecimal originalReplacementValue, int bikeAgeinYears, BigDecimal depreciationRate) {
        this.originalReplacementValue = originalReplacementValue;
        this.bikeAgeInYears = bikeAgeinYears;
        this.depreciationRate = depreciationRate;
    }
    
    //Getter method to obtain originalReplacementValue which then can be used to calculate newValue of the Bike
    public BigDecimal getOriginalReplacementValue() {
        return originalReplacementValue;
    }
    
    public int getBikeAgeInYears() {
        return bikeAgeInYears;
    }
    
    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }
    
    
    public BikeType getType() {
        
        // TODO: Implement Bike.getType
        
        assert false;
        
        return null;
        
    }
}