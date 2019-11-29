package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    private String bikeTypeName;
    private BigDecimal originalReplacementValue;
    private BigDecimal bikeTypedailyRentalPrice;
    
    public BikeType(String bikeTypeName, BigDecimal originalReplacementValue, BigDecimal bikeTypePrice) {
        this.bikeTypeName = bikeTypeName;
        this.originalReplacementValue = originalReplacementValue;
        this.bikeTypedailyRentalPrice = bikeTypePrice;
    }
    
    public BigDecimal getOriginalReplacementValue() {
        return originalReplacementValue;
    }
    
    public String getBikeTypeName() {
        return bikeTypeName;
    }
    
    public BigDecimal getBikeTypePrice() {
        return bikeTypedailyRentalPrice;
    }
    
    //TODO: To be updated
    public BigDecimal getReplacementValue() {
        return originalReplacementValue;
    }
}