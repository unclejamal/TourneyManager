package com.pduda.tourney.domain;

import java.util.Comparator;

public class PlayersByPointsComparator implements Comparator<EventPlayer> {

    @Override
    public int compare(EventPlayer o1, EventPlayer o2) {
        if (o1 == o2) {
            return 0;
        }

        if (o1.getPoints() > o2.getPoints()) {
            return -1;
        }

        return 1;
    }
}
