package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.*;
import com.pduda.tourney.domain.repository.EventRepo;
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
public class PersistentTourneyHandler implements TourneyHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(PersistentTourneyHandler.class.getName());
    @Inject
    private EventRepo eventRepo;
    @Inject
    private TourneyRepo tourneyRepo;

    @Override
    public long createTournament(EventCategory eventCategory, String tourneyName, Set<Team> teams) {
        log.log(Level.INFO, "System is creating a {0} tournament \"{0}\" for: {1}", new Object[]{eventCategory, tourneyName, teams});

        Tourney tourney = new Tourney(tourneyName);
        tourney.addTable(new FoosballTable("upper"));
        tourney.addTable(new FoosballTable("lower"));
        TourneyEvent event = new TourneyEvent(tourney, eventCategory);
        tourney.addEvent(event);
        
        for (Team team : teams) {
            event.addTeam(team);
        }

        Tourney persisted = tourneyRepo.merge(tourney);
        return persisted.getId();
    }

    @Override
    public List<Tourney> getTourneys() {
        return tourneyRepo.findEntities();
    }

    @Override
    public Set<TourneyEvent> getEvents(long tourneyId) {
        Tourney tourney = tourneyRepo.findEntity(tourneyId);
        return tourney.getEvents();
    }

    @Override
    public TourneyEvent getEvent(long eventId) {
        log.info("System is getting event " + eventId);
        TourneyEvent event = eventRepo.findEntity(eventId);
        return event;
    }

    @Override
    public void startEvent(long eventId) {
        TourneyEvent event = eventRepo.findEntity(eventId);
        event.startEvent();
    }

    @Override
    public void reportWinner(long eventId, GameCode gameCode, long teamCode) {
        TourneyEvent event = eventRepo.findEntity(eventId);
        event.reportWinner(gameCode, teamCode);
    }

    @Override
    public void startGame(long eventId, GameCode gameCode) {
        TourneyEvent event = eventRepo.findEntity(eventId);
        event.startGame(gameCode);
    }

    @Override
    public Tourney getTourney(long tourneyId) {
        return tourneyRepo.findEntity(tourneyId);
    }
}
