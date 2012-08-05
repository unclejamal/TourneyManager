package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.*;
import com.pduda.tourney.domain.repository.TourneyRepo;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DefaultTourneyHandler implements TourneyHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(DefaultTourneyHandler.class.getName());
    @Inject
    private TourneyRepo tourneyRepo;

    @Override
    public int createTournament(TournamentCategory category, String tourneyName, List<Team> teams) {
        log.log(Level.INFO, "System is creating a {0} tourney \"{0}\" for: {1}", new Object[]{category, tourneyName, teams});

        Tournament tournament = new Tournament(tourneyRepo.getEntitiesCount(), category, tourneyName);

        tournament.setName(tourneyName);
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            tournament.addTeam(team);
        }

        tournament.addTable(new Table("upper"));
        tournament.addTable(new Table("lower"));

        tourneyRepo.create(tournament);

        return tournament.getId();
    }

    @Override
    public List<Tournament> getTournaments() {
        return tourneyRepo.findEntities();
    }

    @Override
    public Tournament getTournament(int id) {
        return tourneyRepo.findEntity(id);

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
