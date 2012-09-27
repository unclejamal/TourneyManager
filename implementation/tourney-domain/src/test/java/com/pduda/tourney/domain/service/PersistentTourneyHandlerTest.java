package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.util.MyUtils;
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
        Set<Team> teams = ObjectMother.createSeededTeams(4);
        long tourneyId = cut.createTournament(EventCategory.OD, "Szeligi", teams);

        Set<TourneyEvent> events = cut.getEvents(tourneyId);
        TourneyEvent event = MyUtils.any(events);

        cut.startEvent(event.getId());

        Tourney tourney = cut.getTourney(tourneyId);
        assertEquals(2, tourney.getWaitingGames().size());

        Game w21 = tourney.getWaitingGames().get(0);
        Game w22 = tourney.getWaitingGames().get(1);
        playGame(tourneyId, w21);
        playGame(tourneyId, w22);
        TourneyEvent tourneySemis = cut.getEvent(tourneyId);
        assertEquals(2, tourneySemis.getWaitingGames().size());

        Game l4 = tourneySemis.getWaitingGames().get(0);
        Game wfin = tourneySemis.getWaitingGames().get(1);
        playGame(tourneyId, l4);
        playGame(tourneyId, wfin);
        TourneyEvent tourneyWfin = cut.getEvent(tourneyId);
        assertEquals(1, tourneyWfin.getWaitingGames().size());

        Game lfin = tourneyWfin.getWaitingGames().get(0);
        playGame(tourneyId, lfin);
        TourneyEvent tourneyLfin = cut.getEvent(tourneyId);
        assertEquals(1, tourneyLfin.getWaitingGames().size());

        Game fin = tourneyLfin.getWaitingGames().get(0);
        playGame(tourneyId, fin);
        TourneyEvent tourneyFin = cut.getEvent(tourneyId);
        assertEquals(0, tourneyFin.getWaitingGames().size());
    }

    private void playGame(long eventId, Game game) {
        cut.startGame(eventId, game.getGameCode());
        cut.reportWinner(eventId, game.getGameCode(), game.getTeamHome().getTeamCode());
    }
}
