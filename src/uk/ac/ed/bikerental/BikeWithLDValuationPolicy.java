package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/** This class calculates the new value of the bike 
 *  from its original value using linear depreciation method
 */

public class BikeWithLDValuationPolicy extends Bike implements ValuationPolicy {
    
    //Explicit empty constructor option for creating an empty Bike object- in case if null data is received
    public BikeWithLDValuationPolicy() {
        super();
    }
    
    public BikeWithLDValuationPolicy(BigDecimal originalReplacementValue, int bikeAgeInYears, BigDecimal depreciationRate) {
        super(originalReplacementValue, bikeAgeInYears, depreciationRate);
    }

    /**
     * TO CONSIDER: Implement Bike getReplacementValue() method where:
     * originalReplacementValue = bike.getType().getReplacementValue();
     */
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal originalReplacementValue = super.getOriginalReplacementValue();
        int bikeAgeInYears = super.getBikeAgeInYears();
        BigDecimal depreciationRate = super.getDepreciationRate();
        
        BigDecimal difference = new BigDecimal(bikeAgeInYears).multiply(depreciationRate).multiply(originalReplacementValue); 
        BigDecimal newReplacementValue = originalReplacementValue.subtract(difference);
        
        /** The BigDecimal value always rounds to next number depending on the value
         *  and formats the BigDecimal value to 2 decimal places
         */
        newReplacementValue = newReplacementValue.setScale(2, RoundingMode.HALF_EVEN);
                
        return newReplacementValue;
    }
    
    

}
