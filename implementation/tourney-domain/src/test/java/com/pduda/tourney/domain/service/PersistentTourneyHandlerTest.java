package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyCategory;
import java.util.Set;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-context.xml"})
public class PersistentTourneyHandlerTest {

    @Inject
    private PersistentTourneyHandler cut;

    @Test
    public void testCreateTournament() {
        Set<Team> teams = ObjectMother.createSeededTeams(4);
        long tourneyId = cut.createTournament(TourneyCategory.OD, "Szeligi", teams);

        cut.startTourney(tourneyId);

        Tourney tourney = cut.getTournament(tourneyId);
        assertEquals(2, tourney.getWaitingGames().size());

        Game w21 = tourney.getWaitingGames().get(0);
        Game w22 = tourney.getWaitingGames().get(1);
        playGame(tourneyId, w21);
        playGame(tourneyId, w22);
        Tourney tourneySemis = cut.getTournament(tourneyId);
        assertEquals(2, tourneySemis.getWaitingGames().size());

        Game l4 = tourneySemis.getWaitingGames().get(0);
        Game wfin = tourneySemis.getWaitingGames().get(1);
        playGame(tourneyId, l4);
        playGame(tourneyId, wfin);
        Tourney tourneyWfin = cut.getTournament(tourneyId);
        assertEquals(1, tourneyWfin.getWaitingGames().size());

        Game lfin = tourneyWfin.getWaitingGames().get(0);
        playGame(tourneyId, lfin);
        Tourney tourneyLfin = cut.getTournament(tourneyId);
        assertEquals(1, tourneyLfin.getWaitingGames().size());

        Game fin = tourneyLfin.getWaitingGames().get(0);
        playGame(tourneyId, fin);
        Tourney tourneyFin = cut.getTournament(tourneyId);
        assertEquals(0, tourneyFin.getWaitingGames().size());
    }

    private void playGame(long tourneyId, Game game) {
        cut.startGame(tourneyId, game.getGameCode());
        cut.reportWinner(tourneyId, game.getGameCode(), game.getTeamHome().getId());
    }
}
