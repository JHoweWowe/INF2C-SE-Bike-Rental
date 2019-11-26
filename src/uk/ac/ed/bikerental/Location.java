package uk.ac.ed.bikerental;

//TODO: JavaDoc documentation to be completed

public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        this.postcode = postcode;
        this.address = address;
    }
    
    public boolean isNearTo(Location other) throws IllegalArgumentException {
        //Will throw AssertionError if the both Location postcodes are smaller than 6
        if (this.getPostcode().length() < 6 || other.getPostcode().length() < 6) {
            throw new IllegalArgumentException("Postcode length cannot be less than 6");
        }
        if (getPostcode().substring(0,2).equals(other.getPostcode().substring(0,2))) {
            return true;
        }
        return false;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
    //Implemented for DeliveryService
    public boolean equals(Location otherLocation) {
        return (getAddress().equals(otherLocation.getAddress()) && getPostcode().equals(otherLocation.getPostcode()));
    }
    
    // You can add your own methods here
}
