package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class DoubleDepreciationValuationPolicy implements ValuationPolicy {
    
    private BigDecimal depreciationRate;
    
    public DoubleDepreciationValuationPolicy(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }
    
    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }
    
    //TODO: Refactor the variables in the calculateValue() method
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal originalReplacementValue = bike.getBikeType().getOriginalReplacementValue();
        int yearsDifference = LocalDate.now().getYear()-date.getYear();
        
        BigDecimal factor = new BigDecimal(yearsDifference-1).multiply(depreciationRate);
        BigDecimal factorE = new BigDecimal(1).subtract(factor);
        BigDecimal factorExp = factorE.pow(yearsDifference);
        
        BigDecimal newValue = originalReplacementValue.multiply(factorExp);
        //Rounds the calculated new value to be rounded up/down to nearest whole number within 2 decimal places
        newValue = newValue.setScale(2, RoundingMode.HALF_EVEN).abs();

        
        return newValue;
    }

}
