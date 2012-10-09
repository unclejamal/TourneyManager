package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.EventCategory;
import java.util.ArrayList;
import java.util.List;

public class TourneyEventPo {

    private long id;
    private List<TeamPo> teams = new ArrayList<TeamPo>();
    private EventCategory eventCategory;
    private boolean started;
    private boolean doubleGame;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TeamPo> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamPo> teams) {
        this.teams = teams;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean getDoubleGame() {
        return doubleGame;
    }

    public void setDoubleGame(boolean doubleGame) {
        this.doubleGame = doubleGame;
    }
}
