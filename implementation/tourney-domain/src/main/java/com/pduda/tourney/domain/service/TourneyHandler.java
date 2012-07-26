package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tournament;
import com.pduda.tourney.domain.TournamentCategory;
import java.util.List;

public interface TourneyHandler {

    int createTournament(TournamentCategory category, String tourneyName, List<Team> teams);

    Tournament getTournament(int id);

    List<Tournament> getTournaments();

    void reportWinner(int tourneyId, GameId gameId, int seed);

    void startGame(int tourneyId, GameId gameId);

    void startTourney(int id);
}
