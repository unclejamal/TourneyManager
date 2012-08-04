package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;

@Named
public class DefaultTourneyHandler implements TourneyHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(DefaultTourneyHandler.class.getName());
    private List<Tournament> tournaments = new ArrayList<Tournament>();

    @Override
    public int createTournament(TournamentCategory category, String tourneyName, List<Team> teams) {
        log.log(Level.INFO, "System is creating a {0} tourney \"{0}\" for: {1}", new Object[]{category, tourneyName, teams});

        Tournament tournament = new Tournament(tournaments.size(), category, tourneyName);

        tournament.setName(tourneyName);
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            tournament.addTeam(team);
        }

        tournament.addTable(new Table("upper"));
        tournament.addTable(new Table("lower"));

        tournaments.add(tournament);

        return tournament.getId();
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    @Override
    public Tournament getTournament(int id) {
        for (Tournament tournament : tournaments) {
            if (id == tournament.getId()) {
                return tournament;
            }
        }

        throw new RuntimeException("No tourney found with id: " + id);
    }

    @Override
    public void startTourney(int id) {
        getTournament(id).startTournament();
    }

    @Override
    public void reportWinner(int tourneyId, GameId gameId, int seed) {
        getTournament(tourneyId).reportWinner(gameId, seed);
    }

    @Override
    public void startGame(int tourneyId, GameId gameId) {
        getTournament(tourneyId).startGame(gameId);
    }
}
