package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.Team;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static com.pduda.tourney.domain.TourneyAssert.*;

public class Fixture2KOTest {

    private Fixture2KO cut;

    @Before
    public void setUp() {
    }

    @Test
    public void threeTeams() {
        List<Team> teams = createNumberOfTeams(3);

        cut = new Fixture2KO(teams);
        assertGame(1, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)));
        assertGame(3, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 2)));

        // preprocessed bye
        assertGame(1, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 1, 1)));
        assertEquals("preprocessed bye", 1, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)).getWinner().getSeed());
    }

    @Test
    public void fourTeams() {
        List<Team> teams = createNumberOfTeams(4);

        cut = new Fixture2KO(teams);
        assertGame(1, 4, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)));
        assertGame(3, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 2)));
    }

    @Test
    public void fiveTeams() {
        List<Team> teams = createNumberOfTeams(5);

        cut = new Fixture2KO(teams);
        assertGame(1, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 1)));
        assertGame(5, 4, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 2)));
        assertGame(3, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 3)));
        assertGame(0, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 4)));

        // preprocessed bye
        assertEquals("preprocessed bye", 1, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 1)).getWinner().getSeed());
        assertGame(1, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)));

        assertEquals("preprocessed bye", 3, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 3)).getWinner().getSeed());
        assertEquals("preprocessed bye", 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 4, 4)).getWinner().getSeed());
        assertGame(3, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 2)));
    }

    @Test
    public void reportingWins() {
        List<Team> teams = createNumberOfTeams(3);

        cut = new Fixture2KO(teams);

        cut.reportWinner(new GameId(NumberedWbrFactory.PREFIX, 2, 2), 2);

        assertGame(1, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 1, 1)));
        assertGame(0, 3, cut.findGame(new GameId(NumberedLbrFactory.PREFIX, 4, 1)));
        assertEquals("preprocessed bye", 3, cut.findGame(new GameId(NumberedLbrFactory.PREFIX, 4, 1)).getWinner().getSeed());
        assertGame(0, 3, cut.findGame(new GameId(NumberedLbrFactory.PREFIX, 4, 1)));
    }

    private List<Team> createNumberOfTeams(int numberOfTeams) {
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < numberOfTeams; i++) {
            Team team = new Team(new Player("Team " + String.valueOf(i + 1)));
            team.setSeed(i + 1);
            teams.add(team);
        }

        return teams;
    }
}