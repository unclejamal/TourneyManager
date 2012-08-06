package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyCategory;
import java.util.List;
import java.util.Set;

public interface TourneyHandler {

    long createTournament(TourneyCategory category, String tourneyName, Set<Team> teams);

    Tourney getTournament(long id);

    List<Tourney> getTournaments();

    void reportWinner(long tourneyId, GameCode gameId, long teamCode);

    void startGame(long tourneyId, GameCode gameId);

    void startTourney(long tourneyId);
}
