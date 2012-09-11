package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.fee.PointsThresholdFeeCalculatorKey.Licensed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointsThresholdFeeCalculator {

    private Map<PointsThresholdFeeCalculatorKey, List<PointsThresholdFeeCalculatorValue>> fees = new HashMap<PointsThresholdFeeCalculatorKey, List<PointsThresholdFeeCalculatorValue>>();

    public void addFeeConfig(RankClass rankClass, int pointsLimit, Licensed licenseType, int fee) {
        PointsThresholdFeeCalculatorKey key = new PointsThresholdFeeCalculatorKey(rankClass, licenseType);

        if (!fees.containsKey(key)) {
            fees.put(key, new ArrayList<PointsThresholdFeeCalculatorValue>());
        }

        fees.get(key).add(new PointsThresholdFeeCalculatorValue(pointsLimit, fee));
    }

    public long getFee(Licensed licensed, int points, RankClass rankClass) {
        List<PointsThresholdFeeCalculatorValue> values = fees.get(new PointsThresholdFeeCalculatorKey(rankClass, licensed));

        for (int i = 0; i < values.size(); i++) {
            PointsThresholdFeeCalculatorValue value = values.get(i);
            if (points <= value.getPointsLimit()) {
                return values.get(i).getFee();
            }
        }

        return -1;
    }
}
