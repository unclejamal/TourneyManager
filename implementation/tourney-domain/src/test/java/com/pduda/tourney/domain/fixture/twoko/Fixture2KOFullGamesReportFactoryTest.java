package com.pduda.tourney.domain.fixture.twoko;

import com.google.gson.Gson;
import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.infrastructure.gson.GsonFactory;
import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.report.PartialGamesReport;
import com.pduda.tourney.domain.util.MyUtils;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Fixture2KOFullGamesReportFactoryTest {

    private Fixture2KOFullGamesReportFactory cut;
 
    @Before
    public void setUp() {
        cut = new Fixture2KOFullGamesReportFactory();
    }

    @Test
    public void createTournament() {
        Tourney tourney = TourneyObjectMother.createTourneyPlayed(16);
        TourneyEvent event = MyUtils.any(tourney.getTourneyEvents());
        FullGamesReport full = cut.create(event.getFixture());
        
        PartialGamesReport partial = full.getPartialReport("wbr");
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), partial.getRounds().get(0).getGames().get(0).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), partial.getRounds().get(0).getGames().get(1).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), partial.getRounds().get(0).getGames().get(7).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), partial.getRounds().get(1).getGames().get(0).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), partial.getRounds().get(1).getGames().get(3).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), partial.getRounds().get(2).getGames().get(0).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), partial.getRounds().get(2).getGames().get(1).getGameCode());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), partial.getRounds().get(3).getGames().get(0).getGameCode());
        assertEquals("1A, 1B", partial.getRounds().get(3).getGames().get(0).getWinner().getName());
        // extra
        Gson gson = GsonFactory.createGsonFromBuilder();
        String json = gson.toJson(partial);
        System.out.println(json);
        
        assertTrue(json.contains(partial.getRounds().get(3).getGames().get(0).getWinner().getName()));
    }

}