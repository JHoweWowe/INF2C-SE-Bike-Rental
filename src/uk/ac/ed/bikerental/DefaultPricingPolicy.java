package uk.ac.ed.bikerental;

public class DefaultPricingPolicy extends PricingPolicy{

	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice){
		// Allows a Provider to set a rental price for a BikeType
		// TODO: Given a BikeType and a price, set the price of that BikeType to the price
		// BikeType should have a dailyRentalPrice field??
	}

	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
		// Returns the total price of a given collection of Bikes booked for a length of time
		BigDecimal totalDailyPrice = new BigDecimal(0);
		for (bike : bikes){
			totalDailyPrice.add(bike.getBikeType().getReplacementValue()); // BikeType should have a dailyRentalPrice field to match with description???
		}
		// We assume all bikes in a quote are booked for the same length of time
		totalDailyPrice.multiply(new BigDecimal(duration.toDays()));
		return totalDailyPrice;
	}


}
