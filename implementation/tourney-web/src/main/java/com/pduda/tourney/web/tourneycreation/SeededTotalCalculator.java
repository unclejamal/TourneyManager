package com.pduda.tourney.web.tourneycreation;

import javax.inject.Named;

@Named
class SeededTotalCalculator {

    public int calculateSeededTotal(int teamsTotal) {
        long quarter = teamsTotal / 4;

        int i = 0;
        while (Math.pow(2, i) <= quarter) {
            i++;
        }

        return (int) Math.pow(2, i - 1);
    }
}
