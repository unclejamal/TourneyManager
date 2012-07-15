package com.pduda.tourney.domain.report;

import java.util.Comparator;

public class RoundComparator implements Comparator<GamesReportRound> {

    @Override
    public int compare(GamesReportRound o1, GamesReportRound o2) {
        if (o1 == o2) {
            return 0;
        }
        
        if (o1.getOrder() > o2.getOrder()) {
            return 1;
        } else {
            return -1;
        }
    }
}
