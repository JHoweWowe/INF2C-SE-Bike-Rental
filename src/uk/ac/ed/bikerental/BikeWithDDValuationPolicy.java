package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/** This class calculates the new value of the bike 
 *  from its original value using the
 *  double declining balance depreciation method
 */
    
public class BikeWithDDValuationPolicy extends Bike implements ValuationPolicy {
    
    private BigDecimal originalReplacementValue;
    private int bikeAgeInYears;
    private double depreciationRate;
    
    //Explicit empty constructor option for creating an empty Bike object- in case if null data is received
    public BikeWithDDValuationPolicy() {
        this.originalReplacementValue = new BigDecimal(0.0);
        this.bikeAgeInYears = 0;
        this.depreciationRate = 0.0;
    }

    public BikeWithDDValuationPolicy(BigDecimal originalReplacementValue, int bikeAgeInYears, double depreciationRate){
        this.originalReplacementValue = originalReplacementValue;
        this.bikeAgeInYears = bikeAgeInYears;
        this.depreciationRate = depreciationRate;
    }
    
    //Getter method to obtain originalReplacementValue which then can be used to calculate newValue of the Bike
    public BigDecimal getOriginalReplacementValue() {
        return originalReplacementValue;
    }
    
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        originalReplacementValue = getOriginalReplacementValue();
        bikeAgeInYears = Math.abs(date.getYear() - 2019);
        
        BigDecimal depFactor = new BigDecimal(1 - 2.0 * depreciationRate);
        BigDecimal doubleDecliningBalanceValue = depFactor.pow(bikeAgeInYears).multiply(originalReplacementValue);
        
        /**
         * The BigDecimal value always rounds to next number depending on the value
         * and formats the BigDecimal value to 2 decimal places
         */
        doubleDecliningBalanceValue = doubleDecliningBalanceValue.setScale(2, RoundingMode.HALF_EVEN);
        return doubleDecliningBalanceValue;
    }
    
}