package com.pduda.tourney.web.creation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeededTotalCalculatorTest {
    
    private SeededTotalCalculator cut;
    
    @Before
    public void setUp() {
        cut = new SeededTotalCalculator();
    }

    @Test
    public void calculateSeededTotal_powerOf2() {
        assertEquals(2, cut.calculateSeededTotal(8));
        assertEquals(4, cut.calculateSeededTotal(16));
        assertEquals(8, cut.calculateSeededTotal(32));
    }
    
    @Test
    public void calculateSeededTotal_complexCase() {
        assertEquals(1, cut.calculateSeededTotal(5));
        assertEquals(1, cut.calculateSeededTotal(6));
        assertEquals(1, cut.calculateSeededTotal(7));
        
        assertEquals(2, cut.calculateSeededTotal(9));
        assertEquals(2, cut.calculateSeededTotal(10));
        assertEquals(2, cut.calculateSeededTotal(11));
        assertEquals(2, cut.calculateSeededTotal(15));
    }
}
