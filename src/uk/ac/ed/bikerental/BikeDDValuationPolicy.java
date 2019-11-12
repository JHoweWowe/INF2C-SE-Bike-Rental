package uk.ac.ed.bikerental;
    
public class BikeDDValuationPolicy implements ValuationPolicy() {
// Fields ----------------------------------------------------
	private BigDecimal originalReplacementValue;
    private int bikeAgeInYears;
    private double depreciationRate;
// Constructors ----------------------------------------------------
    public DDBDecreciation() {
    	this.originalReplacementValue = new BigDecimal(0.0);
        this.bikeAgeInYears = 0;
        this.depreciationRate = 0.0;
    }

    public DDBDecreciation(BigDecimal originalReplacementValue, int bikeAgeInYears, double depreciationRate){
    	this.originalReplacementValue = originalReplacementValue;
        this.bikeAgeInYears = bikeAgeInYears;
        this.depreciationRate = depreciationRate;
    }
   
// Methods ----------------------------------------------------
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date){
        // Update the values by referencing wih bike type and age
    	originalReplacementValue = bike.getType().getReplacementValue();
        bikeAgeInYears = Math.abs(date.getYear()-2019);
        BigDecimal depFactor = new BigDecimal(1 - 2.0*depreciationRate);
    	return depFactor.pow(bikeAgeInYears).multiply(originalReplacementValue);
    }
}

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
