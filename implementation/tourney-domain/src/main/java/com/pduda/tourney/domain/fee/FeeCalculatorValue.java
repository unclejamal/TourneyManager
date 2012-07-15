package com.pduda.tourney.domain.fee;

public class FeeCalculatorValue {

    public static final int NO_LIMIT = Integer.MAX_VALUE;
    private int pointsLimit;
    private int fee;

    public FeeCalculatorValue(int pointsLimit, int fee) {
        this.pointsLimit = pointsLimit;
        this.fee = fee;
    }

    public int getPointsLimit() {
        return pointsLimit;
    }

    public int getFee() {
        return fee;
    }
}
