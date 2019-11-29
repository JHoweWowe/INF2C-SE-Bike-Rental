package uk.ac.ed.bikerental;

/**
 * This class represents the Location of a bike provider
 */
public class Location {
    /**
     * The first field is the postcode
     * The second field is the address
     */
    private String postcode;
    private String address;
    /**
     * Class constructor which has the postcode and the address
     * @param postcode  
     * @param address the 
     */
    public Location(String postcode, String address) {
        this.postcode = postcode;
        this.address = address;
    }
    /**
     * Checks if the Location is near to another Location
     *  
     * @param another Location
     * @return true if first 2 digits of this Location's postcode equals to
     * the first 2 digits of the other Location's postcode AND false otherwise
     * @throws IllegalArgumentException if both postcode length is less than 5
     */
    public boolean isNearTo(Location other) throws IllegalArgumentException {
        //Will throw AssertionError if the both Location postcodes are smaller than 5
        if (this.getPostcode().length() < 5 || other.getPostcode().length() < 5) {
            throw new IllegalArgumentException("Postcode length cannot be less than 5");
        }
        if (getPostcode().substring(0,2).equals(other.getPostcode().substring(0,2))) {
            return true;
        }
        return false;
    }
    /**
     * Obtains the Location's postcode
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     * Obtains the address
     * @return address
     */
    public String getAddress() {
        return address;
    }
    /**
     * 
     * @param otherLocation represents the Location of another Provider
     * @return if current address equals to another Location's address
     * and current postcode equals to another Location's postcode
     */
    public boolean equals(Location otherLocation) {
        return (getAddress().equals(otherLocation.getAddress()) && getPostcode().equals(otherLocation.getPostcode()));
    }
    
}
