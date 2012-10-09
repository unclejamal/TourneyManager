package com.pduda.tourney.web.tourneymgmt;

import java.util.ArrayList;
import java.util.List;

public class TourneyManagementPo {

    private long tourneyId;
    private int tablesTotal;
    private String tourneyName;
    private List<TourneyEventPo> events = new ArrayList<TourneyEventPo>();
    private List<GamePo> waitingGames = new ArrayList<GamePo>();
    private List<GamePo> ongoingGames = new ArrayList<GamePo>();
    private TeamPo newTeam = new TeamPo();

    public long getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(long tourneyId) {
        this.tourneyId = tourneyId;
    }

    public int getTablesTotal() {
        return tablesTotal;
    }

    public void setTablesTotal(int tablesTotal) {
        this.tablesTotal = tablesTotal;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public List<TourneyEventPo> getEvents() {
        return events;
    }

    public void setEvents(List<TourneyEventPo> events) {
        this.events = events;
    }

    public List<GamePo> getWaitingGames() {
        return waitingGames;
    }

    public void setWaitingGames(List<GamePo> waitingGames) {
        this.waitingGames = waitingGames;
    }

    public List<GamePo> getOngoingGames() {
        return ongoingGames;
    }

    public void setOngoingGames(List<GamePo> ongoingGames) {
        this.ongoingGames = ongoingGames;
    }

    public TeamPo getNewTeam() {
        return newTeam;
    }

    public void setNewTeam(TeamPo newTeam) {
        this.newTeam = newTeam;
    }

    public TourneyEventPo getEvent(long eventId) {
        for (TourneyEventPo tourneyEventPo : events) {
            if (eventId == tourneyEventPo.getId()) {
                return tourneyEventPo;
            }
        }

        throw new RuntimeException();
    }
}
