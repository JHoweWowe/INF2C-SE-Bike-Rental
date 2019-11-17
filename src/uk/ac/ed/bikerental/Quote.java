package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Quote {
    
    private Provider provider;
    private Collection<Bike> bikes;
    private BigDecimal totalDepositAmount;
    private BigDecimal totalPrice;
    
    public Quote(Provider provider, Collection<Bike> bikes, BigDecimal totalPrice, BigDecimal totalDepositAmount) {
        this.provider = provider;
        this.bikes = bikes;
        this.totalDepositAmount = totalDepositAmount;
        this.totalPrice = totalPrice;
    }
    
    public Provider getProvider() {
        return provider;
    }
    
    public Collection<Bike> getCollectionOfBikes() {
        return bikes;
    }
    
    public BigDecimal getTotalDepositAmount() {
        return totalDepositAmount;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    //Given a Collection of Bikes, this method calculates the total price in the quote
    public BigDecimal calculateTotalPrice(Collection<Bike> bikes) {
        totalPrice = new BigDecimal(0);
        for (Bike b : bikes) {
            totalPrice.add(b.getBikePrice());
        }
        return totalPrice;
    }
    
    public BigDecimal calculateDepositAmount(Collection<Bike> bikes) {
        totalDepositAmount = new BigDecimal(0);
        totalDepositAmount = provider.getDepositRate().multiply(calculateTotalPrice(bikes));
        return totalDepositAmount;
        
    }

}
