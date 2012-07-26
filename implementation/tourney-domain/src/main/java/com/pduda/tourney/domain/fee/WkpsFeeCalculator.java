package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
public class WkpsFeeCalculator {

    private FeeCalculator calculator = new FeeCalculator();

    @PostConstruct
    public void init() {
        calculator.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 0);
        calculator.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 5);
        calculator.addFeeConfig(RankClass.NOVICE, 15, FeeCalculatorKey.Licensed.NOT_LICENCED, 5);
        calculator.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 5);
        calculator.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 10);
        calculator.addFeeConfig(RankClass.NOVICE, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 15);
        calculator.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 10);
        calculator.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 15);
        calculator.addFeeConfig(RankClass.AMATOR, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 20);
        calculator.addFeeConfig(RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 15);
        calculator.addFeeConfig(RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 20);
        calculator.addFeeConfig(RankClass.SEMIPRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 30);
        calculator.addFeeConfig(RankClass.PRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 30);
        calculator.addFeeConfig(RankClass.PRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 25);
        calculator.addFeeConfig(RankClass.PRO, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 50);
        calculator.addFeeConfig(RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_THIS_CLUB, 30);
        calculator.addFeeConfig(RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.LICENSED_AT_OTHER_CLUB, 35);
        calculator.addFeeConfig(RankClass.MASTER, FeeCalculatorValue.NO_LIMIT, FeeCalculatorKey.Licensed.NOT_LICENCED, 70);        
    }
    
    public long getFee(FeeCalculatorKey.Licensed licensed, int points, RankClass rankClass) {
        return calculator.getFee(licensed, points, rankClass);
    }
}
