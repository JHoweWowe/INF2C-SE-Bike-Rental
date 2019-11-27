package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quote {
    
    private Provider provider;
    private Collection<Bike> bikes;
    private BigDecimal totalDepositAmount;
    private BigDecimal totalPrice;

    
    public Quote(Provider provider, Collection<Bike> bikes, 
           BigDecimal totalPrice, BigDecimal totalDepositAmount) {
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
    
    //Helper function for the MainSystem to obtain the Map of BikeTypes
    public Map<BikeType,Integer> getMapOfBikes() {
        Map<BikeType,Integer> mapOfBikes = new HashMap<BikeType,Integer>();
        List<BikeType> bts = new ArrayList<BikeType>();
        for (Bike b : bikes) {
          bts.add(b.getBikeType());
        }
        for (BikeType bt : bts) {
          int num = Collections.frequency(bts,bt);
          mapOfBikes.put(bt,num);
        }
        /**for (Map.Entry<BikeType,Integer> entry : mapOfBikes.entrySet()) {
            BikeType bt = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("BikeType is " + bt.getBikeTypeName() + " and value is " + value);
        }**/
        return mapOfBikes;
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
