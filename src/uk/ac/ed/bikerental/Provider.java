package uk.ac.ed.bikerental;

import java.time.LocalTime;

public class Provider {
    
    private String name;
    private Location location;
    private LocalTime openingHours;
    
    public Provider(String name, Location location, LocalTime openingHours) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
    }
    
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
