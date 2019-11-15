package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/** This class calculates the new value of the bike 
 *  from its original value using the
 *  double declining balance depreciation method
 */
    
public class BikeWithDDValuationPolicy extends Bike implements ValuationPolicy {

    //Explicit empty constructor option for creating an empty Bike object- in case if null data is received
    public BikeWithDDValuationPolicy() {
        super();
    }

    public BikeWithDDValuationPolicy(BigDecimal originalReplacementValue, int bikeAgeInYears, BigDecimal depreciationRate){
        super(originalReplacementValue, bikeAgeInYears, depreciationRate);
    }
    
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal originalReplacementValue = super.getOriginalReplacementValue();
        int bikeAgeInYears = super.getBikeAgeInYears();
        BigDecimal depreciationRate = super.getDepreciationRate();
        
        //TODO: Consider the yearFactor to be 0 or negative??
        BigDecimal yearFactor = new BigDecimal(bikeAgeInYears-1).multiply(depreciationRate).subtract(new BigDecimal(1.0));
        BigDecimal doubleDecliningBalanceValue = yearFactor.pow(bikeAgeInYears).multiply(originalReplacementValue);
        
        /** The BigDecimal value always rounds to next number depending on the value
         *  and formats the BigDecimal value to 2 decimal places and also returns the absolute value
         */
        doubleDecliningBalanceValue = doubleDecliningBalanceValue.setScale(2, RoundingMode.HALF_EVEN).abs();
        return doubleDecliningBalanceValue;
    }
    
}