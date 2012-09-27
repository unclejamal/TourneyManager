package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.Team;
import java.util.Comparator;

public class TeamsByPointsComparator implements Comparator<Team> {

    @Override
    public int compare(Team o1, Team o2) {
        if (o1 == o2) {
            return 0;
        }

        if (o1.getPoints() > o2.getPoints()) {
            return -1;
        }

        return 1;
    }
}
