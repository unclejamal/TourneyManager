package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyCategory;
import java.util.List;

public interface TourneyHandler {

    int createTournament(TourneyCategory category, String tourneyName, List<Team> teams);

    Tourney getTournament(int id);

    List<Tourney> getTournaments();

    void reportWinner(int tourneyId, GameId gameId, int seed);

    void startGame(int tourneyId, GameId gameId);

    void startTourney(int id);
}
