package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.Collection;

public class Provider {
    
    private String name;
    private Location location;
    private LocalTime openingHours;
    private BigDecimal depositRate;
    private ValuationPolicy valuationPolicy;
    private PricingPolicy pricingPolicy;
    
    public Provider(String name, Location location, LocalTime openingHours, 
            BigDecimal depositRate, ValuationPolicy valuationPolicy, PricingPolicy pricingPolicy) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
        this.depositRate = depositRate;
        this.valuationPolicy = valuationPolicy;
        this.pricingPolicy = pricingPolicy;
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
    
    public PricingPolicy getPricingPolicy() {
        return pricingPolicy;
    }
    
    public void setDepositRate(BigDecimal newDepositRate) {
        depositRate = newDepositRate;
    }
    
    public void setValuationPolicy(ValuationPolicy valuationPolicy) {
        this.valuationPolicy = valuationPolicy;
    }
    
    //Assume PricingPolicy is default in this scenario
    public void setPricingPolicy(PricingPolicy pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
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
    //Helper function to allow the bikes to be return after booking has been made
    public void acceptBikeReturn(Booking booking) {
        Collection<Bike> bikeCollection = booking.getQuote().getCollectionOfBikes();
        for (Bike bike : bikeCollection) {
            bike.setAvailableForRent();
        }
    }
    
    


}
