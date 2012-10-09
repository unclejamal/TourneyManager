package com.pduda.tourney.web.tourneyview;

import java.util.Comparator;

public class EventsByIdComparator implements Comparator<SelectableTourneyEventPo> {

    @Override
    public int compare(SelectableTourneyEventPo o1, SelectableTourneyEventPo o2) {
        if (o1 == o2) {
            return 0;
        }

        if (o1.getId() > o2.getId()) {
            return -1;
        }

        return 1;
    }
}