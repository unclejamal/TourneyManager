package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import javax.inject.Named;

@Named
public class WkpsCompositeFeeCalculatorFactory {

    public PointsThresholdFeeCalculator createOpenCalc() {
        PointsThresholdFeeCalculator calc = new PointsThresholdFeeCalculator();

        addFeeConfig(calc, RankClass.NOTRANKED, PointsThresholdFeeCalculatorValue.NO_LIMIT, 0, 0, 10);
        addFeeConfig(calc, RankClass.NOVICE, 15, 0, 0, 10);
        addFeeConfig(calc, RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, 5, 5, 15);
        addFeeConfig(calc, RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, 10, 10, 20);
        addFeeConfig(calc, RankClass.SEMIPRO, PointsThresholdFeeCalculatorValue.NO_LIMIT, 15, 15, 30);
        addFeeConfig(calc, RankClass.PRO, PointsThresholdFeeCalculatorValue.NO_LIMIT, 20, 20, 50);
        addFeeConfig(calc, RankClass.MASTER, PointsThresholdFeeCalculatorValue.NO_LIMIT, 25, 25, 70);

        return calc;
    }

    public PointsThresholdFeeCalculator createAmaCalc() {
        PointsThresholdFeeCalculator calc = new PointsThresholdFeeCalculator();

        addFeeConfig(calc, RankClass.NOTRANKED, PointsThresholdFeeCalculatorValue.NO_LIMIT, 0, 0, 10);
        addFeeConfig(calc, RankClass.NOVICE, 15, 0, 0, 10);
        addFeeConfig(calc, RankClass.NOVICE, PointsThresholdFeeCalculatorValue.NO_LIMIT, 5, 5, 15);
        addFeeConfig(calc, RankClass.AMATOR, PointsThresholdFeeCalculatorValue.NO_LIMIT, 10, 10, 25);

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
