package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    private String bikeTypeName;
    private BigDecimal originalReplacementValue;
    
    public BikeType(String bikeTypeName, BigDecimal originalReplacementValue) {
        this.bikeTypeName = bikeTypeName;
        this.originalReplacementValue = originalReplacementValue;
    }
    
    public BigDecimal getOriginalReplacementValue() {
        return originalReplacementValue;
    }
    
    public String getBikeTypeName() {
        return bikeTypeName;
    }
    
    //TODO: To be updated
    public BigDecimal getReplacementValue() {
        return originalReplacementValue;
    }
}