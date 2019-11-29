package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * This class represents the date range
 */
public class DateRange {
    /**
     * Variables start and end represent a date using the LocalDate class
     */
    private LocalDate start, end;
    /**
     * Creates the DateRange constructor using the parameters start and end
     * 
     * @param start the beginning of the dateRange
     * @param end the end of the dateRange
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Gets the start of the DateRange
     * 
     * @return the start of the DateRange
     */
    public LocalDate getStart() {
        return this.start;
    }
    /**
     * Gets the end of the DateRange
     * 
     * @return the end of the DateRange
     */
    public LocalDate getEnd() {
        return this.end;
    }
    /**
     * Represents the standard set of dateRange unit in years 
     * 
     * @return a number which is the number of years between the start and end of the date range
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }
    /**
     * Represents the standard set of dateRange unit in days 
     * 
     * @return a number which is the number of days between the start and end of the date range
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }
    /**
     * Checks if the current date range overlaps another date range 
     * 
     * @return true if the current dateRange start date is NOT after the other dateRange end date
     * and the other dateRange start date is NOT after the current dateRange end date
     * and false if it does not satisfy the following conditions
     */
    public Boolean overlaps(DateRange other) {
        // TODO: implement date range intersection checking
        //assert (this.getStart() != null && this.getEnd() != null && other.getStart() != null && other.getEnd() != null);
        return !this.getStart().isAfter(other.getEnd()) 
                && !other.getStart().isAfter(this.getEnd());
    }
    /**
     * Creates the DateRange as a hashcode value
     * 
     * @return the hashcode value with the current DateRange start and end dates
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }
    /**
     * Checks if the current DateRange is equal to a Object
     * 
     * @return true if the Object is the same as DateRange
     * @return false if the Object is null or of not in the same class as DateRange
     */
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
}
