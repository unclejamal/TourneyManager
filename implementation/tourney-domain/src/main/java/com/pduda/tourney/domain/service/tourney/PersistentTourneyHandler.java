package com.pduda.tourney.domain.service.tourney;

import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.domain.service.tourney.TourneyCreationSo;
import com.pduda.tourney.domain.service.tourney.TourneyEventSo;
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
    public long createTournament(TourneyCreationSo dto) {
        log.log(Level.INFO, "System is creating a tournament: {0}", new Object[]{dto.tourneyName});

        Tourney tourney = new Tourney(dto.tourneyName);

        for (TourneyEventSo tourneyEventDto : dto.events) {
            tourney.addEvent(new TourneyEvent(tourney, tourneyEventDto.category));
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
        return tourney.getTourneyEvents();
    }

    @Override
    public TourneyEvent getEvent(long eventId) {
        log.log(Level.INFO, "System is getting event {0}", eventId);
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
        log.log(Level.INFO, "System is starting game {0} for event {1}", new Object[]{gameCode.toString(), eventId});
        TourneyEvent event = eventRepo.findEntity(eventId);
        event.startGame(gameCode);
    }

    @Override
    public Tourney getTourney(long tourneyId) {
        return tourneyRepo.findEntity(tourneyId);
    }

    @Override
    public void registerTeams(long eventId, Set<Team> teams) {
        log.log(Level.INFO, "System is registering teams for event {0}: {1}", new Object[]{eventId, teams});
        TourneyEvent event = eventRepo.findEntity(eventId);
        event.updateTeams(teams);
        eventRepo.merge(event);
    }
}
