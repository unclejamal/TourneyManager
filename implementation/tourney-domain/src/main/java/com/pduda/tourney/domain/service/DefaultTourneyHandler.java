package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.*;
import com.pduda.tourney.domain.repository.TourneyRepo;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
@Transactional
public class DefaultTourneyHandler implements TourneyHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(DefaultTourneyHandler.class.getName());
    @Inject
    private TourneyRepo tourneyRepo;

    @Override
    public long createTournament(TourneyCategory category, String tourneyName, Set<Team> teams) {
        log.log(Level.INFO, "System is creating a {0} tourney \"{0}\" for: {1}", new Object[]{category, tourneyName, teams});

        Tourney tourney = new Tourney(tourneyRepo.getEntitiesCount(), category, tourneyName);

        tourney.setName(tourneyName);
        for (Team team : teams) {
            tourney.addTeam(team);
        }

        tourney.addTable(new FoosballTable("upper"));
        tourney.addTable(new FoosballTable("lower"));

        Tourney persisted = tourneyRepo.createAndReturn(tourney);
        return persisted.getId();
    }

    @Override
    public List<Tourney> getTournaments() {
        return tourneyRepo.findEntities();
    }

    @Override
    public Tourney getTournament(long id) {
        log.info("System is getting tourney " + id);
        Tourney tourney = tourneyRepo.findEntity(id);
        log.info("System found " + tourney);
        return tourney;
    }

    @Override
    public void startTourney(long id) {
//        Tourney tourney = getTournament(id);
        Tourney tourney = tourneyRepo.findEntity(id);
        System.out.println("Waitz0rage before start: " + tourney.getWaitingGames());
        tourney.startTourney();
//        tourneyRepo.merge(tourney);
        System.out.println("Waitz0rage after start: " + tourney.getWaitingGames());
    }

    @Override
    public void reportWinner(long tourneyId, GameCode gameCode, long teamCode) {
        Tourney tourney = tourneyRepo.findEntity(tourneyId);
        tourney.reportWinner(gameCode, teamCode);
    }

    @Override
    public void startGame(long tourneyId, GameCode gameCode) {
        Tourney tourney = tourneyRepo.findEntity(tourneyId);
        tourney.startGame(gameCode);
    }
}
