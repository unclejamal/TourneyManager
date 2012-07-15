package com.pduda.tourney.domain;

import com.pduda.tourney.domain.fixture.twoko.Fixture2KO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameIdTest {

    private GameId cut;

    @Before
    public void setUp() {
    }

    @Test
    public void objectToString() {
        cut = new GameId("X", 4, 3);
        assertEquals("X-4-3", cut.toString());

        cut = new GameId("1ABC", 4, 3);
        assertEquals("1ABC-4-3", cut.toString());

        cut = new GameId("ABC1ABC", 4, 3);
        assertEquals("ABC1ABC-4-3", cut.toString());
    }

    @Test
    public void stringToObject() {
        cut = new GameId("X-4-3");
        assertGameId("X", 4, 3, cut);

        cut = new GameId("1ABC-4-3");
        assertGameId("1ABC", 4, 3, cut);

        cut = new GameId("ABC1ABC-4-3");
        assertGameId("ABC1ABC", 4, 3, cut);
    }

    @Test
    public void getPrefixedRound() {
        cut = new GameId("X", 4, 3);
        assertEquals("X4", cut.getPrefixedRound());
    }

    @Test
    public void getPrefixedRound_final() {
        cut = new GameId(Fixture2KO.FIN1, 1, 1);
        assertEquals("FIN1", cut.getPrefixedRound());

        cut = new GameId(Fixture2KO.FIN2, 1, 1);
        assertEquals("FIN2", cut.getPrefixedRound());
    }

    private void assertGameId(String prefix, int round, int match, GameId actual) {
        assertEquals(prefix, actual.getPrefix());
        assertEquals(round, actual.getRound());
        assertEquals(match, actual.getMatch());
    }
}