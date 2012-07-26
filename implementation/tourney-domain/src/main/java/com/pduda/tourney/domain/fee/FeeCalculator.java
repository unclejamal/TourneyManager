package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.fee.FeeCalculatorKey.Licensed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;

public class FeeCalculator {

    private Map<FeeCalculatorKey, List<FeeCalculatorValue>> fees = new HashMap<FeeCalculatorKey, List<FeeCalculatorValue>>();

    public void addFeeConfig(RankClass rankClass, int pointsLimit, Licensed licenseType, int fee) {
        FeeCalculatorKey key = new FeeCalculatorKey(rankClass, licenseType);
        
        if (!fees.containsKey(key)) {
            fees.put(key, new ArrayList<FeeCalculatorValue>());
        }
        
        fees.get(key).add(new FeeCalculatorValue(pointsLimit, fee));
    }

    public long getFee(Licensed licensed, int points, RankClass rankClass) {
        List<FeeCalculatorValue> values = fees.get(new FeeCalculatorKey(rankClass, licensed));
        
        for (int i = 0; i < values.size(); i++) {
            FeeCalculatorValue value = values.get(i);
            if (points <= value.getPointsLimit()) {
                return values.get(i).getFee();
            }
        }
        
        return -1;
    }
}
