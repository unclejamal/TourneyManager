package com.pduda.tourney.domain;

import java.util.Comparator;


public class TeamBySeedComparator implements Comparator<Team> {

    @Override
    public int compare(Team o1, Team o2) {
        if (o1 == o2) return 0;
        
        if (o1.getSeed() > o2.getSeed()) return 1;
        
        return -1;
    }

    

}
