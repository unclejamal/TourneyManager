package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import org.junit.*;
import static org.junit.Assert.*;

public class NumberedWinnerBracketFactoryTest {

    private NumberedWbrFactory cut;
    
    @Before
    public void setUp() {
        cut = new NumberedWbrFactory();
    }

    @Test
    public void bracketOrdering() {
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), cut.createNextBracket().getGameCode());
       assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 1), cut.createNextBracket().getGameCode());
    }

}