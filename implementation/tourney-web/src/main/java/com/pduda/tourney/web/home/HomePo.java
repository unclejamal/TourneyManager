package com.pduda.tourney.web.home;

import java.util.ArrayList;
import java.util.List;

public class HomePo {

    private List<TourneyPo> tourneys = new ArrayList<TourneyPo>();

    public List<TourneyPo> getTourneys() {
        return tourneys;
    }

    public void addTourney(TourneyPo tourney) {
        tourneys.add(tourney);
    }
}
