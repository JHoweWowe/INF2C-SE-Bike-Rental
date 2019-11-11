package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

/** This class calculates the new value of the bike 
 *  from the original value of the bike using linear depreciation
 */

public class BikeLDValuationPolicy implements ValuationPolicy {
    
    private BigDecimal originalReplacementValue;
    private int bikeAgeInYears;
    private double depreciationRate;
    
    //Empty constructor- WHY? Because if any of the values given are null
    public BikeLDValuationPolicy() {
        this.originalReplacementValue = new BigDecimal(0.0);
        this.bikeAgeInYears = 0;
        this.depreciationRate = 0.0;
    }
    
    //TODO: Analyze the importance of the BikeLDValuationPolicy
    public BikeLDValuationPolicy(BigDecimal originalReplacementValue, int bikeAgeInYears, double depreciationRate) {
        this.originalReplacementValue = originalReplacementValue;
        this.bikeAgeInYears = bikeAgeInYears;
        this.depreciationRate = depreciationRate;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        
        //The originalReplacementValue must return as a BigDecimal according to BikeType java class
        //TODO: Implement Bike getReplacementValue() method
        originalReplacementValue = bike.getType().getReplacementValue();
        bikeAgeInYears = Math.abs(date.getYear()-2019);
        
        /**
         * TODO: depreciationRate value must be changed from arbitrary number
         */
        BigDecimal difference = new BigDecimal(depreciationRate * bikeAgeInYears).multiply(originalReplacementValue); 
        BigDecimal newReplacementValue = originalReplacementValue.subtract(difference);
                
        return newReplacementValue;
    }

}
