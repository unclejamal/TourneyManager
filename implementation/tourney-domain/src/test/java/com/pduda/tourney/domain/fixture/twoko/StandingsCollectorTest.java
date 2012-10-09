package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.report.Standings;
import com.pduda.tourney.domain.util.MyUtils;
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
        Tourney tourney = TourneyObjectMother.createTourneyPlayed(7);
        TourneyEvent event = MyUtils.any(tourney.getTourneyEvents());

        Fixture2KO fixture = (Fixture2KO) event.getFixture();
        FinalTwoBracket fin2 = fixture.getFinalTwoBracket();
        FinalOneBracket fin1 = fixture.getFinalOneBracket();
        LoserBracket loser = fixture.getLoserBracket();

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