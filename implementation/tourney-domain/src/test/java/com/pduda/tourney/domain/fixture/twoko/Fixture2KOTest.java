package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyEvent;
import org.junit.*;
import static org.junit.Assert.*;
import static com.pduda.tourney.domain.EventAssert.*;
import java.util.Set;

public class Fixture2KOTest {

    private Fixture2KO cut;
    private TourneyEvent tourney;

    @Before
    public void setUp() {
        tourney = new TourneyEvent();
    }

    @Test
    public void threeTeams() {
        TourneyEvent event = new TourneyEvent();
        Set<Team> teams = TourneyObjectMother.createSeededTeams(3);
        for (Team team : teams) {
            event.addTeam(team);
        }

        cut = new Fixture2KO(event);
        assertGame(1, 0, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1)));
        assertGame(3, 2, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2)));

        // preprocessed bye
        assertGame(1, 0, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 1, 1)));
        assertEquals("preprocessed bye", 1, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1)).getWinner().getSeed());
    }

    @Test
    public void fourTeams() {

        Set<Team> teams = TourneyObjectMother.createSeededTeams(4);
        for (Team team : teams) {
            tourney.addTeam(team);
        }

        cut = new Fixture2KO(tourney);
        assertGame(1, 4, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1)));
        assertGame(3, 2, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2)));
    }

    @Test
    public void fiveTeams() {
        TourneyEvent tourney = new TourneyEvent();
        Set<Team> teams = TourneyObjectMother.createSeededTeams(5);
        for (Team team : teams) {
            tourney.addTeam(team);
        }

        cut = new Fixture2KO(tourney);
        assertGame(1, 0, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 1)));
        assertGame(5, 4, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 2)));
        assertGame(3, 0, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 3)));
        assertGame(0, 2, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 4)));

        // preprocessed bye
        assertEquals("preprocessed bye", 1, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 1)).getWinner().getSeed());
        assertGame(1, 0, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1)));

        assertEquals("preprocessed bye", 3, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 3)).getWinner().getSeed());
        assertEquals("preprocessed bye", 2, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 4)).getWinner().getSeed());
        assertGame(3, 2, cut.findGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2)));
    }
}