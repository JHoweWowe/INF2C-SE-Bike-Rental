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
    
    //TODO: To be updated
    public BigDecimal getReplacementValue() {
        return originalReplacementValue;
    }
}