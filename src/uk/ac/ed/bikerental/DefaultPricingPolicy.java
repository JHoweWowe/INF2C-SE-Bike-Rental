package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultPricingPolicy implements PricingPolicy {

    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice){
    }

    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
        // Returns the total price of a given collection of Bikes booked for a length of time
        BigDecimal totalDailyPrice = new BigDecimal(0.0);
        for (Bike bike : bikes){
            totalDailyPrice = totalDailyPrice.add(bike.getBikeType().getBikeTypePrice()); 
            // BikeType should have a dailyRentalPrice field to match with description???
        }
        // We assume all bikes in a quote are booked for the same length of time
        totalDailyPrice = totalDailyPrice.multiply(new BigDecimal(duration.toDays()));
        return totalDailyPrice;
    }
    
    /*
    public static void main(String[] args) {
        Collection<Bike> bikeCollection1 = new ArrayList<Bike>();
        BikeType bikeType1 = new BikeType("Mountain bike",new BigDecimal(900));
        Location loc1 = new Location("EH16 5AY","Pollock Halls");
        DefaultPricingPolicy defaultPricePolicy = new DefaultPricingPolicy();
        ValuationPolicy valuationPolicy = null;
        Provider bikeProvider1 = new Provider("Cycles4Hire",loc1,LocalTime.of(9, 00),new BigDecimal(0.3),valuationPolicy);
        List<DateRange> bikeCollection1BookedDates = new ArrayList<DateRange>();
        
        BigDecimal totalPrice = new BigDecimal(900);
        
        bikeCollection1.add(new Bike(bikeType1,3,totalPrice,bikeProvider1,bikeCollection1BookedDates));
        bikeCollection1.add(new Bike(bikeType1,3,totalPrice,bikeProvider1,bikeCollection1BookedDates));
        
        DateRange requestedDates = new DateRange(LocalDate.of(2018, 1, 5), LocalDate.of(2018, 1, 8));

        BigDecimal test = defaultPricePolicy.calculatePrice(bikeCollection1,requestedDates);

        System.out.println(test);
    }
    */


}
