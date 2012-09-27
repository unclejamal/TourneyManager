package com.pduda.tourney.domain;

import com.pduda.tourney.domain.fixture.twoko.FinalOneBracket;
import com.pduda.tourney.domain.fixture.twoko.FinalTwoBracket;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameIdTest {

    private GameCode cut;

    @Before
    public void setUp() {
    }

    @Test
    public void objectToString() {
        cut = new GameCode("X", 4, 3);
        assertEquals("X-4-3", cut.toString());

        cut = new GameCode("1ABC", 4, 3);
        assertEquals("1ABC-4-3", cut.toString());

        cut = new GameCode("ABC1ABC", 4, 3);
        assertEquals("ABC1ABC-4-3", cut.toString());
    }

    @Test
    public void stringToObject() {
        cut = new GameCode("X-4-3");
        assertGameId("X", 4, 3, cut);

        cut = new GameCode("1ABC-4-3");
        assertGameId("1ABC", 4, 3, cut);

        cut = new GameCode("ABC1ABC-4-3");
        assertGameId("ABC1ABC", 4, 3, cut);
    }

    @Test
    public void getPrefixedRound() {
        cut = new GameCode("X", 4, 3);
        assertEquals("X4", cut.getPrefixedRound());
    }

    @Test
    public void getPrefixedRound_final() {
        cut = new GameCode(FinalOneBracket.FIN1, 1, 1);
        assertEquals("FIN1", cut.getPrefixedRound());

        cut = new GameCode(FinalTwoBracket.FIN2, 1, 1);
        assertEquals("FIN2", cut.getPrefixedRound());
    }

    private void assertGameId(String prefix, int round, int match, GameCode actual) {
        assertEquals(prefix, actual.getPrefix());
        assertEquals(round, actual.getRound());
        assertEquals(match, actual.getMatch());
    }
}