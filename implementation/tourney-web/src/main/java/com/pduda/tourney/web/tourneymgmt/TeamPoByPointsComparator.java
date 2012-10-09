package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.Team;
import com.pduda.tourney.web.tourneymgmt.TeamPo;
import java.util.Comparator;

public class TeamPoByPointsComparator implements Comparator<TeamPo> {

    @Override
    public int compare(TeamPo o1, TeamPo o2) {
        if (o1 == o2) {
            return 0;
        }

        if (o1.getPoints() > o2.getPoints()) {
            return -1;
        }

        return 1;
    }
}
