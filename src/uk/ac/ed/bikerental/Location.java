package uk.ac.ed.bikerental;

//TODO: JavaDoc documentation to be completed

public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    //Checks if this Location has the same first 2 digits as the other Location
    //TODO: How to implement if the two digits don't add up
    public boolean isNearTo(Location other) {
        
        if (this.getPostcode().substring(0,2).equals(other.getPostcode().substring(0,2))) {
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
    
    // You can add your own methods here
}
