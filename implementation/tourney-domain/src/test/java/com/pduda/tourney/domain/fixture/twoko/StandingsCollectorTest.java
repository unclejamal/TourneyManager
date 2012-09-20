package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.report.Standings;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StandingsCollectorTest {

    private StandingsCollector cut;

    @Before
    public void setUp() {
        cut = new StandingsCollector();
    }

    @Test
    public void sevenTeams() {
        Tourney tourney = ObjectMother.createTourneyPlayed("LW", 7);
        Fixture2KO fixture = (Fixture2KO) tourney.getFixture();
        Bracket fin2 = fixture.getFinalBracketTwo();
        Bracket fin1 = fixture.getFinalBracketOne();
        Bracket loser = fixture.getLoserBracket();

        Standings standings = cut.getStandings(fin2, fin1, loser);

        assertEquals(6, standings.size());
        assertEquals(1, standings.getByPlace("1").size());
        assertEquals(1, standings.getByPlace("2").size());
        assertEquals(1, standings.getByPlace("3").size());
        assertEquals(1, standings.getByPlace("4").size());
        assertEquals(2, standings.getByPlace("5/6").size());
        assertEquals(1, standings.getByPlace("7/8").size());
    }
}