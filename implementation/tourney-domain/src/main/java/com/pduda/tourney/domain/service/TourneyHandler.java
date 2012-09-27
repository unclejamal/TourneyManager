package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import java.util.List;
import java.util.Set;

public interface TourneyHandler {

    long createTournament(EventCategory category, String tourneyName, Set<Team> teams);

    TourneyEvent getEvent(long eventId);

    Set<TourneyEvent> getEvents(long tourneyId);

    Tourney getTourney(long tourneyId);
    
    List<Tourney> getTourneys();

    void reportWinner(long tourneyId, GameCode gameId, long teamCode);

    void startGame(long tourneyId, GameCode gameId);

    void startEvent(long eventId);
}
