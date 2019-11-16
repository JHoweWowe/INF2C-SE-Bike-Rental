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
    
    public boolean isNearTo(Location other) {
        //Will throw AssertionError if the both Location postcodes are smaller than 2
        assert getPostcode().length() > 2 && other.getPostcode().length() > 2;
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
    
    // You can add your own methods here
}
