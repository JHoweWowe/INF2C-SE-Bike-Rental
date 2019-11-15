package uk.ac.ed.bikerental;

public class Bike {
    
    private BikeType bikeType;
    private int bikeAgeInYears;
    
    public Bike(BikeType bikeType, int bikeAgeInYears) {
        this.bikeType = bikeType;
        this.bikeAgeInYears = bikeAgeInYears;
    }
    
    public BikeType getBikeType() {
        return bikeType;
    }
    
    public int getBikeAgeInYears() {
        return bikeAgeInYears;
    }

}
