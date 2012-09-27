package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.FoosballTable;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.report.StandingsTextReport;
import java.util.ArrayList;
import java.util.List;

public class TourneyPO {

    private final Tourney tourney;
    private final StandingsTextReport report;

    public TourneyPO(Tourney tourney, StandingsTextReport report) {
        this.tourney = tourney;
        this.report = report;
    }

    public long getId() {
        return tourney.getId();
    }

    public String getName() {
        return tourney.getName();
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();
//        teams.addAll(tourney.getTeams());
        return teams;
    }

    public boolean isStarted() {
//        return tourney.isStarted();
        return true;
    }

    public EventCategory getTourneyCategory() {
//        return tourney.getEventCategory();
        return EventCategory.OD;
    }

    public List<FoosballTable> getTables() {
        List<FoosballTable> tables = new ArrayList<FoosballTable>();
        tables.addAll(tourney.getTables());
        return tables;
    }

    public List<Game> getWaitingGames() {
        return tourney.getWaitingGames();
    }

    public List<Game> getOngoingGames() {
        return tourney.getOngoingGames();
    }

    public String getStandings() {
//        return report.report(tourney.getStandings());
        return "lol";
    }
}
