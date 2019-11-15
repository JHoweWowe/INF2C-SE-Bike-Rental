package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LinearDepreciationValuationPolicy implements ValuationPolicy {
    
    private BigDecimal depreciationRate;
    
    public LinearDepreciationValuationPolicy(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }
    
    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BigDecimal originalReplacementValue = bike.getBikeType().getOriginalReplacementValue();
        //int years = LocalDate.now().getYear() - date.getYear();
        int years = 3;
        
        BigDecimal difference = originalReplacementValue.multiply(new BigDecimal(years)).multiply(getDepreciationRate());
        BigDecimal newValue = originalReplacementValue.subtract(difference);
        
        newValue = newValue.setScale(2, RoundingMode.HALF_EVEN).abs();
        
        return newValue;
        
    }
    

}
