package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import javax.inject.Named;

@Named
public class WkpsCompositeFeeCalculatorFactory {

    public PointsThresholdFeeCalculator createOpenCalc() {
        PointsThresholdFeeCalculator calc = new PointsThresholdFeeCalculator();

        addFeeConfig(calc, RankClass.NOTRANKED, PointsThresholdFeeCalculatorValue.NO_LIMIT, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, 15, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, 5, 10, 15);
        addFeeConfig(calc, RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, 10, 15, 20);
        addFeeConfig(calc, RankClass.SEMIPRO, PointsThresholdFeeCalculatorValue.NO_LIMIT, 15, 20, 30);
        addFeeConfig(calc, RankClass.PRO, PointsThresholdFeeCalculatorValue.NO_LIMIT, 20, 25, 50);
        addFeeConfig(calc, RankClass.MASTER, PointsThresholdFeeCalculatorValue.NO_LIMIT, 25, 30, 70);

        return calc;
    }

    public PointsThresholdFeeCalculator createAmaCalc() {
        PointsThresholdFeeCalculator calc = new PointsThresholdFeeCalculator();

        addFeeConfig(calc, RankClass.NOTRANKED, PointsThresholdFeeCalculatorValue.NO_LIMIT, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, 15, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, 5, 10, 15);
        addFeeConfig(calc, RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, 10, 15, 25);

        return calc;
    }

    public PointsThresholdFeeCalculator createSpecialCalc() {
        PointsThresholdFeeCalculator calc = new PointsThresholdFeeCalculator();

        for (RankClass rankClass : RankClass.values()) {
            addFeeConfig(calc, rankClass, PointsThresholdFeeCalculatorValue.NO_LIMIT, 5, 5, 10);
        }

        return calc;
    }

    private void addFeeConfig(PointsThresholdFeeCalculator calc, RankClass rankClass, int pointLimit, int feeLic, int feeLicOther, int feeNotLic) {
        calc.addFeeConfig(rankClass, pointLimit, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, feeLic);
        calc.addFeeConfig(rankClass, pointLimit, PointsThresholdFeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, feeLicOther);
        calc.addFeeConfig(rankClass, pointLimit, PointsThresholdFeeCalculatorKey.Licensed.NOT_LICENCED, feeNotLic);
    }
}
