package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Provider {
    
    private String name;
    private Location location;
    private LocalTime openingHours;
    private BigDecimal depositRate;
    private ValuationPolicy valuationPolicy;
    
    public Provider(String name, Location location, LocalTime openingHours, BigDecimal depositRate, ValuationPolicy valuationPolicy) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
        this.depositRate = depositRate;
        this.valuationPolicy = valuationPolicy;
    }
    
    public String getName() {
        return name;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public LocalTime getOpeningHours() {
        return openingHours;
    }
    
    public BigDecimal getDepositRate() {
        return depositRate;
    }
    
    public ValuationPolicy getValuationPolicy() {
        return valuationPolicy;
    }
    
    //TODO: Check to be confirmed if obtaining bike price should be in Bike or BikeType
    public void setPrice(Bike bike) {
        bike.bikePrice = bike.getBikePrice();
    }
    
    public void setDepositRate(BigDecimal newDepositRate) {
        depositRate = newDepositRate;
    }
    
    public void setValuationPolicy(ValuationPolicy valuationPolicy) {
        this.valuationPolicy = valuationPolicy;
    }
    
    //Assume the Provider wants to handle return of the bikes- setting if bike is available for rent
    public void setNotAvailableForRent(Bike bike) {
        bike.isBooked = false;
    }
    
    public void setAvailableForRent(Bike bike) {
        bike.isBooked = true;
    }
    
    public boolean sameProviderLocation(Provider otherProvider) {
        String providerAddress = this.getLocation().getAddress();
        String otherProviderAddress = this.getLocation().getAddress();
        String providerPostcode = this.getLocation().getPostcode();
        String otherProviderPostcode = this.getLocation().getPostcode();
        return providerAddress.equals(otherProviderAddress) && providerPostcode.equals(otherProviderPostcode);
    }
    
    //This implements the third use case as required in the system description
    public void returnBikes(Provider otherProvider) {
        if (!(sameProviderLocation(otherProvider))) {
            DeliveryServiceFactory.getDeliveryService();
        }
        //To be implemented
        else {
            
        }
    }


}
