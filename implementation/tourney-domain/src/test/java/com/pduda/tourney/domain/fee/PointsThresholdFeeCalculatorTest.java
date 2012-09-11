package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.fee.PointsThresholdFeeCalculatorKey.Licensed;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointsThresholdFeeCalculatorTest {

    private PointsThresholdFeeCalculator cut;

    @Before
    public void setUp() {
        cut = new PointsThresholdFeeCalculator();
    }

    @Test
    public void xxx() {
        cut.addFeeConfig(RankClass.NOVICE, 15, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        cut.addFeeConfig(RankClass.NOVICE, 15, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        cut.addFeeConfig(RankClass.NOVICE, 15, PointsThresholdFeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        cut.addFeeConfig(RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 5);
        cut.addFeeConfig(RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 10);
        cut.addFeeConfig(RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, PointsThresholdFeeCalculatorKey.Licensed.NOT_LICENCED, 15);
        cut.addFeeConfig(RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 10);
        cut.addFeeConfig(RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 15);
        cut.addFeeConfig(RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, PointsThresholdFeeCalculatorKey.Licensed.NOT_LICENCED, 20);

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