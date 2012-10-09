package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.TourneyEvent;
import org.junit.*;
import static org.junit.Assert.*;

public class NumberedLoserBracketFactoryTest {

    private NumberedLbrFactory cut;
    private TourneyEvent tourneyEvent;
    
    @Before
    public void setUp() {
        cut = new NumberedLbrFactory(tourneyEvent);
    }

    @Test
    public void bracketOrdering() {
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 3), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 4), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 5), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 6), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 7), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 8), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 48, 1), cut.createNextBracket().getGameCode());
    }

}