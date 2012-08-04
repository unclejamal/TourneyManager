package com.pduda.tourney.domain.fixture.twoko;

import com.google.gson.Gson;
import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Tournament;
import com.pduda.tourney.domain.report.PartialGamesReport;
import com.pduda.tourney.domain.report.FullGamesReport;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Fixture2KOFullGamesReportFactoryTest {

    private Fixture2KOFullGamesReportFactory cut;
 
    @Before
    public void setUp() {
        cut = new Fixture2KOFullGamesReportFactory();
    }

    @Test
    public void createTournament() {
        Tournament tournament = ObjectMother.createTournament("Liga Weekendowa", 16);
        FullGamesReport full = cut.create(tournament.getFixture());
        PartialGamesReport t = full.getPartialReport("wbr");
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 8, 1), t.getRounds().get(0).getGames().get(0).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 8, 2), t.getRounds().get(0).getGames().get(1).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 8, 8), t.getRounds().get(0).getGames().get(7).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 4, 1), t.getRounds().get(1).getGames().get(0).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 4, 4), t.getRounds().get(1).getGames().get(3).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 2, 1), t.getRounds().get(2).getGames().get(0).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 2, 2), t.getRounds().get(2).getGames().get(1).getId());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 1, 1), t.getRounds().get(3).getGames().get(0).getId());
        assertEquals("1A, 1B", t.getRounds().get(3).getGames().get(0).getWinner().getName());
       
        // extra
        Gson gson = new Gson();
        String json = gson.toJson(t);
        System.out.println(json);
        
        assertTrue(json.contains(t.getRounds().get(3).getGames().get(0).getWinner().getName()));
    }

}