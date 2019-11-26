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
    
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) throws IllegalArgumentException {
        if (depreciationRate.doubleValue() < 0 || depreciationRate.doubleValue() > 1) {
            throw new IllegalArgumentException("Depreciation rate must be a BigDecimal between 0 and 1");
        }
        BigDecimal originalReplacementValue = bike.getBikeType().getOriginalReplacementValue();
        int yearsDifference = Math.abs(LocalDate.now().getYear()-date.getYear());
        
        BigDecimal factor = new BigDecimal(1.0).subtract(new BigDecimal(2.0).multiply(depreciationRate));
        BigDecimal factorExp = factor.pow(yearsDifference);
        BigDecimal newValue = originalReplacementValue.multiply(factorExp);
        //Rounds the calculated new value to be rounded up/down to nearest whole number within 2 decimal places
        newValue = newValue.setScale(2, RoundingMode.HALF_EVEN);
        
        if (newValue.doubleValue() < 0) {
            throw new IllegalArgumentException("The value of the bike cannot be negative");
        }
        
        return newValue;
    }

}
