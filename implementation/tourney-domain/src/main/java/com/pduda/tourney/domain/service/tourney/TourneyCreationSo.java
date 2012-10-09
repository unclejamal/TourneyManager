package com.pduda.tourney.domain.service.tourney;

import java.util.ArrayList;
import java.util.List;

public class TourneyCreationSo {

    public final String tourneyName;
    public List<TourneyEventSo> events = new ArrayList<TourneyEventSo>();

    public TourneyCreationSo(String tourneyName, List<TourneyEventSo> events) {
        this.tourneyName = tourneyName;
        this.events = events;
    }
}
