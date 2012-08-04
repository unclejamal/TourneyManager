package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.TournamentCategory;
import javax.inject.Named;

@Named
public class WkpsFeeCalculator {

    private FeeCalculator openCalc = new FeeCalculator();
    private FeeCalculator amaCalc = new FeeCalculator();
    private FeeCalculator specialCalc = new FeeCalculator();

    public WkpsFeeCalculator() {
        initOpenCalc(openCalc);
        initAmaCalc(amaCalc);
        initSpecialCalc(specialCalc);
    }

    public long getFee(TournamentCategory tourneyCategory, FeeCalculatorKey.Licensed licensed, int points, RankClass rankClass) {
        FeeCalculator calculator = chooseCalculator(tourneyCategory);
        return calculator.getFee(licensed, points, rankClass);
    }

    private FeeCalculator chooseCalculator(TournamentCategory tourneyCategory) {
        // TODO
        switch (tourneyCategory) {
            case AD:
            case AS:
                return amaCalc;
            case OD:
            case OS:
                return openCalc;
            case DYP:
                return specialCalc;
            default:
                return openCalc;
        }
    }

    private void initOpenCalc(FeeCalculator calc) {
        addFeeConfig(calc, RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, 15, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, 5, 10, 15);
        addFeeConfig(calc, RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, 10, 15, 20);
        addFeeConfig(calc, RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, 15, 20, 30);
        addFeeConfig(calc, RankClass.PRO, FeeCalculatorValue.NO_LIMIT, 20, 25, 50);
        addFeeConfig(calc, RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, 25, 30, 70);
    }

    private void initAmaCalc(FeeCalculator calc) {
        addFeeConfig(calc, RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, 15, 0, 5, 10);
        addFeeConfig(calc, RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, 5, 10, 15);
        addFeeConfig(calc, RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, 10, 15, 25);
    }

    private void initSpecialCalc(FeeCalculator calc) {
        for (RankClass rankClass : RankClass.values()) {
            addFeeConfig(calc, rankClass, FeeCalculatorValue.NO_LIMIT, 5, 5, 10);
        }
    }

    private void addFeeConfig(FeeCalculator calc, RankClass rankClass, int pointLimit, int feeLic, int feeLicOther, int feeNotLic) {
        calc.addFeeConfig(rankClass, pointLimit, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, feeLic);
        calc.addFeeConfig(rankClass, pointLimit, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, feeLicOther);
        calc.addFeeConfig(rankClass, pointLimit, FeeCalculatorKey.Licensed.NOT_LICENCED, feeNotLic);
    }
}
