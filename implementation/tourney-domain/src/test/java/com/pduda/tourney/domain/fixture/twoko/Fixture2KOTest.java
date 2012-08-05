package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Team;
import org.junit.*;
import static org.junit.Assert.*;
import static com.pduda.tourney.domain.TourneyAssert.*;
import java.util.Set;

public class Fixture2KOTest {

    private Fixture2KO cut;

    @Before
    public void setUp() {
    }

    @Test
    public void threeTeams() {
        Set<Team> teams = ObjectMother.createSeededTeams(3);

        cut = new Fixture2KO(teams);
        assertGame(1, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)));
        assertGame(3, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 2)));

        // preprocessed bye
        assertGame(1, 0, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 1, 1)));
        assertEquals("preprocessed bye", 1, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)).getWinner().getSeed());
    }

    @Test
    public void fourTeams() {
        Set<Team> teams = ObjectMother.createSeededTeams(4);

        cut = new Fixture2KO(teams);
        assertGame(1, 4, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 1)));
        assertGame(3, 2, cut.findGame(new GameId(NumberedWbrFactory.PREFIX, 2, 2)));
    }

    @Test
    public void fiveTeams() {
        Set<Team> teams = ObjectMother.createSeededTeams(5);

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
}