package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }
    
    
    @Test
    void testNoOverlap() {
        dateRange1 = new DateRange(LocalDate.of(2019, 1, 1),LocalDate.of(2019, 1, 31));
        dateRange2 = new DateRange(LocalDate.of(2019, 2, 2),LocalDate.of(2019, 2, 28));
        assertFalse(dateRange1.overlaps(dateRange2));
    }
    @Test
    void testDateRange1IsNextToDateRange2ButNoOverlap() {
        dateRange1 = new DateRange(LocalDate.of(2019, 3, 1),LocalDate.of(2019, 3, 31));
        dateRange2 = new DateRange(LocalDate.of(2019, 4, 1),LocalDate.of(2019, 4, 30));
        assertFalse(dateRange1.overlaps(dateRange2));
    }
    @Test
    void testDateRange1OverlapsDateRange2() {
        dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),LocalDate.of(2019, 2, 7));
        dateRange2 = new DateRange(LocalDate.of(2019, 2, 1),LocalDate.of(2019, 2, 22));
        assertTrue(dateRange1.overlaps(dateRange2));
    }
    @Test
    void testDateRange2OverlapsDateRange1() {
        dateRange1 = new DateRange(LocalDate.of(2019, 2, 1),LocalDate.of(2019, 2, 7));
        dateRange2 = new DateRange(LocalDate.of(2019, 1, 1),LocalDate.of(2019, 2, 12));
        assertTrue(dateRange1.overlaps(dateRange2));
    }    
    @Test
    void testDateRangeAreEqual() {
        dateRange1 = new DateRange(LocalDate.of(2019, 3, 1),LocalDate.of(2019, 3, 31));
        dateRange2 = new DateRange(LocalDate.of(2019, 3, 1),LocalDate.of(2019, 3, 31));
        assertTrue(dateRange1.overlaps(dateRange2));
    }
    @Test
    void testDateRange2IsUndertestDateRange1() {
        dateRange1 = new DateRange(LocalDate.of(2019, 3, 1),LocalDate.of(2019, 5, 31));
        dateRange2 = new DateRange(LocalDate.of(2019, 4, 1),LocalDate.of(2019, 4, 30));
        assertTrue(dateRange1.overlaps(dateRange2));
    }

}
