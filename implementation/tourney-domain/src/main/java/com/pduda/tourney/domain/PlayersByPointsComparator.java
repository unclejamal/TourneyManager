package com.pduda.tourney.domain;

import java.util.Comparator;

public class PlayersByPointsComparator implements Comparator<TourneyPlayer> {

    @Override
    public int compare(TourneyPlayer o1, TourneyPlayer o2) {
        if (o1 == o2) {
            return 0;
        }

        if (o1.getPoints() > o2.getPoints()) {
            return -1;
        }

        return 1;
    }
}
