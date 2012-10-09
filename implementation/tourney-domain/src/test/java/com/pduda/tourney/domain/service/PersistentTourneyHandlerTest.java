package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.service.tourney.PersistentTourneyHandler;
import com.pduda.tourney.domain.service.tourney.TourneyEventSo;
import com.pduda.tourney.domain.service.tourney.TourneyCreationSo;
import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.domain.util.MyUtils;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-test-context.xml"})
public class PersistentTourneyHandlerTest {

    @Inject
    private PersistentTourneyHandler cut;

    @Test
    public void testCreateTournament() {
        List<TourneyEventSo> eventDtos = MyUtils.asList(new TourneyEventSo(EventCategory.OS, new Date()),
                new TourneyEventSo(EventCategory.OD, new Date()));
        TourneyCreationSo tourneyCreationDto = new TourneyCreationSo("szeligi", eventDtos);
        long tourneyId = cut.createTournament(tourneyCreationDto);

        Set<TourneyEvent> events = cut.getEvents(tourneyId);
        long osEventId = findEventIdByCategory(events, EventCategory.OS);

        Set<Team> teams = TourneyObjectMother.createSeededTeams(4);
        cut.registerTeams(osEventId, teams);

        checkMemberz(tourneyId);
        cut.startEvent(osEventId);
        checkMemberz(tourneyId);
//
//        checkMemberzEvent(osEventId);
//        cut.startEvent(osEventId);
//        checkMemberzEvent(osEventId);


        Tourney tourney = cut.getTourney(tourneyId);
        assertEquals(2, tourney.getWaitingGames().size());

        Game w21 = tourney.getWaitingGames().get(0);
        Game w22 = tourney.getWaitingGames().get(1);
        playGame(osEventId, w21);
        playGame(osEventId, w22);
        TourneyEvent tourneySemis = cut.getEvent(osEventId);
        assertEquals(2, tourneySemis.getWaitingGames().size());

        Game l4 = tourneySemis.getWaitingGames().get(0);
        Game wfin = tourneySemis.getWaitingGames().get(1);
        playGame(osEventId, l4);
        playGame(osEventId, wfin);
        TourneyEvent tourneyWfin = cut.getEvent(osEventId);
        assertEquals(1, tourneyWfin.getWaitingGames().size());

        Game lfin = tourneyWfin.getWaitingGames().get(0);
        playGame(osEventId, lfin);
        TourneyEvent tourneyLfin = cut.getEvent(osEventId);
        assertEquals(1, tourneyLfin.getWaitingGames().size());

        Game fin = tourneyLfin.getWaitingGames().get(0);
        playGame(osEventId, fin);
        TourneyEvent tourneyFin = cut.getEvent(osEventId);
        assertEquals(0, tourneyFin.getWaitingGames().size());
    }

    private void playGame(long eventId, Game game) {
        cut.startGame(eventId, game.getGameCode());
        cut.reportWinner(eventId, game.getGameCode(), game.getTeamHome().getTeamCode());
    }

    private long findEventIdByCategory(Set<TourneyEvent> events, EventCategory eventCategory) {
        for (TourneyEvent tourneyEvent : events) {
            if (eventCategory == tourneyEvent.getEventCategory()) {
                return tourneyEvent.getId();
            }
        }

        throw new RuntimeException();
    }

    private void checkMemberz(long tourneyId) {
        ///

        System.out.println("getting tourney");
        Tourney t = cut.getTourney(tourneyId);
        System.out.println("getting events");

        Set<TourneyEvent> eeeeee = t.getTourneyEvents();

        for (TourneyEvent e : eeeeee) {
            System.out.println("inside event");

            if (EventCategory.OD == e.getEventCategory()) {
                assertEquals(0, e.getTeams().size());

            } else if (EventCategory.OS == e.getEventCategory()) {
                assertEquals(4, e.getTeams().size());
                for (Team ttt : e.getTeams()) {
                    System.out.println("inside team");
                    assertEquals(2, ttt.getSize());

                }
            }
        }
    }
//    private void checkMemberzEvent(long eventId) {
//        ///
//
//        TourneyEvent e = cut.getEvent(eventId);
//
//        assertEquals(4, e.getTeams().size());
//        for (Team ttt : e.getTeams()) {
//            assertEquals(2, ttt.getMembers().size());
//
//        }
//    }
}
