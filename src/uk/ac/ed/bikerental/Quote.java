package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class Quote {
    
    protected Provider provider;
    protected Bike bike;
    
    private BigDecimal totalPrice;
    private BigDecimal depositAmount;
    
    /**
     * TODO: Write empty constructor option for 
     * creating an empty Quote object- in case if null data is received???
     */
    
    public Quote(Provider provider, Bike bike, BigDecimal totalPrice, BigDecimal depositAmount) {
        this.provider = provider;
        this.bike = bike;
        this.totalPrice = totalPrice;
        this.depositAmount = depositAmount;
    }
    
    //Methods should be changed to have the ValuationPolicyTests
    
    public BigDecimal calculateTotalPrice() {
        return totalPrice;
    }
    
    public BigDecimal calculateDepositAmount() {
        return depositAmount;
    }

}
