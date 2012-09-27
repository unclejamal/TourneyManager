package com.pduda.tourney.domain;

import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.report.Standings;
import java.io.Serializable;
import java.util.List;

public interface Fixture extends Serializable {

    void startGame(GameCode gameCode, FoosballTable foosballTable);

    void reportWinner(GameCode gameCode, Team team);

    Standings getStandings();

    List<Game> getOngoingGames();

    List<Game> getWaitingGames();

    FullGamesReport getGamesReports();

    TourneyEvent getEvent();

    boolean anyGameLeft();
}
