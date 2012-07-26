package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.fee.FeeCalculatorKey.Licensed;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FeeCalculatorTest {

    private FeeCalculator cut;

    @Before
    public void setUp() {
        cut = new FeeCalculator();
    }

    @Test
    public void xxx() {
//Novice do 15 punktów: 0 zł 5 zł 5 zł
//Novice powyżej 15 punktów 5 zł 15 zł 10 zł
//Ama 10 zł 20 zł 15 zł
//Semi-pro 15 zł 30 zł 20 zł
//Pro 30 zł 50 zł 25 zł
//Master 30 zł 70 zł 35 zł
        cut.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        cut.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        cut.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        cut.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 5);
        cut.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 10);
        cut.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 15);
        cut.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 10);
        cut.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 15);
        cut.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 20);

        assertEquals(0, cut.getFee(Licensed.LICENSED_AT_THIS_CLUB, 0, RankClass.NOVICE));
        assertEquals(5, cut.getFee(Licensed.LICENSED_AT_OTHER_CLUB, 0, RankClass.NOVICE));
        assertEquals(5, cut.getFee(Licensed.NOT_LICENCED, 0, RankClass.NOVICE));

        assertEquals(0, cut.getFee(Licensed.LICENSED_AT_THIS_CLUB, 13, RankClass.NOVICE));
        assertEquals(5, cut.getFee(Licensed.LICENSED_AT_OTHER_CLUB, 13, RankClass.NOVICE));
        assertEquals(5, cut.getFee(Licensed.NOT_LICENCED, 13, RankClass.NOVICE));

        assertEquals(0, cut.getFee(Licensed.LICENSED_AT_THIS_CLUB, 15, RankClass.NOVICE));
        assertEquals(5, cut.getFee(Licensed.LICENSED_AT_OTHER_CLUB, 15, RankClass.NOVICE));
        assertEquals(5, cut.getFee(Licensed.NOT_LICENCED, 15, RankClass.NOVICE));
        
        assertEquals(5, cut.getFee(Licensed.LICENSED_AT_THIS_CLUB, 18, RankClass.NOVICE));
        assertEquals(10, cut.getFee(Licensed.LICENSED_AT_OTHER_CLUB, 18, RankClass.NOVICE));
        assertEquals(15, cut.getFee(Licensed.NOT_LICENCED, 18, RankClass.NOVICE));
        
        assertEquals(10, cut.getFee(Licensed.LICENSED_AT_THIS_CLUB, 78, RankClass.AMATOR));
        assertEquals(15, cut.getFee(Licensed.LICENSED_AT_OTHER_CLUB, 78, RankClass.AMATOR));
        assertEquals(20, cut.getFee(Licensed.NOT_LICENCED, 78, RankClass.AMATOR));
    }
}