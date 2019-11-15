package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    private BigDecimal originalReplacementValue;
    
    public BikeType(BigDecimal originalReplacementValue) {
        this.originalReplacementValue = originalReplacementValue;
    }
    
    public BigDecimal getOriginalReplacementValue() {
        return originalReplacementValue;
    }
    
    public BigDecimal getReplacementValue() {
        // TODO: Implement Bike.getReplacementValue
        assert false;
        return null;
    }
}