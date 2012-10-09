package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.EventCategory;
import java.util.Date;

public class GamePo {

    private long eventId;
    private String gameCode;
    private TeamPo teamHome;
    private TeamPo teamAway;
    private Date startDate;
    private EventCategory eventCategory;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public TeamPo getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(TeamPo teamHome) {
        this.teamHome = teamHome;
    }

    public TeamPo getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(TeamPo teamAway) {
        this.teamAway = teamAway;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
}
