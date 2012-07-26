package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.TournamentCategory;
import javax.inject.Named;

@Named
public class WkpsFeeCalculator {

    private FeeCalculator openCalc = new FeeCalculator();
    private FeeCalculator amaCalc = new FeeCalculator();

    public WkpsFeeCalculator() {
        initOpenCalc(openCalc);
        initAmaCalc(amaCalc);
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
            default:
                return openCalc;
        }
    }

    private void initOpenCalc(FeeCalculator calc) {
        calc.addFeeConfig(RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        calc.addFeeConfig(RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        calc.addFeeConfig(RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        calc.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        calc.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        calc.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        calc.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 5);
        calc.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 10);
        calc.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 15);
        calc.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 10);
        calc.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 15);
        calc.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 20);
        calc.addFeeConfig(RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 15);
        calc.addFeeConfig(RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 20);
        calc.addFeeConfig(RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 30);
        calc.addFeeConfig(RankClass.PRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 30);
        calc.addFeeConfig(RankClass.PRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 25);
        calc.addFeeConfig(RankClass.PRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 50);
        calc.addFeeConfig(RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 30);
        calc.addFeeConfig(RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 35);
        calc.addFeeConfig(RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 70);
    }

    private void initAmaCalc(FeeCalculator calc) {
        calc.addFeeConfig(RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        calc.addFeeConfig(RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        calc.addFeeConfig(RankClass.NOTRANKED, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        calc.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        calc.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        calc.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        calc.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 5);
        calc.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 10);
        calc.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 10);
        calc.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 10);
        calc.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 15);
        calc.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 25);
    }
}
