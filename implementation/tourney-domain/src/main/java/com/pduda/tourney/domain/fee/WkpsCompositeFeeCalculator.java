package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.EventCategory;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class WkpsCompositeFeeCalculator {

    @Inject
    private WkpsCompositeFeeCalculatorFactory factory;
    private PointsThresholdFeeCalculator openCalc;
    private PointsThresholdFeeCalculator amaCalc;
    private PointsThresholdFeeCalculator specialCalc;

    @PostConstruct
    public void init() {
        openCalc = factory.createOpenCalc();
        amaCalc = factory.createAmaCalc();
        specialCalc = factory.createSpecialCalc();
    }

    public long getFee(EventCategory tourneyCategory, PointsThresholdFeeCalculatorKey.Licensed licensed, int points, RankClass rankClass) {
        PointsThresholdFeeCalculator calculator = chooseCalculator(tourneyCategory);
        return calculator.getFee(licensed, points, rankClass);
    }

    private PointsThresholdFeeCalculator chooseCalculator(EventCategory tourneyCategory) {
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
}
