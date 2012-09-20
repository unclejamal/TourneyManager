package com.pduda.tourney.domain;

import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.report.Standings;
import java.io.Serializable;
import java.util.List;

public interface Fixture extends Serializable {

    void reportWinner(GameCode gameCode, long winnerTeamCode);

    Standings getStandings();

    List<Game> getOngoingGames();

    List<Game> getWaitingGames();

    Game findGame(GameCode gameCode);

    FullGamesReport getGamesReports();

    Tourney getTourney();

    boolean anyGameLeft();
}
