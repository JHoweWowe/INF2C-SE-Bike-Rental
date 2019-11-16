package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Quote {
    
    protected Provider provider;
    protected Collection<Bike> bikes;
    protected BigDecimal totalDepositAmount;
    protected BigDecimal totalPrice;
    
    public Quote(Provider provider, Collection<Bike> bikes, BigDecimal totalPrice, BigDecimal totalDepositAmount) {
        this.provider = provider;
        this.bikes = bikes;
        this.totalDepositAmount = totalDepositAmount;
        this.totalPrice = totalPrice;
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
